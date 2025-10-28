package com.nepalibazar.usecase.cart.removeall;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.entity.CartEntity;
import com.nepalibazar.entity.UserEntity;
import com.nepalibazar.repository.CartRepository;
import com.nepalibazar.repository.UserRepository;
import com.nepalibazar.usecase.wishlist.add.AddWishListUseCaseResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.Optional;

@Singleton
public class ClearCartUseCase {

    public final UserRepository userRepository;
    public final CartRepository cartRepository;

    @Inject
    public ClearCartUseCase(UserRepository userRepository,
                            CartRepository cartRepository){
        this.userRepository=userRepository;
        this.cartRepository=cartRepository;
    }

    @Transactional
    public ClearCartUseCaseResponse execute(String token){
        try{
            if(token==null){
                return new ClearCartUseCaseResponse(-1, "Unauthorized");
            }
            String jwt= token.replace("Bearer","").trim();
            String email= JwtUtils.getEmailFromToken(jwt);
            String role= JwtUtils.getRoleFromToken(jwt);

            if(!"BUYER".equalsIgnoreCase(role)){
                return new ClearCartUseCaseResponse(-1,"Unauthorized");
            }

            Optional<UserEntity> user= userRepository.findByEmailPhone(email);
            if(user.isEmpty()){
                return new ClearCartUseCaseResponse(-1,"No user found");
            }
            UserEntity userEntity= user.get();

            Optional<CartEntity> cart= cartRepository.findByUser(userEntity);
            if(cart.isEmpty()){
                return new ClearCartUseCaseResponse(-1,"No items available");
            }
            CartEntity cartEntity= cart.get();

            if (cartEntity.getCartItemEntities().isEmpty()) {
                return new ClearCartUseCaseResponse(-1, "Cart is already empty");
            }

            cartEntity.getCartItemEntities().clear();
            cartEntity.setTotalItem(0);
            cartEntity.setTotalMrpPrice(0.0);
            cartEntity.setSellingPrice(0.0);

            cartRepository.save(cartEntity);
            return new ClearCartUseCaseResponse(0,"Items removed");

        }catch(Exception e){
            e.printStackTrace();
            return new ClearCartUseCaseResponse(-1,"Internal error occured");

        }
    }
}
