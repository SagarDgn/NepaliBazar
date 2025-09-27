package com.nepalibazar.usecase.order.cancel;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record CancelOrderUseCaseRequest(
        Integer orderId
) implements UseCase.Request {
}
