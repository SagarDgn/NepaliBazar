package com.nepalibazar.usecase.message.get;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.entities.ChatRoomEntity;
import com.nepalibazar.entities.MessageEntity;
import com.nepalibazar.repository.ChatRoomRepository;
import com.nepalibazar.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GetMessageUseCase {

    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Autowired
    public GetMessageUseCase(MessageRepository messageRepository,
                             ChatRoomRepository chatRoomRepository) {
        this.messageRepository = messageRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    // ✅ Pass receiverEmail from frontend when user opens chat
    public List<GetMessageUseCaseResponse> execute(String token, String receiverEmail) {
        try {
            String currentUserEmail = JwtUtils.extractEmail(token);

            // Try to find chat room where current user is buyer or seller
            Optional<ChatRoomEntity> chatRoomOpt =
                    chatRoomRepository.findByBuyerIdAndSellerId(currentUserEmail, receiverEmail)
                            .or(() -> chatRoomRepository.findByBuyerIdAndSellerId(receiverEmail, currentUserEmail));

            if (chatRoomOpt.isEmpty()) {
                return Collections.emptyList(); // No chat room yet
            }

            ChatRoomEntity chatRoom = chatRoomOpt.get();

            // Fetch all messages in this chat room (sorted by time)
            List<MessageEntity> messages = messageRepository.findByChatRoomIdOrderByMessageTime(chatRoom.getId());

            // Map to response
            return messages.stream()
                    .map(message -> new GetMessageUseCaseResponse(
                            message.getSenderId(),
                            message.getMessageContent(),
                            message.getMessageTime()
                    ))
                    .collect(Collectors.toUnmodifiableList());

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.singletonList(
                    new GetMessageUseCaseResponse(null, "Error: " + e.getMessage(), null)
            );
        }
    }
}
