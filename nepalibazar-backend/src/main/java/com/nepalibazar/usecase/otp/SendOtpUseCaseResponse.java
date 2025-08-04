package com.nepalibazar.usecase.otp;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record SendOtpUseCaseResponse(String message)
implements UseCase.Response {
}
