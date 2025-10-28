package com.nepalibazar.usecase.order.place;

import com.nepalibazar.convertor.AddressConvertor;
import com.nepalibazar.core.payment.PaymentGateway;
import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.domain.ORDER_STATUS;
import com.nepalibazar.entity.*;
import com.nepalibazar.convertor.OrderConvertor;
import com.nepalibazar.repository.AddressRepository;
import com.nepalibazar.repository.CartRepository;
import com.nepalibazar.repository.OrderRepository;
import com.nepalibazar.repository.UserRepository;
import com.nepalibazar.usecase.order.cancel.CancelOrderUseCaseResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;


@Singleton
public class PlaceOrderUseCase {

    public final UserRepository userRepository;
    public final CartRepository cartRepository;
    public final OrderRepository orderRepository;
    public final AddressRepository addressRepository;

    @Inject
    public PlaceOrderUseCase(UserRepository userRepository,
                             CartRepository cartRepository,
                             OrderRepository orderRepository,
                             AddressRepository addressRepository){
        this.orderRepository=orderRepository;
        this.userRepository=userRepository;
        this.addressRepository=addressRepository;
        this.cartRepository=cartRepository;
    }
    public PlaceOrderUseCaseResponse execute(String token,PlaceOrderUseCaseRequest request){
        try{
            if(token==null){
                return new PlaceOrderUseCaseResponse(-1,null,"Unauthorized");
            }

            String jwt= token.replace("Bearer","").trim();
            String email= JwtUtils.getEmailFromToken(jwt);
            String role= JwtUtils.getRoleFromToken(jwt);

            if(!"BUYER".equalsIgnoreCase(role)){
                return new PlaceOrderUseCaseResponse(-1,null,"Unauthorized");
            }

            Optional<UserEntity> user= userRepository.findByEmailPhone(email);
            if(user.isEmpty()){
                return new PlaceOrderUseCaseResponse(-1,null,"User not available");
            }
            UserEntity userEntity= user.get();

            Optional<CartEntity> cart= cartRepository.findByUser(userEntity);
            if(cart.isEmpty()){
                return new PlaceOrderUseCaseResponse(-1,null,"Cart not available");
            }
            CartEntity cartEntity= cart.get();

            var userAddress= AddressConvertor.toEntity(request);
            addressRepository.save(userAddress);

            var order = OrderConvertor.fromCartAndRequest(userEntity, cartEntity, userAddress, request);
            var savedOrder = orderRepository.save(order);



            cartEntity.getCartItemEntities().clear();
            cartEntity.setTotalMrpPrice(0.0);
            cartEntity.setTotalItem(0);
            cartEntity.setSellingPrice(0.0);
            cartEntity.setDiscount(0);
            cartRepository.update(cartEntity);

            return new PlaceOrderUseCaseResponse(0, savedOrder.getId(), "Order placed successfullu");



        }catch(Exception e){
            e.printStackTrace();
            return new PlaceOrderUseCaseResponse(-1,null,"Internal server error"+e.getMessage());
        }
    }
}
