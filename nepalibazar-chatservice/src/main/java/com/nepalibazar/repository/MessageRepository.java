package com.nepalibazar.repository;

import com.nepalibazar.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
    List<MessageEntity> findByChatRoomIdOrderByMessageTime(Integer chatRoomId);
}
