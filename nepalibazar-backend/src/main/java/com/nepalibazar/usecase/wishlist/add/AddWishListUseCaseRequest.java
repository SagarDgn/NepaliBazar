package com.nepalibazar.usecase.wishlist.add;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AddWishListUseCaseRequest(
        Integer productId
)  implements UseCase.Request {
}
