package com.nepalibazar.usecase.cart.get;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public record GetUserCartItemUseCaseResponse(
       Integer code,
       String message,
       List<CartItemUseCaseResponse> cartItems,
       Integer totalItems,
       Double totalMrpPrice,
       Double totalSellingPrice
)implements UseCase.Response {
}
