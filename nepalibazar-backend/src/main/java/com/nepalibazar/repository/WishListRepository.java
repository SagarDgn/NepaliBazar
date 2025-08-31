package com.nepalibazar.repository;

import com.nepalibazar.entity.UserEntity;
import com.nepalibazar.entity.WishListEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface WishListRepository extends CrudRepository<WishListEntity,Integer> {
    Optional<WishListEntity> findByUserEntity(UserEntity userEntity);
}
