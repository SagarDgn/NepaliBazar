package com.nepalibazar.usecase.cart.update;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.entity.CartEntity;
import com.nepalibazar.entity.CartItemEntity;
import com.nepalibazar.entity.ProductEntity;
import com.nepalibazar.entity.UserEntity;
import com.nepalibazar.repository.CartItemRepository; // ADD THIS
import com.nepalibazar.repository.CartRepository;
import com.nepalibazar.repository.ProductRepository;
import com.nepalibazar.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.Optional;

@Singleton
public class UpdateCartItemUseCase {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Inject
    public UpdateCartItemUseCase(CartRepository cartRepository,
                                 CartItemRepository cartItemRepository,
                                 UserRepository userRepository,
                                 ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public UpdateCartItemUseCaseResponse execute(String token,
                                                 UpdateCartItemUseCaseRequest request) {
        try {
            if (token == null) {
                return new UpdateCartItemUseCaseResponse(-1, "Unauthorized: missing or invalid token");
            }

            String jwt = token.replace("Bearer", "").trim();
            String email = JwtUtils.getEmailFromToken(jwt);
            String role = JwtUtils.getRoleFromToken(jwt);

            if (!"BUYER".equalsIgnoreCase(role)) {
                return new UpdateCartItemUseCaseResponse(-1, "Unauthorized");
            }

            Optional<UserEntity> user = userRepository.findByEmailPhone(email);
            if (user.isEmpty()) {
                return new UpdateCartItemUseCaseResponse(-1, "Login required");
            }
            UserEntity userEntity = user.get();


            Optional<CartEntity> cartOpt = cartRepository.findByUser(userEntity);
            if (cartOpt.isEmpty()) {
                return new UpdateCartItemUseCaseResponse(-1, "Cart not found");
            }
            CartEntity cart = cartOpt.get();
            
            if (request.quantity() <= 0) {
                return new UpdateCartItemUseCaseResponse(-1, "Quantity must be at least 1");
            }

            // Find the cart item by cartItemId
            Optional<CartItemEntity> cartItemOpt = cartItemRepository.findById(request.cartItemId());
            if (cartItemOpt.isEmpty()) {
                return new UpdateCartItemUseCaseResponse(-1, "Cart item not found");
            }

            CartItemEntity cartItem = cartItemOpt.get();

            // Verify this cart item belongs to the user's cart
            if (!cartItem.getCart().getId().equals(cart.getId())) {
                return new UpdateCartItemUseCaseResponse(-1, "Unauthorized to update this cart item");
            }

            ProductEntity product = cartItem.getProductEntity();

            if (product.getQuantity() < request.quantity()) {
                return new UpdateCartItemUseCaseResponse(-1,
                        "Only " + product.getQuantity() + " items available");
            }

            cartItem.setQuantity(request.quantity());
            cartItem.setPrice(product.getPrice() * request.quantity());

            recalculateCartTotals(cart);

            cartRepository.save(cart);

            return new UpdateCartItemUseCaseResponse(0, "Cart updated successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateCartItemUseCaseResponse(-1, "Internal error occurred: " + e.getMessage());
        }
    }

    private void recalculateCartTotals(CartEntity cart) {
        // Calculate total items count
        int totalItems = cart.getCartItemEntities().stream()
                .mapToInt(CartItemEntity::getQuantity)
                .sum();

        // Calculate total MRP price
        double totalMrpPrice = cart.getCartItemEntities().stream()
                .mapToDouble(item -> item.getProductEntity().getPrice() * item.getQuantity())
                .sum();

        // Calculate total selling price (with discounts)
        double totalSellingPrice = cart.getCartItemEntities().stream()
                .mapToDouble(item -> item.getProductEntity().getDiscountedPrice() * item.getQuantity())
                .sum();

        // Calculate discount percentage
        double discountPercentage = 0.0;
        if (totalMrpPrice > 0) {
            discountPercentage = ((totalMrpPrice - totalSellingPrice) / totalMrpPrice) * 100;
        }

        cart.setTotalItem(totalItems);
        cart.setTotalMrpPrice(totalMrpPrice);
        cart.setSellingPrice(totalSellingPrice);
        cart.setDiscount(discountPercentage);
    }
}