package com.nepalibazar.usecase.seller.products;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record GetSellerProductUseCaseResponse(
        Integer id,
        String productName,
        String aboutProduct,
        String image,
        Double price,
        Integer discount,
        Integer quantity
) implements UseCase.Response {
}
