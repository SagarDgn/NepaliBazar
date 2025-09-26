package com.nepalibazar.usecase.payment.cod;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record CheckoutCodUseCaseRequest(
        Integer orderId
) implements UseCase.Request {
}
