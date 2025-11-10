package com.nepalibazar.usecase.user.authgoogle;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AuthenticateGoogleUserUseCaseResponse(
        String token,
        String message,
        String[] permission,
        String userName
)
implements UseCase.Response {
}
