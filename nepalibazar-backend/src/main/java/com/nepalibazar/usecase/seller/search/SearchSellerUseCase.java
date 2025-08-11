package com.nepalibazar.usecase.seller.search;

import com.nepalibazar.convertor.SellerConvertor;
import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.entity.SellerEntity;
import com.nepalibazar.repository.SellerRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class SearchSellerUseCase  {

    public final SellerRepository sellerRepository;

    @Inject
    public SearchSellerUseCase (SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
    }
    public List<SearchSellerUseCaseResponse> execute(){
        List<SellerEntity> sellers = sellerRepository.findAll();
        return sellers.stream()
                .map(SellerConvertor::toSearchSellerUseCaseResponse)
                .collect(Collectors.toList());
    }

}
