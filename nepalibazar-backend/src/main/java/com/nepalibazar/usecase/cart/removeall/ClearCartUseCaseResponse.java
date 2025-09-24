package com.nepalibazar.usecase.cart.removeall;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record ClearCartUseCaseResponse(
        Integer code,
        String message
)
implements UseCase.Response {
}
