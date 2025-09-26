package com.nepalibazar.controller;

import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.usecase.payment.cod.CheckoutCodUseCase;
import com.nepalibazar.usecase.payment.cod.CheckoutCodUseCaseRequest;
import com.nepalibazar.usecase.payment.cod.CheckoutCodUseCaseResponse;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Header;
import jakarta.inject.Inject;

@Controller("/api/v1")
public class CodOrderController {

    public final CheckoutCodUseCase checkoutCodUseCase;

    @Inject
    public CodOrderController(CheckoutCodUseCase checkoutCodUseCase){
        this.checkoutCodUseCase=checkoutCodUseCase;
    }

    public RestResponse<CheckoutCodUseCaseResponse> checkout(@Body CheckoutCodUseCaseRequest request,
                                                             @Header(HttpHeaders.AUTHORIZATION) String authorization){
        if(authorization==null){
            return RestResponse.error("Unnauthorized");
        }

        try{
            CheckoutCodUseCaseResponse response = checkoutCodUseCase.execute(authorization,request);
            return RestResponse.success(response);
        }catch(Exception e){
            return RestResponse.error("Internal error"+e.getMessage());
        }
    }
}
