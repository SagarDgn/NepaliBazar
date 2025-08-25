package com.nepalibazar.repository;

import com.nepalibazar.entity.UserEntity;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
        Optional<UserEntity> findByEmailPhone(String emailPhone);

        @Query("UPDATE UserEntity u SET u.isLoggedIin = :status WHERE u.emailPhone = :email")
        void updateLoginStatus(String email, boolean status);
}
