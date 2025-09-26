package com.nepalibazar.usecase.payment.stripe;

import com.nepalibazar.core.payment.PaymentGateway;
import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.entity.OrderEntity;
import com.nepalibazar.entity.UserEntity;
import com.nepalibazar.repository.OrderRepository;
import com.nepalibazar.repository.UserRepository;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Singleton
public class CheckoutStripeUseCase {

    public final UserRepository userRepository;
    public final OrderRepository orderRepository;

    @Inject
    public CheckoutStripeUseCase(UserRepository userRepository,
                                 OrderRepository orderRepository,
                                 @jakarta.inject.Named("stripe.secret-key")String stripeSecretKey){
        this.orderRepository=orderRepository;
        this.userRepository=userRepository;
        Stripe.apiKey=stripeSecretKey;
    }

    @Transactional
    public CheckoutStripeUseCaseResponse execute(String token, CheckoutStripeUseCaseRequest request){
        try{
            if(token==null){
                return new  CheckoutStripeUseCaseResponse(-1,"Unauthorized","");
            }

            String jwt= token.replace("Bearer","").trim();
            String email= JwtUtils.getEmailFromToken(jwt);

            Optional<UserEntity> user= userRepository.findByEmailPhone(email);
            if(user.isEmpty()){
                return new CheckoutStripeUseCaseResponse(-1,"User not available","");
            }
            UserEntity userEntity= user.get();

            Optional<OrderEntity> order= orderRepository.findById(request.orderId());
            if(order.isEmpty()){
                return new CheckoutStripeUseCaseResponse(-1,"No order found","");
            }
            OrderEntity orderEntity= order.get();

            Long amountInCents= Math.round(orderEntity.getTotalSellingPrice()*100);

            SessionCreateParams.LineItem.PriceData.ProductData productData=
                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                            .setName("Order#"+ orderEntity.getId())
                            .build();

            SessionCreateParams.LineItem.PriceData priceData=
                    SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency("usd")
                            .setUnitAmount(amountInCents)
                            .setProductData(productData)
                            .build();

            SessionCreateParams.LineItem lineItem=
                    SessionCreateParams.LineItem.builder()
                            .setQuantity(1L)
                            .setPriceData(priceData)
                            .build();

            SessionCreateParams sessionCreateParams= SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(request.successUrl())
                    .setCancelUrl(request.cancelUrl())
                    .addLineItem(lineItem)
                    .putMetadata("orderId", orderEntity.getId().toString())
                    .build();

            Session session= Session.create(sessionCreateParams);
            return new CheckoutStripeUseCaseResponse(0, "Successful checkout from stripe", session.getUrl());



        }catch (Exception e){
            e.printStackTrace();
            return new CheckoutStripeUseCaseResponse(-1,e.getMessage(),null);
        }
    }

}
