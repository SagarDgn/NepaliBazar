package com.nepalibazar.usecase.seller.add;

import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.domain.USER_ROLE;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

@Serdeable
public record AddSellerUseCaseRequest(
        @NotBlank(message = "Name is required")
        String sellerName,
        @NotBlank(message = "Email or phone needed")
        String emailPhone,
        @NotBlank(message = "Password is required")
        String password,
        @NotBlank(message = "Address is required")
        String location,
        @Nullable
        String productCategory,
        @Nullable
        USER_ROLE role
) implements UseCase.Request {
}
