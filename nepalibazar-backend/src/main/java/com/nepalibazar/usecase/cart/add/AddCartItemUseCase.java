package com.nepalibazar.usecase.cart.add;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.entity.CartEntity;
import com.nepalibazar.entity.CartItemEntity;
import com.nepalibazar.entity.ProductEntity;
import com.nepalibazar.entity.UserEntity;
import com.nepalibazar.repository.CartRepository;
import com.nepalibazar.repository.ProductRepository;
import com.nepalibazar.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.Optional;

@Singleton
public class AddCartItemUseCase {

    public final CartRepository cartRepository;
    public final UserRepository userRepository;
    public final ProductRepository productRepository;

    @Inject
    public AddCartItemUseCase(CartRepository cartRepository,
                              UserRepository userRepository,
                              ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public AddCartItemUseCaseResponse execute(String token,
                                              AddCartItemUseCaseRequest request) {
        try {
            if (token == null) {
                return new AddCartItemUseCaseResponse(-1, "Unauthorized: missing or invalid token");
            }

            String jwt = token.replace("Bearer", "").trim();
            String email = JwtUtils.getEmailFromToken(jwt);

            Optional<UserEntity> user= userRepository.findByEmailPhone(email);
            if(user.isEmpty()){
                return  new AddCartItemUseCaseResponse(-1, "Login required");
            }
            UserEntity userEntity=user.get();

            Optional<ProductEntity> product= productRepository.findById(request.productId());
            if(product.isEmpty()){
                return new AddCartItemUseCaseResponse(-1, "Product not available");
            }
            ProductEntity productEntity= product.get();

            CartEntity cart= cartRepository.findByUser(userEntity)
                    .orElseGet(()->{
                        CartEntity cartEntity= new CartEntity();
                        cartEntity.setUser(userEntity);
                        return cartRepository.save(cartEntity);
                    });
            CartItemEntity cartItemEntity= new CartItemEntity();
            cartItemEntity.setCart(cart);
            cartItemEntity.setProductEntity(productEntity);
            cartItemEntity.setQuantity(1);
            cartItemEntity.setPrice(productEntity.getPrice());

            cart.getCartItemEntities().add(cartItemEntity);

            cart.setTotalItem(cart.getCartItemEntities().size());
            cart.setTotalMrpPrice(cart.getCartItemEntities().stream().mapToDouble(CartItemEntity::getPrice).sum());
            cart.setDiscount(20);
            Double discountedAmount= cart.getTotalMrpPrice()* cart.getDiscount()/100;
            cart.setSellingPrice(cart.getTotalMrpPrice()-discountedAmount);

            cartRepository.save(cart);

            return new AddCartItemUseCaseResponse(0, "Added to cart");




        } catch (Exception e) {
            System.out.println("Error"+ e);
            return new AddCartItemUseCaseResponse(-1, "Internal error occured");

        }
    }
}
