package com.nepalibazar.repository;

import com.nepalibazar.entity.CartEntity;
import com.nepalibazar.entity.UserEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<CartEntity, Integer> {
    Optional<CartEntity> findByUser(UserEntity user);
}
