package com.nepalibazar.repository;

import com.nepalibazar.entity.AdminEntity;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<AdminEntity,Integer> {

    public Optional<AdminEntity> findByUserName(String userName);

    @Query("UPDATE AdminEntity u SET u.isLoggedIn = :status WHERE u.userName = :userName")
    void updateLoginStatus(String userName, boolean status);
}
