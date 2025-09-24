package com.nepalibazar.usecase.cart.remove;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public record RemoveCartUseCaseRequest(
        List<Integer> productId
) implements UseCase.Request {

}