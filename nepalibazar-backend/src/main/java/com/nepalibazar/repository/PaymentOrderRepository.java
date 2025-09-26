package com.nepalibazar.repository;

import com.nepalibazar.entity.PaymentOrderEntity;
import com.nepalibazar.entity.UserEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

@Repository
public interface PaymentOrderRepository extends CrudRepository<PaymentOrderEntity, Integer> {

    List<PaymentOrderEntity> findByUser(UserEntity user);
}
