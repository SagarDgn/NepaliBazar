package com.nepalibazar.repository;

import com.nepalibazar.entity.AddressEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity,Integer> {
}
