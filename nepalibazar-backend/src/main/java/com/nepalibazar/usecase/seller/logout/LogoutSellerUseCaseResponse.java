package com.nepalibazar.usecase.seller.logout;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record LogoutSellerUseCaseResponse(Integer code,
                                          String message) implements UseCase.Response {
}
