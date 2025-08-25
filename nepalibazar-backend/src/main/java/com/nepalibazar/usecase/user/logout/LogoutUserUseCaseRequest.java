package com.nepalibazar.usecase.user.logout;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record LogoutUserUseCaseRequest() implements UseCase.Request {
}
