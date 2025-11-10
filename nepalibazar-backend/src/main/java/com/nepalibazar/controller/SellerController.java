package com.nepalibazar.controller;

import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.usecase.otp.SendBuyerOtpUseCase;
import com.nepalibazar.usecase.otp.SendOtpUseCaseRequest;
import com.nepalibazar.usecase.otp.SendOtpUseCaseResponse;
import com.nepalibazar.usecase.otp.SendSellerOtpUseCase;
import com.nepalibazar.usecase.seller.add.AddSellerUseCase;
import com.nepalibazar.usecase.seller.add.AddSellerUseCaseRequest;
import com.nepalibazar.usecase.seller.add.AddSellerUseCaseResponse;
import com.nepalibazar.usecase.seller.logout.LogoutSellerUseCase;
import com.nepalibazar.usecase.seller.products.GetSellerProductUseCaseResponse;
import com.nepalibazar.usecase.seller.products.GetSellersProductUseCase;
import com.nepalibazar.usecase.seller.search.SearchSellerUseCase;
import com.nepalibazar.usecase.seller.search.SearchSellerUseCaseResponse;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/api/v1")
public class SellerController {

    public final AddSellerUseCase addSellerUseCase;
    public final SendSellerOtpUseCase sendSellerOtpUseCase;
    public final SearchSellerUseCase searchSellerUseCase;
    public final LogoutSellerUseCase logoutSellerUseCase;
    public final GetSellersProductUseCase getSellersProductUseCase;

    @Inject
    public SellerController(AddSellerUseCase addSellerUseCase,
                            SendSellerOtpUseCase sendSellerOtpUseCase,
                            SearchSellerUseCase searchSellerUseCase,
                            LogoutSellerUseCase logoutSellerUseCase,
                            GetSellersProductUseCase getSellersProductUseCase){
        this.addSellerUseCase= addSellerUseCase;
        this.sendSellerOtpUseCase=sendSellerOtpUseCase;
        this.searchSellerUseCase=searchSellerUseCase;
        this.logoutSellerUseCase=logoutSellerUseCase;
        this.getSellersProductUseCase=getSellersProductUseCase;
    }

    @Post("/seller/signup")
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

    @Post("/seller/sent-otp")
    public RestResponse<SendOtpUseCaseResponse> sentOtp(@Body SendOtpUseCaseRequest request){
        System.out.println("In seller controller");
        try{
            SendOtpUseCaseResponse response= sendSellerOtpUseCase.execute(request.emailPhone());
            if(response.id() == -1)
                return new RestResponse<>("-1", response.message(), null);
            return RestResponse.success(response);
        }catch(Exception e){
            return RestResponse.error("Fail to send otp." +e.getMessage());
        }
    }

    @Get("/seller/search")
    public RestResponse<List<SearchSellerUseCaseResponse>> searchSeller(){
        try{
            List<SearchSellerUseCaseResponse> sellers= searchSellerUseCase.execute();
            return RestResponse.success(sellers);
        }catch (Exception e){
            return RestResponse.error("Fail to search seller "+e.getMessage());
        }
    }

    @Get("/my/products")
    public RestResponse<List<GetSellerProductUseCaseResponse>> getProduct(@Header(HttpHeaders.AUTHORIZATION) String authorization){
        if(authorization.isBlank()){
            return RestResponse.error("Unauthorized");
        }
        try{
            List<GetSellerProductUseCaseResponse> products= getSellersProductUseCase.execute(authorization);
            return RestResponse.success(products);
        }catch(Exception e){
            return RestResponse.error("Unauthorized" +e.getMessage());
        }
    }




}
