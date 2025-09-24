package com.nepalibazar.usecase.cart.get;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record CartItemUseCaseResponse(
        Integer productId,
        String productName,
        Double price,
        Integer quantity
)
        implements UseCase.Response {
}
