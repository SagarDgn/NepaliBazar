package com.nepalibazar.usecase.admin.getSellers;

import com.nepalibazar.convertor.SellerConvertor;
import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.entity.SellerEntity;
import com.nepalibazar.repository.SellerRepository;
import com.nepalibazar.usecase.seller.search.SearchSellerUseCaseResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class GetSellersUseCase {

    private final SellerRepository sellerRepository;

    @Inject
    public GetSellersUseCase (SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
    }
    public List<SearchSellerUseCaseResponse> execute() {
        try {
            List<SellerEntity> sellers = sellerRepository.findAll();
            return sellers.stream()
                    .map(SellerConvertor::toSearchSellerUseCaseResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Unexpected happened"+e.getLocalizedMessage());
        }
    }
}
