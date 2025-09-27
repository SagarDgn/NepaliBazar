package com.nepalibazar.usecase.message.send;

import com.nepalibazar.core.usecase.UseCase;

public record SendMessageUseCaseRequest(

        String receiverEmail,
        String content
)implements UseCase.Request {
}
