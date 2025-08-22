package com.nepalibazar.usecase.user.authuser;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AuthenticateUserUseCaseRequest(String emailPhone,
                                             String password) implements UseCase.Request {
}
