package com.nepalibazar.controller;

import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.usecase.wishlist.add.AddWishListUseCase;
import com.nepalibazar.usecase.wishlist.add.AddWishListUseCaseRequest;
import com.nepalibazar.usecase.wishlist.add.AddWishListUseCaseResponse;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/api/v1")
public class WishListController {

    public final AddWishListUseCase addWishListUseCase;

    @Inject
    public WishListController(AddWishListUseCase addWishListUseCase) {
        this.addWishListUseCase = addWishListUseCase;
    }

    @Post("/wishlist/add")
    public RestResponse<AddWishListUseCaseResponse> addWishListUseCase(@Header(HttpHeaders.AUTHORIZATION) String authorization,
            @Body AddWishListUseCaseRequest request) {
        System.out.println("Test here " + authorization);
        if (authorization == null ) {
            return RestResponse.error("Unauthorized");
        }
        System.out.println("Here test ");
        try{
            AddWishListUseCaseResponse response= addWishListUseCase.execute(authorization,request);
            return RestResponse.success(response);
        }catch(Exception e){

            return RestResponse.error(e.getMessage());
        }
    }
}
