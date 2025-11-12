package com.nepalibazar.usecase.product.search;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record SearchOneProductUseCaseRequest(
        Integer productId
) implements UseCase.Request{
}
