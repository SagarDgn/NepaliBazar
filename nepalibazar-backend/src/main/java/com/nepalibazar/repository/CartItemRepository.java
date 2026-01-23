package com.nepalibazar.repository;

import com.nepalibazar.entity.CartItemEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {
}
