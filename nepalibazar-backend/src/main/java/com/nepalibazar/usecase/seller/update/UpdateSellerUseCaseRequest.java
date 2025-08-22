package com.nepalibazar.usecase.seller.update;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record UpdateSellerUseCaseRequest() implements UseCase.Request {
}
