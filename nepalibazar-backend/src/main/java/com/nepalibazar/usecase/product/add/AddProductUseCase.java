package com.nepalibazar.usecase.product.add;

import com.nepalibazar.convertor.ProductConvertor;
import com.nepalibazar.repository.ProductRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class AddProductUseCase {

    public final ProductRepository productRepository;

    @Inject
    public AddProductUseCase(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public AddProductUseCaseResponse execute(AddProductUseCaseRequest request){
        try {
            var productEntity= ProductConvertor.toEntity(request);
            var saved= productRepository.save(productEntity);
            return new AddProductUseCaseResponse(saved.getId(), "product added successfully");
        }catch (Exception e){
            throw new RuntimeException("Cannot add product: "+ e.getMessage(),e);
        }
    }
}
