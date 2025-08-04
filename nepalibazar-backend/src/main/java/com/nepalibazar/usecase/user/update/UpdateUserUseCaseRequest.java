package com.nepalibazar.usecase.user.update;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record UpdateUserUseCaseRequest(Integer id,
                                       String userName,
                                       String emailPhone,
                                       String password,
                                       String address) implements UseCase.Request {
}
