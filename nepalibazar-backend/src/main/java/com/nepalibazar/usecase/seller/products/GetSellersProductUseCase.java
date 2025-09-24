package com.nepalibazar.usecase.seller.products;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.entity.ProductEntity;
import com.nepalibazar.repository.ProductRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class GetSellersProductUseCase {
    public final ProductRepository productRepository;

    @Inject
    public GetSellersProductUseCase(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public List<GetSellerProductUseCaseResponse> execute(String token){
        String sellerEmail= JwtUtils.getEmailFromToken(token);
        List<ProductEntity> productEntitiy= productRepository.findBySeller_EmailPhone(sellerEmail);

        return productEntitiy.stream().
                map(product->new GetSellerProductUseCaseResponse(
                        product.getId(),
                        product.getProductName(),
                        product.getAboutProduct(),
                        product.getImage(),
                        product.getPrice(),
                        product.getDiscount(),
                        product.getQuantity()
                ))
                .toList();
    }


}
