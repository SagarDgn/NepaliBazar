package com.nepalibazar.usecase.order.sellerorder;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.entity.OrderEntity;
import com.nepalibazar.repository.OrderRepository;
import com.nepalibazar.repository.SellerRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class GetSellerOrderUseCase {

    private final OrderRepository orderRepository;
    private final SellerRepository sellerRepository;

    @Inject
    public GetSellerOrderUseCase(OrderRepository orderRepository,
                                 SellerRepository sellerRepository) {
        this.orderRepository = orderRepository;
        this.sellerRepository = sellerRepository;
    }

    public GetSellerOrderUseCaseResponse execute(String token) {
        try {
            String jwt = token.replace("Bearer", "").trim();
            String email = JwtUtils.getEmailFromToken(jwt);
            String role = JwtUtils.getRoleFromToken(jwt);

            if (!"SELLER".equalsIgnoreCase(role)) {
                return new GetSellerOrderUseCaseResponse(-1, null, "Not authorized");
            }

            Optional<?> sellerOpt = sellerRepository.findByEmailPhone(email);
            if (sellerOpt.isEmpty()) {
                return new GetSellerOrderUseCaseResponse(-1, null, "Seller not found");
            }

            List<OrderEntity> orders = orderRepository.findOrdersBySellerEmail(email);

            return new GetSellerOrderUseCaseResponse(1, orders, "Orders fetched successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return new GetSellerOrderUseCaseResponse(-1, null, "Internal error: " + e.getMessage());
        }
    }
}
