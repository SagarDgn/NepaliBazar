package com.nepalibazar.repository;

import com.nepalibazar.entity.OrderEntity;
import com.nepalibazar.entity.OrderItemEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface OrderItemRepsitory extends CrudRepository<OrderItemEntity,Integer> {

    List<OrderItemEntity> findByOrder(OrderEntity order);
}
