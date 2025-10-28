package com.nepalibazar.usecase.wishlist.get;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;
import java.util.List;

@Serdeable
public record GetWishlistUseCaseResponse(
        Integer code,
        String message,
        List<WishListProductResponse> wishlist
) implements UseCase.Response {}
