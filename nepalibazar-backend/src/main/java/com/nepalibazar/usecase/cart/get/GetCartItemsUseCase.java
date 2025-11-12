package com.nepalibazar.usecase.cart.get;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.entity.CartEntity;
import com.nepalibazar.entity.UserEntity;
import com.nepalibazar.repository.CartRepository;
import com.nepalibazar.repository.UserRepository;
import com.nepalibazar.usecase.wishlist.add.AddWishListUseCaseResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class GetCartItemsUseCase {

    public final UserRepository userRepository;
    public final CartRepository cartRepository;

    @Inject
    public GetCartItemsUseCase(UserRepository userRepository,
                               CartRepository cartRepository){
        this.userRepository=userRepository;
        this.cartRepository=cartRepository;
    }

    @Transactional
    public GetUserCartItemUseCaseResponse execute(String token){
        try{
            if(token==null){
                return new GetUserCartItemUseCaseResponse(-1,"Unauthorized",null,0,0.0,0.0,0.0);
            }

            String jwt= token.replace("Bearer","").trim();
            String email= JwtUtils.getEmailFromToken(jwt);
            String role= JwtUtils.getRoleFromToken(jwt);

            if(!"BUYER".equalsIgnoreCase(role)){
                return new GetUserCartItemUseCaseResponse(-1,"Unauthorized",null,0,0.0,0.0,0.0);
            }

            Optional<UserEntity> user= userRepository.findByEmailPhone(email);
            if(user.isEmpty()){
                return new GetUserCartItemUseCaseResponse(-1,"User not found",null,0,0.0,0.0,0.0);
            }
            UserEntity userEntity= user.get();

            Optional<CartEntity> cart= cartRepository.findByUser(userEntity);
            if(cart.isEmpty() || cart.get().getCartItemEntities().isEmpty()){
                return new GetUserCartItemUseCaseResponse(-1,"Cart not available", List.of(),0,0.0,0.0,0.0);
            }
            CartEntity cartEntity= cart.get();

            List<CartItemUseCaseResponse> items= cartEntity.getCartItemEntities().stream()
                    .map(item->new CartItemUseCaseResponse(
                            item.getProductEntity().getId(),
                            item.getProductEntity().getProductName(),
                            item.getPrice(),
                            item.getQuantity()

                    )).collect(Collectors.toUnmodifiableList());

            return new GetUserCartItemUseCaseResponse(
                    0,
                    "Success",
                    items,
                    cartEntity.getTotalItem(),
                    cartEntity.getTotalMrpPrice(),
                    cartEntity.getSellingPrice(),
                    cartEntity.getDiscount()

            );

        }catch (Exception e){
            e.printStackTrace();
            return new GetUserCartItemUseCaseResponse(-1,"Internal error occured",null,0,0.0,0.0,0.0);

        }
    }
}
