package com.nepalibazar.controller;

import com.nepalibazar.convertor.OrderConvertor;
import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.usecase.order.place.PlaceOrderUseCase;
import com.nepalibazar.usecase.order.place.PlaceOrderUseCaseRequest;
import com.nepalibazar.usecase.order.place.PlaceOrderUseCaseResponse;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("api/v1")
public class OrderController {

    public final PlaceOrderUseCase placeOrderUseCase;

    @Inject
    public OrderController(PlaceOrderUseCase placeOrderUseCase){
        this.placeOrderUseCase=placeOrderUseCase;
    }

    @Post("/place/order")
    public RestResponse<PlaceOrderUseCaseResponse> placeOrder(@Body PlaceOrderUseCaseRequest request,
                                                              @Header(HttpHeaders.AUTHORIZATION) String authorization){
        if(authorization==null){
            return RestResponse.error("Unauthorized");
        }
        try{
            PlaceOrderUseCaseResponse response= placeOrderUseCase.execute(authorization,request);
            return RestResponse.success(response);
        }catch(Exception e){
            return RestResponse.error("Internal error"+e.getMessage());
        }

    }
}
