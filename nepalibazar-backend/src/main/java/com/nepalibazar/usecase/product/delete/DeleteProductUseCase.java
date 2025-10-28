package com.nepalibazar.usecase.product.delete;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.repository.ProductRepository;
import com.nepalibazar.usecase.wishlist.get.GetWishlistUseCaseResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class DeleteProductUseCase  {

    public final ProductRepository productRepository;

    @Inject
    public DeleteProductUseCase(ProductRepository productRepository){
        this.productRepository=productRepository;
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

        }else{
            productRepository.deleteById(id);
            return new DeleteProductUseCaseResponse(id, "Product deleted sucessfully");
        }
    }
}
