package com.nepalibazar.usecase.user.authgoogle;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AuthenticateGoogleUserUseCaseRequest(
        String token,
        String clientId
)
implements UseCase.Request {
}
