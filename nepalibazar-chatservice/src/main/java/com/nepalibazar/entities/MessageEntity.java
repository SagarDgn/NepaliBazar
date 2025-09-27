package com.nepalibazar.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Entity
@Table(name = "message")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "messsage_id")
    private Integer id;

    private String senderId;

    private String receiverId;

    private String messageContent;

    @Column(name = "message_time" ,updatable = false)
    @CreatedDate
    private Instant messageTime;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoomEntity chatRoom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Instant getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Instant messageTime) {
        this.messageTime = messageTime;
    }

    public ChatRoomEntity getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoomEntity chatRoom) {
        this.chatRoom = chatRoom;
    }
}
