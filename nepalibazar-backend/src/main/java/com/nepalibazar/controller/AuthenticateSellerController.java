package com.nepalibazar.controller;

import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.usecase.seller.authgoogle.AuthenticateGoogleSellerUseCase;
import com.nepalibazar.usecase.seller.authgoogle.AuthenticateGoogleSellerUseCaseRequest;
import com.nepalibazar.usecase.seller.authgoogle.AuthenticateGoogleSellerUseCaseResponse;
import com.nepalibazar.usecase.seller.authseller.AuthenticateSellerUseCase;
import com.nepalibazar.usecase.seller.authseller.AuthenticateSellerUseCaseRequest;
import com.nepalibazar.usecase.seller.authseller.AuthenticateSellerUseCaseResponse;
import com.nepalibazar.usecase.seller.logout.LogoutSellerUseCase;
import com.nepalibazar.usecase.seller.logout.LogoutSellerUseCaseResponse;
import com.nepalibazar.usecase.user.authgoogle.AuthenticateGoogleUserUseCaseRequest;
import com.nepalibazar.usecase.user.authgoogle.AuthenticateGoogleUserUseCaseResponse;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/api/v1")
public class AuthenticateSellerController {

    public final AuthenticateSellerUseCase authenticateSellerUseCase;
    public final LogoutSellerUseCase logoutSellerUseCase;
    public final AuthenticateGoogleSellerUseCase authenticateGoogleSellerUseCase;

    @Inject
    public AuthenticateSellerController(AuthenticateSellerUseCase authenticateSellerUseCase,
                                        LogoutSellerUseCase logoutSellerUseCase,
                                        AuthenticateGoogleSellerUseCase authenticateGoogleSellerUseCase) {
        this.authenticateSellerUseCase = authenticateSellerUseCase;
        this.logoutSellerUseCase = logoutSellerUseCase;
        this.authenticateGoogleSellerUseCase=authenticateGoogleSellerUseCase;
    }

    @Post("/auth/seller/login")
    public RestResponse<AuthenticateSellerUseCaseResponse> authSeller(@Body AuthenticateSellerUseCaseRequest payload) {
        try {
            AuthenticateSellerUseCaseResponse response = authenticateSellerUseCase.execute(payload);
            return RestResponse.success(response);
        } catch (Exception e) {
            return RestResponse.error(e.getLocalizedMessage());

        }
    }

    @Post("/auth/seller/logout")
    public RestResponse<LogoutSellerUseCaseResponse> logoutSeller(@Header(HttpHeaders.AUTHORIZATION)String authorization){
        try{
        LogoutSellerUseCaseResponse response= logoutSellerUseCase.execute(authorization);
        return RestResponse.success(response);
    }catch (Exception e){
            return RestResponse.error(e.getLocalizedMessage());
        }
    }

    @Post("/seller/google/login")
    public RestResponse<AuthenticateGoogleSellerUseCaseResponse> loginGoogle(@Body AuthenticateGoogleSellerUseCaseRequest request){
        try{
            var response= authenticateGoogleSellerUseCase.execute(request);
            return RestResponse.success(response);
        }catch (Exception e){
            e.printStackTrace();
            return RestResponse.error("Some exception occured");
        }
    }
}