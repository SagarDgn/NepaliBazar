package com.nepalibazar.usecase.order.sellerorder;

import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.entity.OrderEntity;
import com.nepalibazar.entity.UserEntity;
import io.micronaut.serde.annotation.Serdeable;
import org.hibernate.query.Order;

import java.util.List;

@Serdeable
public record GetSellerOrderUseCaseResponse(
        Integer code,
        List<OrderEntity> orders,
        String message

)implements UseCase.Response {
}
