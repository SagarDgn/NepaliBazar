package com.nepalibazar.usecase.wishlist.add;

import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.repository.WishListRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class AddWishListUseCase implements UseCase<AddWishListUseCaseRequest, AddWishListUseCaseResponse> {

    public final WishListRepository wishListRepository;

    @Inject
    public AddWishListUseCase(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }
    @Override
    public AddWishListUseCaseResponse execute(AddWishListUseCaseRequest request) {
        return null;
    }
}
