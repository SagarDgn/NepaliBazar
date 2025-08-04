package com.nepalibazar.usecase.user.delete;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record DeleteUserUcResponse(Integer id,
                                   String message) implements UseCase.Response {
}
