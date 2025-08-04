package com.nepalibazar.convertor;

import com.nepalibazar.entity.ProductEntity;
import com.nepalibazar.usecase.product.add.AddProductUseCaseRequest;
import com.nepalibazar.usecase.product.search.SearchAllProductUseCaseResponse;

public class ProductConvertor {
    private ProductConvertor(){}

    public static ProductEntity toEntity(AddProductUseCaseRequest product){
        ProductEntity productEntity= new ProductEntity();
        productEntity.setProductName(product.productName());
        productEntity.setAboutProduct(product.aboutProduct());
        productEntity.setPrice(product.price());
        productEntity.setDiscount(product.discount());
        productEntity.setImage(product.image());
        productEntity.setQuantity(product.quantity());

        return productEntity;
    }

    public static SearchAllProductUseCaseResponse toSearchAllProductUseCase(ProductEntity product){
        return new SearchAllProductUseCaseResponse(
                product.getId(),
                product.getProductName(),
                product.getAboutProduct(),
                product.getImage(),
                product.getPrice(),
                product.getDiscount(),
                product.getQuantity()
        );
    }

}
