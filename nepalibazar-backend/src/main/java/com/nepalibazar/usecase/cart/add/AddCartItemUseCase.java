package com.nepalibazar.usecase.cart.add;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.entity.CartEntity;
import com.nepalibazar.entity.CartItemEntity;
import com.nepalibazar.entity.ProductEntity;
import com.nepalibazar.entity.UserEntity;
import com.nepalibazar.repository.CartRepository;
import com.nepalibazar.repository.ProductRepository;
import com.nepalibazar.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.Optional;

@Singleton
public class AddCartItemUseCase {

    public final CartRepository cartRepository;
    public final UserRepository userRepository;
    public final ProductRepository productRepository;

    @Inject
    public AddCartItemUseCase(CartRepository cartRepository,
                              UserRepository userRepository,
                              ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public AddCartItemUseCaseResponse execute(String token,
                                              AddCartItemUseCaseRequest request) {
        try {
            if (token == null) {
                return new AddCartItemUseCaseResponse(-1, "Unauthorized: missing or invalid token");
            }

            String jwt = token.replace("Bearer", "").trim();
            String email = JwtUtils.getEmailFromToken(jwt);
            String role = JwtUtils.getRoleFromToken(jwt);

            if (!"BUYER".equalsIgnoreCase(role)) {
                return new AddCartItemUseCaseResponse(-1, "Unauthorized");
            }

            Optional<UserEntity> user = userRepository.findByEmailPhone(email);
            if (user.isEmpty()) {
                return new AddCartItemUseCaseResponse(-1, "Login required");
            }
            UserEntity userEntity = user.get();

            Optional<ProductEntity> product = productRepository.findById(request.productId());
            if (product.isEmpty()) {
                return new AddCartItemUseCaseResponse(-1, "Product not available");
            }
            ProductEntity productEntity = product.get();

            // Check if requested quantity is available
            if (request.quantity() == null || request.quantity() <= 0) {
                return new AddCartItemUseCaseResponse(-1, "Invalid quantity");
            }

            if (productEntity.getQuantity() < request.quantity()) {
                return new AddCartItemUseCaseResponse(-1,
                        "Only " + productEntity.getQuantity() + " items available");
            }

            CartEntity cart = cartRepository.findByUser(userEntity)
                    .orElseGet(() -> {
                        CartEntity cartEntity = new CartEntity();
                        cartEntity.setUser(userEntity);
                        return cartRepository.save(cartEntity);
                    });

            // Check if product already exists in cart
            Optional<CartItemEntity> existingCartItem = cart.getCartItemEntities().stream()
                    .filter(item -> item.getProductEntity().getId().equals(productEntity.getId()))
                    .findFirst();

            if (existingCartItem.isPresent()) {
                // Update existing item quantity
                CartItemEntity cartItem = existingCartItem.get();
                int newQuantity = cartItem.getQuantity() + request.quantity();

                // Check if updated quantity exceeds available stock
                if (productEntity.getQuantity() < newQuantity) {
                    return new AddCartItemUseCaseResponse(-1,
                            "Cannot add more items. Only " + productEntity.getQuantity() + " available. " +
                                    "You already have " + cartItem.getQuantity() + " in cart.");
                }

                cartItem.setQuantity(newQuantity);
                cartItem.setPrice(productEntity.getPrice() * newQuantity);
            } else {
                // Add new cart item
                CartItemEntity cartItemEntity = new CartItemEntity();
                cartItemEntity.setCart(cart);
                cartItemEntity.setProductEntity(productEntity);
                cartItemEntity.setQuantity(request.quantity());
                cartItemEntity.setPrice(productEntity.getPrice() * request.quantity());
                cart.getCartItemEntities().add(cartItemEntity);
            }

            // Recalculate cart totals
            int totalItems = cart.getCartItemEntities().stream()
                    .mapToInt(CartItemEntity::getQuantity)
                    .sum();

            double totalMrpPrice = cart.getCartItemEntities().stream()
                    .mapToDouble(item -> item.getProductEntity().getPrice() * item.getQuantity())
                    .sum();

            double totalSellingPrice = cart.getCartItemEntities().stream()
                    .mapToDouble(item -> item.getProductEntity().getDiscountedPrice() * item.getQuantity())
                    .sum();

            cart.setTotalItem(totalItems);
            cart.setTotalMrpPrice(totalMrpPrice);
            cart.setSellingPrice(totalSellingPrice);

            // Calculate discount percentage
            if (totalMrpPrice > 0) {
                double discountPercent = ((totalMrpPrice - totalSellingPrice) / totalMrpPrice) * 100;
                cart.setDiscount(discountPercent);
            } else {
                cart.setDiscount(0.0);
            }

            cartRepository.save(cart);
            return new AddCartItemUseCaseResponse(0, "Added to cart");

        } catch (Exception e) {
            e.printStackTrace();
            return new AddCartItemUseCaseResponse(-1, "Internal error occurred: " + e.getMessage());
        }
    }
}