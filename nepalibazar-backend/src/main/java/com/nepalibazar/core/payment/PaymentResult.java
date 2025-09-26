package com.nepalibazar.core.payment;

public record PaymentResult(
        String paymentId,
        String clientSecret
) {
}
