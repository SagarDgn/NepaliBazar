package com.nepalibazar.controller;

import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.usecase.cart.add.AddCartItemUseCase;
import com.nepalibazar.usecase.cart.add.AddCartItemUseCaseRequest;
import com.nepalibazar.usecase.cart.add.AddCartItemUseCaseResponse;
import com.nepalibazar.usecase.cart.get.GetCartItemsUseCase;
import com.nepalibazar.usecase.cart.get.GetUserCartItemUseCaseResponse;
import com.nepalibazar.usecase.cart.remove.RemoveCartUseCase;
import com.nepalibazar.usecase.cart.remove.RemoveCartUseCaseRequest;
import com.nepalibazar.usecase.cart.remove.RemoveCartUseCaseResponse;
import com.nepalibazar.usecase.cart.removeall.ClearCartUseCase;
import com.nepalibazar.usecase.cart.removeall.ClearCartUseCaseResponse;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;


@Controller("/api/v1")
public class CartController {

    public final AddCartItemUseCase addCartItemUseCase;
    public final RemoveCartUseCase removeCartUseCase;
    public final GetCartItemsUseCase getCartItemsUseCase;
    public final ClearCartUseCase clearCartUseCase;

    @Inject
    public CartController(AddCartItemUseCase addCartItemUseCase,
                          RemoveCartUseCase removeCartUseCase,
                          GetCartItemsUseCase getCartItemsUseCase,
                          ClearCartUseCase clearCartUseCase){
        this.addCartItemUseCase=addCartItemUseCase;
        this.removeCartUseCase=removeCartUseCase;
        this.getCartItemsUseCase=getCartItemsUseCase;
        this.clearCartUseCase=clearCartUseCase;
    }


    @Post("/cart/add")
    public RestResponse<AddCartItemUseCaseResponse> add(@Body AddCartItemUseCaseRequest request,
                                                        @Header(HttpHeaders.AUTHORIZATION) String authorization){
        if(authorization==null){
            return RestResponse.error("Unauthorized");
        }
        try{
            AddCartItemUseCaseResponse response= addCartItemUseCase.execute(authorization,request);
            return RestResponse.success(response);
        }catch (Exception e){
            return RestResponse.error(e.getMessage());
        }
    }

    @Put("/cart/remove/item")
    public RestResponse<RemoveCartUseCaseResponse> remove(@Header(HttpHeaders.AUTHORIZATION) String authorization,
                                                         @Body RemoveCartUseCaseRequest request){
        if(authorization==null){
            return RestResponse.error("Unauthorized");
        }
        System.out.println("Inside cart remove");
        try {
            RemoveCartUseCaseResponse response = removeCartUseCase.execute(authorization, request);
            return RestResponse.success(response);
        }catch (Exception e){
            return RestResponse.error(e.getMessage());
        }
    }

    @Get("/mycart/items")
    public RestResponse<GetUserCartItemUseCaseResponse> getCart(@Header(HttpHeaders.AUTHORIZATION)
                                                           String authorization){
        if(authorization==null){
            return RestResponse.error("Unauthorized");
        }
        try{
            GetUserCartItemUseCaseResponse response= getCartItemsUseCase.execute(authorization);
            return RestResponse.success(response);
        }catch (Exception e){
            e.printStackTrace();
            return RestResponse.error(e.getMessage());
        }
    }

    @Delete("/cart/clear")
    public RestResponse<ClearCartUseCaseResponse> removeAll(@Header(HttpHeaders.AUTHORIZATION)
                                                                String authorization){
        if(authorization==null){
            return RestResponse.error("Unauthorized");
        }
        try{
           ClearCartUseCaseResponse response= clearCartUseCase.execute(authorization);
           return RestResponse.success(response);
        }catch (Exception e){
            e.printStackTrace();
            return RestResponse.error(e.getMessage());
        }
    }
}
