package com.nepalibazar.controller;

import com.nepalibazar.controller.requestpayload.AddProductRequestPayload;
import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.entity.SellerEntity;
import com.nepalibazar.repository.SellerRepository;
import com.nepalibazar.usecase.product.add.AddProductUseCase;
import com.nepalibazar.usecase.product.add.AddProductUseCaseRequest;
import com.nepalibazar.usecase.product.add.AddProductUseCaseResponse;
import com.nepalibazar.usecase.product.delete.DeleteProductUseCase;
import com.nepalibazar.usecase.product.delete.DeleteProductUseCaseResponse;
import com.nepalibazar.usecase.product.search.SearchAllProductUseCase;
import com.nepalibazar.usecase.product.search.SearchAllProductUseCaseResponse;
import com.nepalibazar.usecase.product.update.UpdateProductUseCase;
import com.nepalibazar.usecase.product.update.UpdateProductUseCaseRequest;
import com.nepalibazar.usecase.product.update.UpdateProductUseCaseResponse;
import io.micronaut.http.HttpHeaders;

import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/api/v1")
public class ProductController {

    public final AddProductUseCase addProductUseCase;
    public final SearchAllProductUseCase searchAllProductUseCase;
    public final UpdateProductUseCase updateProductUseCase;
    public final DeleteProductUseCase deleteProductUseCase;
    public final SellerRepository sellerRepository;

    @Inject
    public ProductController(AddProductUseCase addProductUseCase,
                             SearchAllProductUseCase searchAllProductUseCase,
                             UpdateProductUseCase updateProductUseCase,
                             DeleteProductUseCase deleteProductUseCase,
                             SellerRepository sellerRepository){
        this.addProductUseCase=addProductUseCase;
        this.searchAllProductUseCase=searchAllProductUseCase;
        this.updateProductUseCase=updateProductUseCase;
        this.deleteProductUseCase=deleteProductUseCase;
        this.sellerRepository=sellerRepository;
    }

    @Post("/product/add")
    public RestResponse<AddProductUseCaseResponse> postProduct(
            @Body AddProductRequestPayload request,
            @Header(HttpHeaders.AUTHORIZATION) String authorization
    ) {
        System.out.println("Inside add product");
        try {
            System.out.println(authorization);
            String token = authorization.replace("Bearer ", "").trim();
            String emailOrPhone = JwtUtils.getEmailFromToken(token);// sub = email/phone
            System.out.println(emailOrPhone);
            String role= JwtUtils.getRoleFromToken(token);

            System.out.println("Received token"+ token);

            if(!"SELLER".equalsIgnoreCase(role)){
                return RestResponse.error("Unauthorized");
            }



            AddProductUseCaseRequest addProductUseCaseRequest = new AddProductUseCaseRequest(
                    request.productName(),
                    request.aboutProduct(),
                    request.price(),
                    request.discount(),
                    request.image(),
                    request.quantity(),
                    emailOrPhone
            );

            AddProductUseCaseResponse response = addProductUseCase.execute(addProductUseCaseRequest);
            return RestResponse.success(response);

        } catch (Exception e) {
            return RestResponse.error("Cannot insert the product: " + e.getMessage());
        }
    }


    @Get("/product/get")
    public RestResponse<List<SearchAllProductUseCaseResponse>> getAllProduct(){
        try{
            List<SearchAllProductUseCaseResponse> products= searchAllProductUseCase.execute();
            return RestResponse.success(products);
        }catch (Exception e){
            return RestResponse.error("Product not found"+ e.getMessage());
        }
    }

    @Put("/product/update/{id}")
    public RestResponse<UpdateProductUseCaseResponse> updateProduct(@PathVariable Integer id,
                                                                    @Body UpdateProductUseCaseRequest request){
        try{
            var finalProduct = new UpdateProductUseCaseRequest(
                    id,
                    request.productName(),
                    request.aboutProduct(),
                    request.price(),
                    request.image(),
                    request.quantity(),
                    request.discount()
            );
            UpdateProductUseCaseResponse response= updateProductUseCase.execute(finalProduct);
            return RestResponse.success(response);

        }catch(Exception e){
            return RestResponse.error("Cannot update product "+ e.getMessage());

        }

    }

    @Delete("/product/delete/{id}")
    public RestResponse<DeleteProductUseCaseResponse> deleteProduct(@PathVariable Integer id,
                                                                    @Header(HttpHeaders.AUTHORIZATION)String authorization){

        if(authorization==null){
            return RestResponse.error("Unauthorized");
        }
        System.out.println("Received" + authorization);
        try{
            DeleteProductUseCaseResponse response= deleteProductUseCase.execute(id,authorization);
            return RestResponse.success(response);
        }catch(Exception e){
            return RestResponse.error("Cannot delete product "+ e.getMessage());

        }
    }
}
