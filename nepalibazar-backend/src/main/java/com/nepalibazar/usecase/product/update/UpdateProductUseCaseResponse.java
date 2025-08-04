package com.nepalibazar.usecase.product.update;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record UpdateProductUseCaseResponse(Integer id,
                                           String message) implements UseCase.Response {
}
