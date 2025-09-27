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
    public final MessageRepository messageRepository;

    public final ChatRoomRepository chatRoomRepository;

    @Autowired
    public GetMessageUseCase(MessageRepository messageRepository,

                             ChatRoomRepository chatRoomRepository){
        this.messageRepository=messageRepository;

        this.chatRoomRepository=chatRoomRepository;
    }

    public List<GetMessageUseCaseResponse> execute(String token){
        try{
            String currentUserEmail= JwtUtils.extractEmail(token);
            Optional<ChatRoomEntity> chatRoomEntities= chatRoomRepository.findByBuyerIdAndSellerId(currentUserEmail,currentUserEmail);
            return chatRoomEntities.stream()
                    .flatMap(chatRoomEntity -> messageRepository
                            .findByChatRoomIdOrderByMessageTime(chatRoomEntity.getId())
                            .stream())
                    .map(message-> new GetMessageUseCaseResponse(
                            message.getSenderId(),
                            message.getMessageContent(),
                            message.getMessageTime()
                    ))
                    .collect(Collectors.toUnmodifiableList());




        }catch (Exception e){
            return Collections.singletonList(new GetMessageUseCaseResponse( null, null, null));
        }
    }
}
