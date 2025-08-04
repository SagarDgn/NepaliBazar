package com.nepalibazar.usecase.product.search;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record SearchAllProductUseCaseResponse(
        Integer id,
        String productName,
        String aboutProduct,
        String image,
        Double price,
        Integer discount,
        Integer quantity
) implements UseCase.Response {
}
