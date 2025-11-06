package com.nepalibazar.controller;

import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.usecase.payment.stripe.CheckoutStripeUseCase;
import com.nepalibazar.usecase.payment.stripe.CheckoutStripeUseCaseRequest;
import com.nepalibazar.usecase.payment.stripe.CheckoutStripeUseCaseResponse;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/api/v1")
public class StripeCheckoutController {

    private final CheckoutStripeUseCase checkoutStripeUseCase;

    @Inject
    public StripeCheckoutController(CheckoutStripeUseCase checkoutStripeUseCase) {
        this.checkoutStripeUseCase = checkoutStripeUseCase;
    }

    @Post("/stripe/create-checkout")
    public RestResponse<CheckoutStripeUseCaseResponse> createCheckoutSession(
            @Body CheckoutStripeUseCaseRequest request,
            @Header(HttpHeaders.AUTHORIZATION) String authorization) {

        if (authorization == null) {
            return RestResponse.error("Unauthorized");
        }

        try {
            CheckoutStripeUseCaseResponse response = checkoutStripeUseCase.execute(authorization, request);
            return RestResponse.success(response);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.error("Internal error: " + e.getMessage());
        }
    }
}
