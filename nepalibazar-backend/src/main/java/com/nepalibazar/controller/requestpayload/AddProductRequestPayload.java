package com.nepalibazar.controller.requestpayload;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AddProductRequestPayload(
        String productName,
        String aboutProduct,
        Double  price,
        Integer discount,
        String image,
        Integer quantity
) {
}
