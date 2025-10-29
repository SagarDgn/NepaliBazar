package com.nepalibazar.usecase.payment.cod;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.domain.ORDER_STATUS;
import com.nepalibazar.domain.PAYMENT_METHOD;
import com.nepalibazar.domain.PAYMENT_ORDER_STATUS;
import com.nepalibazar.entity.OrderEntity;
import com.nepalibazar.entity.UserEntity;
import com.nepalibazar.repository.OrderRepository;
import com.nepalibazar.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class CheckoutCodUseCase {

    public final UserRepository userRepository;
    public final OrderRepository orderRepository;

    @Inject
    public CheckoutCodUseCase(UserRepository userRepository,
                              OrderRepository orderRepository){
        this.userRepository=userRepository;
        this.orderRepository=orderRepository;
    }

    public CheckoutCodUseCaseResponse execute(String token, CheckoutCodUseCaseRequest request){
        try{
            if(token==null){
                return new CheckoutCodUseCaseResponse(-1,null, "Unauthorized");
            }
            String jwt= token.replace("Bearer","").trim();
            String email= JwtUtils.getEmailFromToken(jwt);

            Optional<UserEntity> user= userRepository.findByEmailPhone(email);
                if(user.isEmpty()){
                    return new CheckoutCodUseCaseResponse(-1,null, "User not available");
                }
                UserEntity userEntity= user.get();


            Optional<OrderEntity> order= orderRepository.findById(request.orderId());
            if(order.isEmpty()){
                return new CheckoutCodUseCaseResponse(-1,null, "Order first");
            }
            OrderEntity orderEntity= order.get();

            if(!orderEntity.getUser().getId().equals(userEntity.getId())){
                return new CheckoutCodUseCaseResponse(-1,null, "Unauthorized to access this order");
            }

            orderEntity.setOrderStatus(ORDER_STATUS.CONFIRMED);
            orderEntity.setPaymentOrderStatus(PAYMENT_ORDER_STATUS.SUCCESS);
            orderEntity.setPaymentMethod(PAYMENT_METHOD.CASH);

            orderRepository.update(orderEntity);
            return new CheckoutCodUseCaseResponse(0,orderEntity.getId(),"Order placed on Cash on Delivery");

        }catch(Exception e){
            e.printStackTrace();
            return new CheckoutCodUseCaseResponse(-1,null,"Internal error");
        }
    }
}
