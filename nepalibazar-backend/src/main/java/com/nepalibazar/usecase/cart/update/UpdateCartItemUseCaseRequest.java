package com.nepalibazar.usecase.cart.update;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Introspected
public record UpdateCartItemUseCaseRequest(
        Integer cartItemId,
        Integer quantity
) implements UseCase.Request {

    public UpdateCartItemUseCaseRequest {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
    }
}
