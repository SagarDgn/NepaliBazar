package com.nepalibazar.core.payment;

import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

public class StripePaymentGatewayImplementation implements PaymentGateway {

    @Override
    public PaymentResult createPaymentIntent(Long amount, String currency) {
        try{
            PaymentIntentCreateParams params= PaymentIntentCreateParams.builder()
                    .setAmount(amount)
                    .setCurrency(currency)
                    .build();

            PaymentIntent  intent= PaymentIntent.create(params);

            return new PaymentResult(intent.getId(),intent.getClientSecret());

        }catch (Exception e){
            throw new RuntimeException("Stripe error"+e.getMessage());
        }
    }
}
