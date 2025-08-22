package com.nepalibazar.controller;

import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.usecase.user.authuser.AuthenticateUserUseCase;
import com.nepalibazar.usecase.user.authuser.AuthenticateUserUseCaseRequest;
import com.nepalibazar.usecase.user.authuser.AuthenticateUserUseCaseResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/api/auth/user")
public class AuthenticateUserController {
    private final AuthenticateUserUseCase authenticateUseCase;

    @Inject
    public AuthenticateUserController(AuthenticateUserUseCase authenticateUseCase){
        this.authenticateUseCase=authenticateUseCase;
    }

    @Post("/login")
    public RestResponse<AuthenticateUserUseCaseResponse> authUser(@Body AuthenticateUserUseCaseRequest payload){
        try {
            AuthenticateUserUseCaseResponse response= authenticateUseCase.execute(payload);
            return RestResponse.success(response);
        }catch (Exception e){
            return RestResponse.error( e.getLocalizedMessage());
        }

    }
}
