package com.nepalibazar.controller.payload;

public record ChatMessageWrapper(
        String token,
        String receiverEmail,
        String content
) {
}
