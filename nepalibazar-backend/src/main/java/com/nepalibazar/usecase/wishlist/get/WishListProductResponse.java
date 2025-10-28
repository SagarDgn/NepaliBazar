package com.nepalibazar.usecase.wishlist.get;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record WishListProductResponse(
        Integer id,
        String productName,
        String image,
        Double price
) implements UseCase.Response {
}
