package com.nepalibazar.usecase.user.update;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record UpdateUserUseCaseResponse(Integer id,
                                        String message) implements UseCase.Response {
}
