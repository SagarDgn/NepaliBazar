package com.nepalibazar.usecase.product.delete;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record DeleteProductUseCaseResponse(Integer id,
                                           String message) implements UseCase.Response {
}
