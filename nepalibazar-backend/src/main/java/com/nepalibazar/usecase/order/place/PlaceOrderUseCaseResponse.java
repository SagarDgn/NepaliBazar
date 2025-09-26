package com.nepalibazar.usecase.order.place;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record PlaceOrderUseCaseResponse(
        Integer code,
        Integer orderId,
        String message
) implements UseCase.Response {
}
