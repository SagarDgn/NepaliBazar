package com.nepalibazar.usecase.product.delete;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.entity.SellerEntity;
import com.nepalibazar.repository.ProductRepository;
import com.nepalibazar.repository.SellerRepository;
import com.nepalibazar.usecase.wishlist.get.GetWishlistUseCaseResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class DeleteProductUseCase  {

    public final ProductRepository productRepository;
    public final SellerRepository sellerRepository;

    @Inject
    public DeleteProductUseCase(ProductRepository productRepository,SellerRepository sellerRepository){
        this.productRepository=productRepository;
        this.sellerRepository=sellerRepository;
    }

    public DeleteProductUseCaseResponse execute(Integer id,String token){

        if (token == null || token.isBlank()) {
            return new DeleteProductUseCaseResponse(-1, "Unauthorized: missing token");
        }

        String jwt = token.replace("Bearer", "").trim();
        String email = JwtUtils.getEmailFromToken(jwt);
        String role= JwtUtils.getRoleFromToken(jwt);

        if(!"SELLER".equalsIgnoreCase(role)){
            return new DeleteProductUseCaseResponse(-1,"Unauthorized");
        }
        if(!productRepository.existsById(id)){
            throw new RuntimeException("Product not found");

        }
        Optional<SellerEntity> seller= sellerRepository.findByEmailPhone(email);
        if(seller.isEmpty()){
            return new DeleteProductUseCaseResponse(-1, "Seller not found");
        }
        else{
            productRepository.deleteById(id);
            return new DeleteProductUseCaseResponse(id, "Product deleted sucessfully");
        }
    }
}
