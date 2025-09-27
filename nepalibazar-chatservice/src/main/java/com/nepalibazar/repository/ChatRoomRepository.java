package com.nepalibazar.repository;

import com.nepalibazar.entities.ChatRoomEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Integer> {
    Optional<ChatRoomEntity> findByBuyerIdAndSellerId(String buyerId, String sellerId);
}
