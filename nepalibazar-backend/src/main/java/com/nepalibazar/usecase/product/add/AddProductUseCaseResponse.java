package com.nepalibazar.usecase.product.add;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AddProductUseCaseResponse(Integer id,
                                        String message) implements UseCase.Response {
}
