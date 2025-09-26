package com.nepalibazar.repository;

import com.nepalibazar.entity.OrderEntity;
import com.nepalibazar.entity.PaymentOrderEntity;
import com.nepalibazar.entity.UserEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity,Integer> {
    List<OrderEntity> findByUser(UserEntity user);
    List<OrderEntity> findByPaymentOrder(PaymentOrderEntity paymentOrder);
}
