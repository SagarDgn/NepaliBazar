package com.nepalibazar.usecase.product.search;
import com.nepalibazar.convertor.ProductConvertor;
import com.nepalibazar.entity.ProductEntity;
import com.nepalibazar.repository.ProductRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class SearchAllProductUseCase {

    public final ProductRepository productRepository;

    @Inject
    public SearchAllProductUseCase(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public List<SearchAllProductUseCaseResponse> execute(){
        List<ProductEntity> product= productRepository.findAll();
        return product.stream()
                .map(ProductConvertor::toSearchAllProductUseCase)
                .collect(Collectors.toList());
    }
}
