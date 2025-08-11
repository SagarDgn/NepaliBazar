package com.nepalibazar.usecase.product.update;

import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.repository.ProductRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class UpdateProductUseCase implements UseCase<UpdateProductUseCaseRequest, UpdateProductUseCaseResponse> {

    public final ProductRepository productRepository;

    @Inject
    public UpdateProductUseCase(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public UpdateProductUseCaseResponse execute(UpdateProductUseCaseRequest request){
        var existingProduct= productRepository.findById(request.id())
                .orElseThrow(()-> new RuntimeException("Product not found with "+request.id()));

        existingProduct.setProductName(request.productName());
        existingProduct.setAboutProduct(request.aboutProduct());
        existingProduct.setPrice(request.price());
        existingProduct.setDiscount(request.discount());
        existingProduct.setImage(request.image());
        existingProduct.setQuantity(request.quantity());

        var updatedProduct= productRepository.update(existingProduct);
        return new UpdateProductUseCaseResponse(updatedProduct.getId(), "Product updated successfully");

    }
}
