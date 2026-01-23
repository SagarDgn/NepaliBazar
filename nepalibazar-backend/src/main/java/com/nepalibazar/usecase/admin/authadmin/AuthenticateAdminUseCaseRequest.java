package com.nepalibazar.usecase.admin.authadmin;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AuthenticateAdminUseCaseRequest (
        String userName,
        String password
)implements UseCase.Request {
}
