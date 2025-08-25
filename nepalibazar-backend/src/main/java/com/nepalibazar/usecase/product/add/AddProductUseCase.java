package com.nepalibazar.usecase.product.add;

import com.nepalibazar.convertor.ProductConvertor;
import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.entity.SellerEntity;
import com.nepalibazar.repository.ProductRepository;
import com.nepalibazar.repository.SellerRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class AddProductUseCase implements UseCase<AddProductUseCaseRequest, AddProductUseCaseResponse> {



    public final ProductRepository productRepository;
    public final SellerRepository sellerRepository;

    @Inject
    public AddProductUseCase(ProductRepository productRepository,
                             SellerRepository sellerRepository){
        this.productRepository=productRepository;
        this.sellerRepository=sellerRepository;
    }

    public AddProductUseCaseResponse execute(AddProductUseCaseRequest request){
        try {

            SellerEntity sellerEntity= sellerRepository.findByEmailPhone(request.sellerEmail())
                    .orElseThrow(()->new RuntimeException("Seller not found"));
            var productEntity= ProductConvertor.toEntity(request, sellerEntity);
            productEntity.setSeller(sellerEntity);
            var saved= productRepository.save(productEntity);
            return new AddProductUseCaseResponse(saved.getId(), "product added successfully");
        }catch (Exception e){
            throw new RuntimeException("Cannot add product: "+ e.getMessage(),e);
        }
    }
}
