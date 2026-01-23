package com.nepalibazar.usecase.order.place;

import com.nepalibazar.convertor.AddressConvertor;
import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.entity.*;
import com.nepalibazar.convertor.OrderConvertor;
import com.nepalibazar.repository.*;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.Optional;

@Singleton
public class PlaceOrderUseCase {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;

    @Inject
    public PlaceOrderUseCase(UserRepository userRepository,
                             CartRepository cartRepository,
                             OrderRepository orderRepository,
                             AddressRepository addressRepository,
                             ProductRepository productRepository){
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public PlaceOrderUseCaseResponse execute(String token, PlaceOrderUseCaseRequest request) {
        try {
            if (token == null) {
                return new PlaceOrderUseCaseResponse(-1, null, "Unauthorized");
            }

            String jwt = token.replace("Bearer", "").trim();
            String email = JwtUtils.getEmailFromToken(jwt);
            String role = JwtUtils.getRoleFromToken(jwt);

            if (!"BUYER".equalsIgnoreCase(role)) {
                return new PlaceOrderUseCaseResponse(-1, null, "Unauthorized");
            }

            Optional<UserEntity> user = userRepository.findByEmailPhone(email);
            if (user.isEmpty()) {
                return new PlaceOrderUseCaseResponse(-1, null, "User not available");
            }
            UserEntity userEntity = user.get();

            Optional<CartEntity> cart = cartRepository.findByUser(userEntity);
            if (cart.isEmpty()) {
                return new PlaceOrderUseCaseResponse(-1, null, "Cart not available");
            }
            CartEntity cartEntity = cart.get();

            // Check if cart is empty
            if (cartEntity.getCartItemEntities() == null || cartEntity.getCartItemEntities().isEmpty()) {
                return new PlaceOrderUseCaseResponse(-1, null, "Cart is empty");
            }

            // Check if all items are still available before placing order
            for (CartItemEntity cartItem : cartEntity.getCartItemEntities()) {
                // Get fresh product from database to ensure we have latest quantity
                Optional<ProductEntity> productOpt = productRepository.findById(cartItem.getProductEntity().getId());
                if (productOpt.isEmpty()) {
                    return new PlaceOrderUseCaseResponse(-1, null,
                            "Product '" + cartItem.getProductEntity().getProductName() + "' is no longer available");
                }

                ProductEntity product = productOpt.get();
                if (product.getQuantity() < cartItem.getQuantity()) {
                    return new PlaceOrderUseCaseResponse(-1, null,
                            "Product '" + product.getProductName() + "' only has " +
                                    product.getQuantity() + " items available. You have " +
                                    cartItem.getQuantity() + " in cart.");
                }
            }

            // Decrease product quantities in inventory
            for (CartItemEntity cartItem : cartEntity.getCartItemEntities()) {
                // Get product from database
                Optional<ProductEntity> productOpt = productRepository.findById(cartItem.getProductEntity().getId());
                if (productOpt.isPresent()) {
                    ProductEntity product = productOpt.get();
                    int newQuantity = product.getQuantity() - cartItem.getQuantity();
                    product.setQuantity(newQuantity);
                    productRepository.save(product); // Use save() instead of update()
                }
            }

            var userAddress = AddressConvertor.toEntity(request);
            addressRepository.save(userAddress);

            var order = OrderConvertor.fromCartAndRequest(userEntity, cartEntity, userAddress, request);
            var savedOrder = orderRepository.save(order);

            // Clear cart after successful order
            cartEntity.getCartItemEntities().clear();
            cartEntity.setTotalMrpPrice(0.0);
            cartEntity.setTotalItem(0);
            cartEntity.setSellingPrice(0.0);
            cartEntity.setDiscount(0.0);
            cartRepository.save(cartEntity); // Use save() instead of update()

            return new PlaceOrderUseCaseResponse(0, savedOrder.getId(), "Order placed successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return new PlaceOrderUseCaseResponse(-1, null, "Internal server error: " + e.getMessage());
        }
    }
}