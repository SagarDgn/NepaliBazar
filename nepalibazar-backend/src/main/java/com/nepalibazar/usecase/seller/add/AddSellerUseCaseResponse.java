package com.nepalibazar.usecase.seller.add;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AddSellerUseCaseResponse(Integer code,
                                       Integer id,
                                       String message) implements UseCase.Response {
}
