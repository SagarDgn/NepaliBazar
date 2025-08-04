package com.nepalibazar.repository;

import com.nepalibazar.entity.ProductEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
}
