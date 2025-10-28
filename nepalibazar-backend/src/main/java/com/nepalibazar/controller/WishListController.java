package com.nepalibazar.controller;

import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.usecase.wishlist.add.AddWishListUseCase;
import com.nepalibazar.usecase.wishlist.add.AddWishListUseCaseRequest;
import com.nepalibazar.usecase.wishlist.add.AddWishListUseCaseResponse;
import com.nepalibazar.usecase.wishlist.get.GetWishlistUseCase;
import com.nepalibazar.usecase.wishlist.get.GetWishlistUseCaseResponse;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

@Controller("/api/v1")
public class WishListController {

    public final AddWishListUseCase addWishListUseCase;
    public final GetWishlistUseCase getWishlistUseCase;

    @Inject
    public WishListController(AddWishListUseCase addWishListUseCase,
                              GetWishlistUseCase getWishlistUseCase) {
        this.addWishListUseCase = addWishListUseCase;
        this.getWishlistUseCase=getWishlistUseCase;
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

    @Get("/wishlist")
    public RestResponse<GetWishlistUseCaseResponse> getWishlist(
            @Header(HttpHeaders.AUTHORIZATION) String authorization
    ) {
        if (authorization == null) {
            return RestResponse.error("Unauthorized: missing token");
        }

        GetWishlistUseCaseResponse response = getWishlistUseCase.execute(authorization);
        return RestResponse.success(response);
    }
}
