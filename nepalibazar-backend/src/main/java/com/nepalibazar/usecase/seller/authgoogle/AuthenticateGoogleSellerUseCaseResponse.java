package com.nepalibazar.usecase.seller.authgoogle;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AuthenticateGoogleSellerUseCaseResponse(
        String token,
        String message,
        String[] permission,
        String userName
)
implements UseCase.Response {
}
