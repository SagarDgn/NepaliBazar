package com.nepalibazar.controller;

import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.usecase.seller.add.AddSellerUseCase;
import com.nepalibazar.usecase.seller.add.AddSellerUseCaseRequest;
import com.nepalibazar.usecase.seller.add.AddSellerUseCaseResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import jakarta.inject.Inject;

@Controller("/api/seller")
public class SellerController {

    public final AddSellerUseCase addSellerUseCase;

    @Inject
    public SellerController(AddSellerUseCase addSellerUseCase){
        this.addSellerUseCase= addSellerUseCase;
    }

    public RestResponse<AddSellerUseCaseResponse> addSeller(@Body AddSellerUseCaseRequest request){

        try{
            AddSellerUseCaseResponse response= addSellerUseCase.execute(request);
            return RestResponse.success(response);
        }catch (Exception e){
            return RestResponse.error("Fail to register seller "+e.getMessage());
        }

    }
}
