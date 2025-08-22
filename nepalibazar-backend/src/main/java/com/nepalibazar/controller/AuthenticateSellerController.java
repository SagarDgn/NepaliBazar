package com.nepalibazar.controller;

import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.usecase.seller.authseller.AuthenticateSellerUseCase;
import com.nepalibazar.usecase.seller.authseller.AuthenticateSellerUseCaseRequest;
import com.nepalibazar.usecase.seller.authseller.AuthenticateSellerUseCaseResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/api/auth/seller")
public class AuthenticateSellerController {

    public final AuthenticateSellerUseCase authenticateSellerUseCase;

    @Inject
    public AuthenticateSellerController(AuthenticateSellerUseCase authenticateSellerUseCase) {
        this.authenticateSellerUseCase = authenticateSellerUseCase;
    }

    @Post("/login")
    public RestResponse<AuthenticateSellerUseCaseResponse> authSeller(@Body AuthenticateSellerUseCaseRequest payload) {
        try {
            AuthenticateSellerUseCaseResponse response = authenticateSellerUseCase.execute(payload);
            return RestResponse.success(response);
        } catch (Exception e) {
            return RestResponse.error(e.getLocalizedMessage());

        }
    }
}