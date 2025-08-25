package com.nepalibazar.usecase.wishlist.add;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AddWishListUseCaseResponse(Integer code,
                                         String message)implements UseCase.Response {
}
