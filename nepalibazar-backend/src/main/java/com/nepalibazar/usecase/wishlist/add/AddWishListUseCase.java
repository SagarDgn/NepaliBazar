package com.nepalibazar.usecase.wishlist.add;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.entity.ProductEntity;
import com.nepalibazar.entity.UserEntity;
import com.nepalibazar.entity.WishListEntity;
import com.nepalibazar.repository.ProductRepository;
import com.nepalibazar.repository.UserRepository;
import com.nepalibazar.repository.WishListRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.HashSet;
import java.util.Optional;

@Singleton
public class AddWishListUseCase {

    private final WishListRepository wishListRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Inject
    public AddWishListUseCase(WishListRepository wishListRepository,
                              UserRepository userRepository,
                              ProductRepository productRepository) {
        this.wishListRepository = wishListRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public AddWishListUseCaseResponse execute(String token, AddWishListUseCaseRequest request) {
        System.out.println("Here inside execute function");
        try {
            if (token == null) {
                return new AddWishListUseCaseResponse(-1, "Unauthorized: missing or invalid token");
            }

            String jwt = token.replace("Bearer", "").trim();
            String email = JwtUtils.getEmailFromToken(jwt);

            Optional<UserEntity> userOptional = userRepository.findByEmailPhone(email);
            if (userOptional.isEmpty()) {
                return new AddWishListUseCaseResponse(-1, "Login required to add product in wishlist");
            }
            UserEntity user = userOptional.get();


            Optional<ProductEntity> productOptional = productRepository.findById(request.productId());
            if (productOptional.isEmpty()) {
                return new AddWishListUseCaseResponse(-1, "Product not found with ID: " + request.productId());
            }
            ProductEntity product = productOptional.get();

            WishListEntity wishList = wishListRepository.findByUserEntity(user)
                    .orElseGet(() -> {
                        WishListEntity wishListEntity = new WishListEntity();
                        wishListEntity.setUserEntity(user);
                        wishListEntity.setProductEntity(new HashSet<>());
                        return wishListEntity;
                    });

            if (wishList.getProductEntity() == null) {
                wishList.setProductEntity(new HashSet<>());
            }

            if (wishList.getProductEntity().contains(product)) {
                wishList.getProductEntity().remove(product);
                wishListRepository.save(wishList);
                return new AddWishListUseCaseResponse(0, "Product removed from wishlist");
            } else {
                wishList.getProductEntity().add(product);
                wishListRepository.save(wishList);
                return new AddWishListUseCaseResponse(0, "Product added to wishlist");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new AddWishListUseCaseResponse(-1,
                    "Wishlist operation failed: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}
