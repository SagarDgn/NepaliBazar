package com.nepalibazar.usecase.authuser;

import com.nepalibazar.core.usecase.UseCase;

public record AuthenticateUseCaseRequest(String emailphone,
                                         String password) implements UseCase.Request {
}
