package com.nepalibazar.usecase.payment.stripe;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record CheckoutStripeUseCaseRequest(
        Integer orderId,
        String successUrl,   // frontend URL after successful payment
        String cancelUrl     // frontend URL if payment canceled
) implements UseCase.Request {
}
