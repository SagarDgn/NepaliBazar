package com.nepalibazar.usecase.order.cancel;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.domain.ORDER_STATUS;
import com.nepalibazar.domain.PAYMENT_ORDER_STATUS;
import com.nepalibazar.entity.OrderEntity;
import com.nepalibazar.entity.UserEntity;
import com.nepalibazar.repository.OrderRepository;
import com.nepalibazar.repository.UserRepository;
import com.stripe.Stripe;
import com.stripe.model.Refund;
import com.stripe.param.RefundCreateParams;
import io.micronaut.context.annotation.Value;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import java.util.Optional;

@Singleton
public class CancelOrderUseCase {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Inject
    public CancelOrderUseCase(OrderRepository orderRepository,
                              UserRepository userRepository,
                              @Value("${stripe.secret-key}") String stripeSecret) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;

        // Initialize Stripe
        if (stripeSecret != null && !stripeSecret.trim().isEmpty()) {
            Stripe.apiKey = stripeSecret;
            System.out.println("Stripe initialized successfully");
        } else {
            System.out.println("Warning: Stripe secret key not configured");
        }
    }

    // ... rest of your existing code remains the same
    @Transactional
    public CancelOrderUseCaseResponse execute(String token, CancelOrderUseCaseRequest request) {
        try {
            if (token == null) {
                return new CancelOrderUseCaseResponse(-1, "Unauthorized");
            }

            String jwt = token.replace("Bearer", "").trim();
            String email = JwtUtils.getEmailFromToken(jwt);
            String role = JwtUtils.getRoleFromToken(jwt);

            if (!"BUYER".equalsIgnoreCase(role)) {
                return new CancelOrderUseCaseResponse(-1, "Unauthorized");
            }

            Optional<UserEntity> userOpt = userRepository.findByEmailPhone(email);
            if (userOpt.isEmpty()) {
                return new CancelOrderUseCaseResponse(-1, "User not found");
            }
            UserEntity user = userOpt.get();

            Optional<OrderEntity> orderOpt = orderRepository.findById(request.orderId());
            if (orderOpt.isEmpty()) {
                return new CancelOrderUseCaseResponse(-1, "Order not found");
            }
            OrderEntity order = orderOpt.get();

            if (!order.getUser().getId().equals(user.getId())) {
                return new CancelOrderUseCaseResponse(-1, "You cannot cancel this order");
            }

            if (order.getOrderStatus() == ORDER_STATUS.SHIPPED ||
                    order.getOrderStatus() == ORDER_STATUS.DELIVERED) {
                return new CancelOrderUseCaseResponse(-1, "Order cannot be cancelled at this stage");
            }

            // Handle Stripe refund only for Stripe payments
            if (order.getPaymentMethod() != null &&
                    order.getPaymentMethod().name().equals("STRIPE") &&
                    order.getPaymentOrder() != null &&
                    order.getPaymentOrder().getPaymentGatewayTransactionId() != null) {

                try {
                    RefundCreateParams refundCreateParams = RefundCreateParams.builder()
                            .setPaymentIntent(order.getPaymentOrder().getPaymentGatewayTransactionId())
                            .build();
                    Refund refund = Refund.create(refundCreateParams);
                    order.setPaymentOrderStatus(PAYMENT_ORDER_STATUS.REFUNDED);
                } catch (Exception e) {
                    return new CancelOrderUseCaseResponse(-1, "Stripe refund failed: " + e.getMessage());
                }
            }

            // For CASH payments, simply cancel the order
            order.setOrderStatus(ORDER_STATUS.CANCELLED);
            orderRepository.update(order);

            return new CancelOrderUseCaseResponse(0, "Order cancelled successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return new CancelOrderUseCaseResponse(-1, "Internal error: " + e.getMessage());
        }
    }
}