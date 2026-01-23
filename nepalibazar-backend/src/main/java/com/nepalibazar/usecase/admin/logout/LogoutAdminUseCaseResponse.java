package com.nepalibazar.usecase.admin.logout;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record LogoutAdminUseCaseResponse(Integer code,
                                         String message)
implements UseCase.Response {
}
