package com.nepalibazar.convertor;

import com.nepalibazar.core.security.SecurityUtils;
import com.nepalibazar.domain.USER_ROLE;
import com.nepalibazar.entity.SellerEntity;
import com.nepalibazar.usecase.seller.add.AddSellerUseCaseRequest;
import com.nepalibazar.usecase.seller.search.SearchSellerUseCaseResponse;

public class SellerConvertor {

    private SellerConvertor() {
    }

    public static SellerEntity toEntity(AddSellerUseCaseRequest seller) {
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setSellerName(seller.sellerName());
        sellerEntity.setEmailPhone(seller.emailPhone());
        sellerEntity.setPassword(SecurityUtils.hashPassword(seller.password()));
        sellerEntity.setLocation(seller.location());
        sellerEntity.setProductCategory(seller.productCategory());
        sellerEntity.setRole(USER_ROLE.valueOf("SELLER"));

        return sellerEntity;
    }

    public static SearchSellerUseCaseResponse toSearchSellerUseCaseResponse(SellerEntity sellerEntity) {
        return new SearchSellerUseCaseResponse(
                sellerEntity.getId(),
                sellerEntity.getSellerName(),
                sellerEntity.getEmailPhone(),
                sellerEntity.getLocation(),
                sellerEntity.getRole(),
                sellerEntity.getProductCategory(),
                sellerEntity.getRating()
        );
    }
    }
