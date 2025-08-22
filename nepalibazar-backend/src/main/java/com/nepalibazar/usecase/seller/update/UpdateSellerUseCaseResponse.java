package com.nepalibazar.usecase.seller.update;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record UpdateSellerUseCaseResponse(Integer id,
                                          String message) implements UseCase.Response {
}
