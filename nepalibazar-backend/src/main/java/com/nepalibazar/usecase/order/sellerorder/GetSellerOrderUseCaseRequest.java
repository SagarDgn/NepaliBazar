package com.nepalibazar.usecase.order.sellerorder;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record GetSellerOrderUseCaseRequest() implements UseCase.Request {
}
