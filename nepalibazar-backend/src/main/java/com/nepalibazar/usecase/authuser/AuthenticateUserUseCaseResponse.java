package com.nepalibazar.usecase.authuser;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AuthenticateUserUseCaseResponse(
        String token,
        String successAuthenticate, String[] permission,
        String userName
) implements UseCase.Response {
}
