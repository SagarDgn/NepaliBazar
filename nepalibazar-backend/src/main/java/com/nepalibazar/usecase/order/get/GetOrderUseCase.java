package com.nepalibazar.usecase.order.get;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.entity.OrderEntity;
import com.nepalibazar.entity.UserEntity;
import com.nepalibazar.repository.OrderRepository;
import com.nepalibazar.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class GetOrderUseCase {


    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Inject
    public GetOrderUseCase(OrderRepository orderRepository,
                                UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public GetOrderUseCaseResponse execute(String token) {
        try {
            if (token == null) {
                return new GetOrderUseCaseResponse(-1, null, "Unauthorized");
            }

            String jwt = token.replace("Bearer", "").trim();
            String email = JwtUtils.getEmailFromToken(jwt);
            String role = JwtUtils.getRoleFromToken(jwt);

            if (!"BUYER".equalsIgnoreCase(role)) {
                return new GetOrderUseCaseResponse(-1, null, "Unauthorized");
            }

            Optional<UserEntity> userOpt = userRepository.findByEmailPhone(email);
            if (userOpt.isEmpty()) {
                return new GetOrderUseCaseResponse(-1, null, "User not found");
            }
            UserEntity user = userOpt.get();

            List<OrderEntity> orders = orderRepository.findByUser(user);

            // Sort orders by created date descending (newest first)
            List<OrderEntity> sortedOrders = orders.stream()
                    .sorted((o1, o2) -> o2.getOrderDate().compareTo(o1.getOrderDate()))
                    .collect(Collectors.toList());

            return new GetOrderUseCaseResponse(0, sortedOrders, "Orders retrieved successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return new GetOrderUseCaseResponse(-1, null, "Internal server error: " + e.getMessage());
        }
    }
}
