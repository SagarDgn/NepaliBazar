package com.nepalibazar.usecase.product.delete;

import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.repository.ProductRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class DeleteProductUseCase  {

    public final ProductRepository productRepository;

    @Inject
    public DeleteProductUseCase(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public DeleteProductUseCaseResponse execute(Integer id){
        if(!productRepository.existsById(id)){
            throw new RuntimeException("Product not found");

        }else{
            productRepository.deleteById(id);
            return new DeleteProductUseCaseResponse(id, "Product deleted sucessfully");
        }
    }
}
