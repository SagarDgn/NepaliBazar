package com.nepalibazar.usecase.seller.authgoogle;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AuthenticateGoogleSellerUseCaseRequest(
        String token,
        String clientId
)
implements UseCase.Request {
}
