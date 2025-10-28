package com.nepalibazar.usecase.wishlist.get;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.entity.ProductEntity;
import com.nepalibazar.entity.UserEntity;
import com.nepalibazar.entity.WishListEntity;
import com.nepalibazar.repository.UserRepository;
import com.nepalibazar.repository.WishListRepository;
import com.nepalibazar.usecase.wishlist.add.AddWishListUseCaseResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class GetWishlistUseCase {
    public final WishListRepository wishListRepository;
    public final UserRepository userRepository;

    @Inject
    public GetWishlistUseCase(WishListRepository wishListRepository,
                              UserRepository userRepository) {
        this.wishListRepository = wishListRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public GetWishlistUseCaseResponse execute(String token) {
        if (token == null || token.isBlank()) {
            return new GetWishlistUseCaseResponse(-1, "Unauthorized: missing token", List.of());
        }

        try {
            String jwt = token.replace("Bearer", "").trim();
            String email = JwtUtils.getEmailFromToken(jwt);
            String role= JwtUtils.getRoleFromToken(jwt);

            if(!"BUYER".equalsIgnoreCase(role)){
                return new GetWishlistUseCaseResponse(-1,"Unauthorized",null);
            }

            Optional<UserEntity> userOpt = userRepository.findByEmailPhone(email);
            if (userOpt.isEmpty()) {
                return new GetWishlistUseCaseResponse(-1, "User not found", List.of());
            }

            UserEntity user = userOpt.get();

            Optional<WishListEntity> wishListOpt = wishListRepository.findByUserEntity(user);
            if (wishListOpt.isEmpty() || wishListOpt.get().getProductEntity() == null) {
                return new GetWishlistUseCaseResponse(0, "Wishlist is empty", List.of());
            }

            List<WishListProductResponse> products = wishListOpt.get()
                    .getProductEntity()
                    .stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());

            return new GetWishlistUseCaseResponse(0, "Wishlist fetched successfully", products);
        } catch (Exception e) {
            e.printStackTrace();
            return new GetWishlistUseCaseResponse(-1, "Error: " + e.getMessage(), List.of());
        }
    }

    private WishListProductResponse mapToResponse(ProductEntity product) {
        return new WishListProductResponse(
                product.getId(),
                product.getProductName(),        // change if your entity has a different field
                product.getImage(),    // change accordingly
                product.getPrice()
        );
    }
}
