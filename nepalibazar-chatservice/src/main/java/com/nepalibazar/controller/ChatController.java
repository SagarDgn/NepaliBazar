package com.nepalibazar.controller;

import com.nepalibazar.controller.payload.ChatMessageWrapper;
import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.usecase.message.get.GetMessageUseCase;
import com.nepalibazar.usecase.message.get.GetMessageUseCaseResponse;
import com.nepalibazar.usecase.message.send.SendMessageUseCase;
import com.nepalibazar.usecase.message.send.SendMessageUseCaseRequest;
import com.nepalibazar.usecase.message.send.SendMessageUseCaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ChatController {

    public final SendMessageUseCase sendMessageUseCase;
    public final SimpMessagingTemplate simpMessagingTemplate;
    public final GetMessageUseCase getMessageUseCase;


    @Autowired
    public ChatController(SendMessageUseCase sendMessageUseCase,
                          SimpMessagingTemplate simpMessagingTemplate,
                          GetMessageUseCase getMessageUseCase
                          ){
        this.sendMessageUseCase=sendMessageUseCase;
        this.simpMessagingTemplate=simpMessagingTemplate;
        this.getMessageUseCase=getMessageUseCase;

    }

    @MessageMapping("/chat.sendMessage")
    public RestResponse<Void> sendMessage(@Payload ChatMessageWrapper payload,
                                          @Header("Authorization") String authorization){
        try{

            if(authorization==null){
                return RestResponse.error("Unauthorized");
            }

            String token= authorization.replace("Bearer","").trim();
            SendMessageUseCaseRequest request= new SendMessageUseCaseRequest(
                    payload.receiverEmail(),
                    payload.content()
            );

            SendMessageUseCaseResponse response= sendMessageUseCase.execute(
                    payload.token(), request
            );

            simpMessagingTemplate.convertAndSendToUser(
                    response.senderEmail(),
                    "/queue/messages",
                    response
            );
            simpMessagingTemplate.convertAndSendToUser(
                    response.receiverEmail(),
                    "queue/messages",
                    response
            );
            return RestResponse.success();

        }catch (Exception e){
            return RestResponse.error("Internal error"+e.getLocalizedMessage());
        }
    }

    @MessageMapping("/chat.getAll")
    public RestResponse<Void> getMessage(@Header("Authorization") String authorization){
        if (authorization==null){
            return RestResponse.error("Unauthorized");

        }
        String token= authorization.replace("Bearer","").trim();
        try{
            List<GetMessageUseCaseResponse> allMessages= getMessageUseCase.execute(token);
            simpMessagingTemplate.convertAndSendToUser(
                    JwtUtils.extractEmail(token),
                    "/queue/messages",
                    allMessages
            );
            return RestResponse.success();

        }catch(Exception e){
            return RestResponse.error(e.getLocalizedMessage());
        }
    }

}
