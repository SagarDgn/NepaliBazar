package com.nepalibazar.controller;

import com.nepalibazar.core.response.RestResponse;
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
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/api/product")
public class ProductController {

    public final AddProductUseCase addProductUseCase;
    public final SearchAllProductUseCase searchAllProductUseCase;
    public final UpdateProductUseCase updateProductUseCase;
    public final DeleteProductUseCase deleteProductUseCase;

    @Inject
    public ProductController(AddProductUseCase addProductUseCase,
                             SearchAllProductUseCase searchAllProductUseCase,
                             UpdateProductUseCase updateProductUseCase,
                             DeleteProductUseCase deleteProductUseCase){
        this.addProductUseCase=addProductUseCase;
        this.searchAllProductUseCase=searchAllProductUseCase;
        this.updateProductUseCase=updateProductUseCase;
        this.deleteProductUseCase=deleteProductUseCase;
    }

    @Post("/add")
    public RestResponse<AddProductUseCaseResponse> postProduct(@Body AddProductUseCaseRequest request){
        try{
            AddProductUseCaseResponse response= addProductUseCase.execute(request);
            return RestResponse.success(response);

        }catch(Exception e){
            return RestResponse.error("Cannot insert the product"+ e.getMessage());
        }
    }

    @Get("/get")
    public RestResponse<List<SearchAllProductUseCaseResponse>> getAllProduct(){
        try{
            List<SearchAllProductUseCaseResponse> products= searchAllProductUseCase.execute();
            return RestResponse.success(products);
        }catch (Exception e){
            return RestResponse.error("Product not found"+ e.getMessage());
        }
    }

    @Put("/update/{id}")
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

    @Delete("/delete/{id}")
    public RestResponse<DeleteProductUseCaseResponse> deleteProduct(@PathVariable Integer id){
        try{
            DeleteProductUseCaseResponse response= deleteProductUseCase.execute(id);
            return RestResponse.success(response);
        }catch(Exception e){
            return RestResponse.error("Cannot delete product "+ e.getMessage());

        }
    }
}
