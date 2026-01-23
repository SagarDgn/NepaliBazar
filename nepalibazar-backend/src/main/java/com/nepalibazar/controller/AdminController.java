package com.nepalibazar.controller;

import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.usecase.admin.getSellers.GetSellersUseCase;
import com.nepalibazar.usecase.admin.getUsers.GetUsersUseCase;
import com.nepalibazar.usecase.seller.search.SearchSellerUseCaseResponse;
import com.nepalibazar.usecase.user.search.SearchAllUserUcResponse;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import jakarta.inject.Inject;

import java.util.List;

@Controller("api/v1")
public class AdminController {

    private final GetUsersUseCase getUsersUseCase;
    private final GetSellersUseCase getSellersUseCase;

    @Inject
    public AdminController(GetUsersUseCase getUsersUseCase,
                           GetSellersUseCase getSellersUseCase){
        this.getUsersUseCase=getUsersUseCase;
        this.getSellersUseCase=getSellersUseCase;
    }

    @Get("/myusers")
    public RestResponse<List<SearchAllUserUcResponse>> getUsers(@Header(HttpHeaders.AUTHORIZATION) String authorization) {
        try {
            if (authorization == null || authorization.isBlank()) {
                return RestResponse.error("Unauthorized");
            }
            List<SearchAllUserUcResponse> response = getUsersUseCase.execute();
            return RestResponse.success(response);

        } catch (Exception e) {
            return RestResponse.error(e.getLocalizedMessage());
        }
    }

        @Get("/mysellers")
        public RestResponse<List<SearchSellerUseCaseResponse>> getSellers(@Header(HttpHeaders.AUTHORIZATION) String authorization){
            try {
                if (authorization == null || authorization.isBlank()) {
                    return RestResponse.error("Unauthorized");
                }
                List<SearchSellerUseCaseResponse> response = getSellersUseCase.execute();
                return RestResponse.success(response);

            } catch (Exception e) {
                return RestResponse.error(e.getLocalizedMessage());
            }
        }


}
