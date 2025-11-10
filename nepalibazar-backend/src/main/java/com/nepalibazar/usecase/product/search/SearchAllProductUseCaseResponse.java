package com.nepalibazar.usecase.product.search;

import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.entity.SellerEntity;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record SearchAllProductUseCaseResponse(
        Integer id,
        String productName,
        String aboutProduct,
        String image,
        Double price,
        Integer discount,
        Integer quantity,
        SellerEntity sellerEntity,
        Double discountedPrice
) implements UseCase.Response {
}
