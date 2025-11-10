package com.nepalibazar.usecase.order.place;

import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.domain.PAYMENT_METHOD;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record PlaceOrderUseCaseRequest(
        PAYMENT_METHOD paymentMethod,
        String country,
        String city,
        String state,
        String street,
        String pinCode,
        String phoneNumber

) implements UseCase.Request {
}
