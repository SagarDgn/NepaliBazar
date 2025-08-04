package com.nepalibazar.usecase.product.update;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record UpdateProductUseCaseRequest(
        Integer id,
        String productName,
        String aboutProduct,
        Double  price,
        String image,
        Integer quantity,
        Integer discount

) implements UseCase.Request {
}
