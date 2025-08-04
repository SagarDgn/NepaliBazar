package com.nepalibazar.usecase.user.add;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AddUserUseCaseResponse(Integer code,
                                     Integer id,
                                     String message) implements UseCase.Response {
}
