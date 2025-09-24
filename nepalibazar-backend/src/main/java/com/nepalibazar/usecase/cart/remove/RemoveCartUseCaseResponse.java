package com.nepalibazar.usecase.cart.remove;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record RemoveCartUseCaseResponse(Integer code,
                                        String message,
                                         Integer removed ) implements UseCase.Response {
}
