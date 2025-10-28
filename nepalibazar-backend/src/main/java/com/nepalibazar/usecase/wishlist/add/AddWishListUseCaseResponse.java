package com.nepalibazar.usecase.wishlist.add;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

import java.util.Set;

@Serdeable
public record AddWishListUseCaseResponse(Integer code,
                                         String message,
                                         boolean isWishlisted)implements UseCase.Response {
}
