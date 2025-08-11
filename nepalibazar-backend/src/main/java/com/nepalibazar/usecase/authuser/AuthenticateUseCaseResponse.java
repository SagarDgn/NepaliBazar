package com.nepalibazar.usecase.authuser;

import com.nepalibazar.core.usecase.UseCase;

public record AuthenticateUseCaseResponse(
        String token,
        String[] permission,
        String emailphone
) implements UseCase.Response {
}
