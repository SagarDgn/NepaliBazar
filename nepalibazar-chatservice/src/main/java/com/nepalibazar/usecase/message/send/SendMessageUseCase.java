package com.nepalibazar.usecase.message.send;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.entities.ChatRoomEntity;
import com.nepalibazar.entities.MessageEntity;
import com.nepalibazar.repository.ChatRoomRepository;
import com.nepalibazar.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SendMessageUseCase {

    public final ChatRoomRepository chatRoomRepository;
    public final MessageRepository messageRepository;


    @Autowired
    public SendMessageUseCase(ChatRoomRepository chatRoomRepository,
                              MessageRepository messageRepository
                              ){
        this.chatRoomRepository=chatRoomRepository;
        this.messageRepository=messageRepository;

    }

    public SendMessageUseCaseResponse execute(String token, SendMessageUseCaseRequest request){

        try{
            if(token==null){
                return new SendMessageUseCaseResponse(null,null,null,null,null);
            }

            String jwt= token.replace("Bearer","").trim();
            String senderEmail= JwtUtils.extractEmail(jwt);

            ChatRoomEntity chatRoom= chatRoomRepository.
                    findByBuyerIdAndSellerId(senderEmail,request.receiverEmail())
                    .or(()->chatRoomRepository.findByBuyerIdAndSellerId(request.receiverEmail(),senderEmail))
                    .orElseGet(()->chatRoomRepository.save(
                            new ChatRoomEntity(){{
                                setBuyerId(senderEmail);
                                setSellerId(request.receiverEmail());
                            }}
                    ));

            MessageEntity message= new MessageEntity();
            message.setSenderId(senderEmail);
            message.setReceiverId(request.receiverEmail());
            message.setMessageContent(request.content());
            message.setMessageTime(Instant.now());
            message.setChatRoom(chatRoom);
            message= messageRepository.save(message);

            return new SendMessageUseCaseResponse(
                    message.getId(),
                    message.getSenderId(),
                    message.getReceiverId(),
                    message.getMessageContent(),
                    message.getMessageTime()

            );


        }catch(Exception e){
            e.printStackTrace();
            return  new SendMessageUseCaseResponse(null,null,null,null,null);
        }

    }
}
