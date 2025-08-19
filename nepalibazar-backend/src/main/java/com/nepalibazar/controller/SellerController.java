package com.nepalibazar.controller;

import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.usecase.otp.SendOtpUseCase;
import com.nepalibazar.usecase.otp.SendOtpUseCaseRequest;
import com.nepalibazar.usecase.otp.SendOtpUseCaseResponse;
import com.nepalibazar.usecase.seller.add.AddSellerUseCase;
import com.nepalibazar.usecase.seller.add.AddSellerUseCaseRequest;
import com.nepalibazar.usecase.seller.add.AddSellerUseCaseResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/api/seller")
public class SellerController {

    public final AddSellerUseCase addSellerUseCase;
    public final SendOtpUseCase sendOtpUseCase;

    @Inject
    public SellerController(AddSellerUseCase addSellerUseCase,
                            SendOtpUseCase sendOtpUseCase){
        this.addSellerUseCase= addSellerUseCase;
        this.sendOtpUseCase=sendOtpUseCase;
    }

    @Post("/add")
    public RestResponse<AddSellerUseCaseResponse> addSeller(@Body AddSellerUseCaseRequest request){

        try{
            AddSellerUseCaseResponse response= addSellerUseCase.execute(request);
            if(response.code()!=0){
                return new RestResponse<>("-1", response.message(), null);
            }
            return RestResponse.success(response);
        }catch (Exception e){
            return RestResponse.error("Fail to register seller "+e.getMessage());
        }

    }

    @Post("/sent-otp")
    public RestResponse<SendOtpUseCaseResponse> sentOtp(@Body SendOtpUseCaseRequest request){
        try{
            SendOtpUseCaseResponse response= sendOtpUseCase.execute(request.email());
            return RestResponse.success(response);
        }catch(Exception e){
            return RestResponse.error("Fail to send otp." +e.getMessage());
        }
    }
}
