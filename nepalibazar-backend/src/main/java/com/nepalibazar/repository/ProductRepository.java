package com.nepalibazar.repository;

import com.nepalibazar.entity.ProductEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    List<ProductEntity> findBySeller_EmailPhone(String emailPhone);
}
