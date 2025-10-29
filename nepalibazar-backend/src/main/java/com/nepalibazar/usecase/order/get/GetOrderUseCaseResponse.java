package com.nepalibazar.usecase.order.get;

import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.entity.OrderEntity;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public record GetOrderUseCaseResponse(
        Integer code,
        List<OrderEntity> orders,
        String message
) implements UseCase.Response {
}