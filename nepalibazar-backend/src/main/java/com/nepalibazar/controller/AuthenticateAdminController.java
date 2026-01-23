package com.nepalibazar.controller;

import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.usecase.admin.authadmin.AuthenticateAdminUseCase;
import com.nepalibazar.usecase.admin.authadmin.AuthenticateAdminUseCaseRequest;
import com.nepalibazar.usecase.admin.authadmin.AuthenticateAdminUseCaseResponse;
import com.nepalibazar.usecase.admin.logout.LogoutAdminUseCase;
import com.nepalibazar.usecase.admin.logout.LogoutAdminUseCaseResponse;
import com.nepalibazar.usecase.user.logout.LogoutUserUseCaseResponse;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/api/v1")
public class AuthenticateAdminController {

    private final AuthenticateAdminUseCase authenticateAdminUseCase;
    private final LogoutAdminUseCase logoutAdminUseCase;

    @Inject
    public AuthenticateAdminController(AuthenticateAdminUseCase authenticateAdminUseCase,
                                       LogoutAdminUseCase logoutAdminUseCase) {
        this.authenticateAdminUseCase = authenticateAdminUseCase;
        this.logoutAdminUseCase = logoutAdminUseCase;
    }

    @Post("/login")
    public RestResponse<AuthenticateAdminUseCaseResponse> login(@Body AuthenticateAdminUseCaseRequest payload) {
        try {
            var response = authenticateAdminUseCase.execute(payload);
            return RestResponse.success(response);
        } catch (Exception e) {
            return RestResponse.error("Unexpected happened" + e.getLocalizedMessage());
        }
    }

    @Post("/logout")
    public RestResponse<LogoutAdminUseCaseResponse> logout(@Header(HttpHeaders.AUTHORIZATION) String authorization) {
       try{
           if(authorization==null || authorization.isBlank()){
               return RestResponse.error("Unauthorized");
           }
           LogoutAdminUseCaseResponse response= logoutAdminUseCase.execute(authorization);
           return RestResponse.success(response);

       }catch (Exception e){
           return RestResponse.error(e.getLocalizedMessage());

       }
    }
}
