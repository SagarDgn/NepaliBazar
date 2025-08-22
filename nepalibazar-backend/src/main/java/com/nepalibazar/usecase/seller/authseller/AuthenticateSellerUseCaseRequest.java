package com.nepalibazar.usecase.seller.authseller;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AuthenticateSellerUseCaseRequest(String emailPhone,
                                               String password) implements UseCase.Request {
}
