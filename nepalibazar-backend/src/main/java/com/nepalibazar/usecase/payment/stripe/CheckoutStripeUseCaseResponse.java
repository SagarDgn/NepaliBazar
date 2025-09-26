package com.nepalibazar.usecase.payment.stripe;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record CheckoutStripeUseCaseResponse(
        Integer code,
        String message,
        String sessionUrl
) implements UseCase.Response {
}
