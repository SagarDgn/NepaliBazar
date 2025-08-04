package com.nepalibazar.usecase.user.add;

import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.domain.USER_ROLE;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

@Serdeable
public record AddUserUseCaseRequest(
        @NotBlank(message = "Username is required")
        String userName,
        @NotBlank(message = "Email or phone needed")
        String emailPhone,
        @NotBlank(message = "Password is required")
        String password,
        @NotBlank(message = "Address is required")
        String address,
        @Nullable USER_ROLE role,
         @Nullable String otp
) implements UseCase.Request {
}
