package com.nepalibazar.repository;

import com.nepalibazar.entity.UserEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
        Optional<UserEntity> findByUserName(String userName);
}
