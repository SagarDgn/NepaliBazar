package com.nepalibazar.core.payment;

public interface PaymentGateway {
    PaymentResult  createPaymentIntent(Long amount, String currency);
}
