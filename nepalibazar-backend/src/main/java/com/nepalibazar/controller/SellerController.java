package com.nepalibazar.controller;

import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.usecase.otp.SendOtpUseCase;
import com.nepalibazar.usecase.otp.SendOtpUseCaseRequest;
import com.nepalibazar.usecase.otp.SendOtpUseCaseResponse;
import com.nepalibazar.usecase.seller.add.AddSellerUseCase;
import com.nepalibazar.usecase.seller.add.AddSellerUseCaseRequest;
import com.nepalibazar.usecase.seller.add.AddSellerUseCaseResponse;
import com.nepalibazar.usecase.seller.logout.LogoutSellerUseCase;
import com.nepalibazar.usecase.seller.logout.LogoutSellerUseCaseRequest;
import com.nepalibazar.usecase.seller.logout.LogoutSellerUseCaseResponse;
import com.nepalibazar.usecase.seller.search.SearchSellerUseCase;
import com.nepalibazar.usecase.seller.search.SearchSellerUseCaseResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/api/seller")
public class SellerController {

    public final AddSellerUseCase addSellerUseCase;
    public final SendOtpUseCase sendOtpUseCase;
    public final SearchSellerUseCase searchSellerUseCase;
    public final LogoutSellerUseCase logoutSellerUseCase;

    @Inject
    public SellerController(AddSellerUseCase addSellerUseCase,
                            SendOtpUseCase sendOtpUseCase,
                            SearchSellerUseCase searchSellerUseCase,
                            LogoutSellerUseCase logoutSellerUseCase){
        this.addSellerUseCase= addSellerUseCase;
        this.sendOtpUseCase=sendOtpUseCase;
        this.searchSellerUseCase=searchSellerUseCase;
        this.logoutSellerUseCase=logoutSellerUseCase;
    }

    @Post("/signup")
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
            if(response.id() == -1)
                return new RestResponse<>("-1", response.message(), null);
            return RestResponse.success(response);
        }catch(Exception e){
            return RestResponse.error("Fail to send otp." +e.getMessage());
        }
    }

    @Get("/search")
    public RestResponse<List<SearchSellerUseCaseResponse>> searchSeller(){
        try{
            List<SearchSellerUseCaseResponse> sellers= searchSellerUseCase.execute();
            return RestResponse.success(sellers);
        }catch (Exception e){
            return RestResponse.error("Fail to search seller "+e.getMessage());
        }
    }




}
