package com.nepalibazar.usecase.order.cancel;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record CancelOrderUseCaseResponse(

        Integer code,
        String message
)implements UseCase.Response {
}
