package com.nepalibazar.usecase.admin.authadmin;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AuthenticateAdminUseCaseResponse(
        String token,
        String successAuthenticate,
        String permission,
        String userName
)implements UseCase.Response {
}
