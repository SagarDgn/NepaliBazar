package com.nepalibazar.usecase.seller.search;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record SearchSellerUseCaseRequest() implements UseCase.Request {
}
