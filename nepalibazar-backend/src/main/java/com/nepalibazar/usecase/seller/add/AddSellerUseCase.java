package com.nepalibazar.usecase.seller.add;

import com.nepalibazar.convertor.SellerConvertor;
import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.repository.SellerRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class AddSellerUseCase implements UseCase<AddSellerUseCaseRequest, AddSellerUseCaseResponse> {

    public final SellerRepository sellerRepository;

    @Inject
    public AddSellerUseCase(SellerRepository sellerRepository){
        this.sellerRepository=sellerRepository;
    }

    public AddSellerUseCaseResponse execute(AddSellerUseCaseRequest request){
        try{
            var sellerEntity= SellerConvertor.toEntity(request);
            var saved= sellerRepository.save(sellerEntity);
            return new  AddSellerUseCaseResponse(null, sellerEntity.getId(), "Successfuln registration for seller");
        }catch (Exception e){
            throw new RuntimeException("Fail to register as seller "+e.getMessage());
        }
    }
}
