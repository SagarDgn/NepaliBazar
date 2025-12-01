package com.nepalibazar.repository;

import com.nepalibazar.entity.OrderEntity;
import com.nepalibazar.entity.PaymentOrderEntity;
import com.nepalibazar.entity.SellerEntity;
import com.nepalibazar.entity.UserEntity;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity,Integer> {
    List<OrderEntity> findByUser(UserEntity user);
    List<OrderEntity> findByPaymentOrder(PaymentOrderEntity paymentOrder);

    @Query("""
        SELECT o
        FROM OrderEntity o
        WHERE o.sellerEmail = :sellerEmail
        ORDER BY o.orderDate DESC
    """)
    List<OrderEntity> findOrdersBySellerEmail(String sellerEmail);
}
