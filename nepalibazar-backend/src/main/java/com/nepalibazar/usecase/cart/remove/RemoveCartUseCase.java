package com.nepalibazar.usecase.cart.remove;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.entity.CartEntity;
import com.nepalibazar.entity.CartItemEntity;
import com.nepalibazar.entity.ProductEntity;
import com.nepalibazar.entity.UserEntity;
import com.nepalibazar.repository.CartRepository;
import com.nepalibazar.repository.ProductRepository;
import com.nepalibazar.repository.UserRepository;
import com.nepalibazar.usecase.wishlist.add.AddWishListUseCaseResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Singleton
public class RemoveCartUseCase {

    public final CartRepository cartRepository;
    public final UserRepository userRepository;
    public final ProductRepository productRepository;

    @Inject

    public RemoveCartUseCase(CartRepository cartRepository,
                             UserRepository userRepository,
                             ProductRepository productRepository){
        this.cartRepository=cartRepository;
        this.userRepository=userRepository;
        this.productRepository=productRepository;
    }

    @Transactional
    public RemoveCartUseCaseResponse execute(String token, RemoveCartUseCaseRequest request){

        try{
            if(token==null){
                return new RemoveCartUseCaseResponse(-1, "Unauthorized",0);
            }

            String jwt= token.replace("Bearer", "").trim();
            String email= JwtUtils.getEmailFromToken(jwt);
            String role= JwtUtils.getRoleFromToken(jwt);

            if(!"BUYER".equalsIgnoreCase(role)){
                return new RemoveCartUseCaseResponse(-1,"Unauthorized",0);
            }

            Optional<UserEntity> user= userRepository.findByEmailPhone(email);
            if(user.isEmpty()){
                return new RemoveCartUseCaseResponse(-1, "User not found",0);
            }
            UserEntity userEntity= user.get();
            List<Integer> productsId= request.productId();
            if(productsId==null || productsId.isEmpty()){
                return new RemoveCartUseCaseResponse(-1, "No product selected",0);
            }
            Optional<CartEntity> cart = cartRepository.findByUser(userEntity);
            if(cart.isEmpty()){
                return new RemoveCartUseCaseResponse(-1, "Cart not available",0);
            }
            CartEntity cartEntity= cart.get();

            Integer removed=0;

            for(Integer productId: productsId){
                Optional<CartItemEntity> cartItemEntity= cartEntity.getCartItemEntities()
                        .stream()
                        .filter(item->item.getProductEntity().getId().equals(productId))
                        .findFirst();

                if(cartItemEntity.isPresent()){
                   CartItemEntity cartItemEntity1= cartItemEntity.get();
                   cartEntity.getCartItemEntities().remove(cartItemEntity1);
                   removed++;

                }
            }
            cartEntity.setTotalItem(cartEntity.getCartItemEntities().size());
            cartEntity.setTotalMrpPrice(cartEntity.getCartItemEntities()
                    .stream()
                    .mapToDouble(CartItemEntity::getPrice)
                    .sum());
            cartEntity.setSellingPrice(cartEntity.getTotalMrpPrice());
            cartRepository.save(cartEntity);

            return new RemoveCartUseCaseResponse(0,"Products removed",removed);


        }catch (Exception e){
            e.printStackTrace();
            return  new RemoveCartUseCaseResponse(-1,"Internal error occured",0);

        }
    }
}
