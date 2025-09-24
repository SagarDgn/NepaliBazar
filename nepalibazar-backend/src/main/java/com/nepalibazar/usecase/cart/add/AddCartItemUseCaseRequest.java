package com.nepalibazar.usecase.cart.add;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AddCartItemUseCaseRequest(
        Integer productId
)implements UseCase.Request {
}
