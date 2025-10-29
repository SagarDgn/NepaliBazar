package com.nepalibazar.controller;

import com.nepalibazar.convertor.OrderConvertor;
import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.usecase.order.cancel.CancelOrderUseCase;
import com.nepalibazar.usecase.order.cancel.CancelOrderUseCaseRequest;
import com.nepalibazar.usecase.order.cancel.CancelOrderUseCaseResponse;
import com.nepalibazar.usecase.order.get.GetOrderUseCase;
import com.nepalibazar.usecase.order.get.GetOrderUseCaseResponse;
import com.nepalibazar.usecase.order.place.PlaceOrderUseCase;
import com.nepalibazar.usecase.order.place.PlaceOrderUseCaseRequest;
import com.nepalibazar.usecase.order.place.PlaceOrderUseCaseResponse;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

@Controller("api/v1")
public class OrderController {

    public final PlaceOrderUseCase placeOrderUseCase;
    public final CancelOrderUseCase cancelOrderUseCase;
    public final GetOrderUseCase getOrderUseCase;

    @Inject
    public OrderController(PlaceOrderUseCase placeOrderUseCase,
                           CancelOrderUseCase cancelOrderUseCase,
                           GetOrderUseCase getOrderUseCase){
        this.placeOrderUseCase=placeOrderUseCase;
        this.cancelOrderUseCase=cancelOrderUseCase;
        this.getOrderUseCase=getOrderUseCase;
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

    @Post("/order/cancel")
    public RestResponse<CancelOrderUseCaseResponse> cancelOrder(@Body CancelOrderUseCaseRequest request,
                                                                @Header(HttpHeaders.AUTHORIZATION) String authorization) {

        if (authorization == null) {
            return RestResponse.error("Unauthorized");
        }

        try {
            CancelOrderUseCaseResponse response = cancelOrderUseCase.execute(authorization, request);
                return RestResponse.success(response);
        } catch (Exception e) {
            return RestResponse.error("Internal error: " + e.getMessage());
        }
    }

    @Get("/orders")
    public RestResponse<GetOrderUseCaseResponse> getUserOrders(@Header(HttpHeaders.AUTHORIZATION) String authorization) {
        if (authorization == null) {
            return RestResponse.error("Unauthorized");
        }
        try {
            GetOrderUseCaseResponse response = getOrderUseCase.execute(authorization);
            return RestResponse.success(response);
        } catch (Exception e) {
            return RestResponse.error("Internal error: " + e.getMessage());
        }
    }
}
