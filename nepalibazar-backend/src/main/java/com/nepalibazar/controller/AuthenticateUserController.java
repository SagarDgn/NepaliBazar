package com.nepalibazar.controller;

import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.usecase.user.authuser.AuthenticateUserUseCase;
import com.nepalibazar.usecase.user.authuser.AuthenticateUserUseCaseRequest;
import com.nepalibazar.usecase.user.authuser.AuthenticateUserUseCaseResponse;
import com.nepalibazar.usecase.user.logout.LogoutUserUseCase;
import com.nepalibazar.usecase.user.logout.LogoutUserUseCaseResponse;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/api/v1")
public class AuthenticateUserController {
    private final AuthenticateUserUseCase authenticateUseCase;
    public final LogoutUserUseCase logoutUserUseCase;

    @Inject
    public AuthenticateUserController(AuthenticateUserUseCase authenticateUseCase,
                                      LogoutUserUseCase logoutUserUseCase){
        this.authenticateUseCase=authenticateUseCase;
        this.logoutUserUseCase=logoutUserUseCase;
    }

    @Post("/auth/user/login")
    public RestResponse<AuthenticateUserUseCaseResponse> authUser(@Body AuthenticateUserUseCaseRequest payload){
        System.out.println("Inside auth controller");
        try {
            AuthenticateUserUseCaseResponse response= authenticateUseCase.execute(payload);
            return RestResponse.success(response);
        }catch (Exception e){
            return RestResponse.error( e.getLocalizedMessage());
        }

    }

    @Post("/auth/user/logout")
    public RestResponse<LogoutUserUseCaseResponse> logoutUser(@Header(HttpHeaders.AUTHORIZATION) String authorization){
        try{
            LogoutUserUseCaseResponse response= logoutUserUseCase.execute(authorization);
            return RestResponse.success(response);
        }catch (Exception e){
            return RestResponse.error( e.getLocalizedMessage());

        }
    }
}
