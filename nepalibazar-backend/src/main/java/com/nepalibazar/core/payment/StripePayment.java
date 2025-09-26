package com.nepalibazar.core.payment;

import com.stripe.Stripe;
import io.micronaut.context.annotation.Value;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;

@Singleton
public class StripePayment {

    @Value("${stripe.secret-key}")
    private Stripe secretKey;

    @PostConstruct
    public void init(){
        Stripe apiKey= secretKey;
    }
}
