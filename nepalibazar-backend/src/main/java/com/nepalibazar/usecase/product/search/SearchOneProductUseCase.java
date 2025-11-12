package com.nepalibazar.usecase.product.search;

import com.nepalibazar.convertor.ProductConvertor;
import com.nepalibazar.entity.ProductEntity;
import com.nepalibazar.repository.ProductRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class SearchOneProductUseCase {

    public final ProductRepository productRepository;

    @Inject
    public SearchOneProductUseCase(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public List<SearchAllProductUseCaseResponse> execute(SearchOneProductUseCaseRequest request) {
        Optional<ProductEntity> product= productRepository.findById(request.productId());
       return product.stream()
               .map(ProductConvertor::toSearchAllProductUseCase)
               .collect(Collectors.toList());
    }

}
