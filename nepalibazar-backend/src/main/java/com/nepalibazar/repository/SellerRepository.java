package com.nepalibazar.repository;

import com.nepalibazar.entity.SellerEntity;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface SellerRepository extends CrudRepository<SellerEntity, Integer> {
    Optional<SellerEntity> findByEmailPhone(String emailPhone);

    @Query("UPDATE SellerEntity u SET u.isLoggedIn = :status WHERE u.emailPhone = :email")
    void updateLoginStatus(String email, boolean status);
}
