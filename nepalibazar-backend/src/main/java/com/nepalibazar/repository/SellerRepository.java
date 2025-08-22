package com.nepalibazar.repository;

import com.nepalibazar.entity.SellerEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface SellerRepository extends CrudRepository<SellerEntity, Integer> {
    Optional<SellerEntity> findByEmailPhone(String emailPhone);
}
