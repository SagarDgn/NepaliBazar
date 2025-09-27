package com.nepalibazar.usecase.message.send;

import com.nepalibazar.core.usecase.UseCase;

import java.time.Instant;

public record SendMessageUseCaseResponse(
        Integer messageId,
        String senderEmail,
        String receiverEmail,
        String content,
        Instant timeStamp
) implements UseCase.Response {
}
