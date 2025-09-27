package com.nepalibazar.usecase.message.get;

import com.nepalibazar.core.usecase.UseCase;

import java.time.Instant;

public record GetMessageUseCaseResponse(
        String senderemail,
        String content,
        Instant timeStamp

)
        implements UseCase.Response {
}
