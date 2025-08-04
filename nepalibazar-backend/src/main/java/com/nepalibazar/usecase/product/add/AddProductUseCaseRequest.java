package com.nepalibazar.usecase.product.add;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AddProductUseCaseRequest(
        String productName,
        String aboutProduct,
        Double  price,
        Integer discount,
        String image,
        Integer quantity
) implements UseCase.Request {
}
