package com.nepalibazar.usecase.cart.update;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Introspected
public record UpdateCartItemUseCaseResponse(
        Integer code,
        String message
) implements UseCase.Response {
}
