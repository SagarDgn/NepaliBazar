package com.nepalibazar.controller;

import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.domain.ORDER_STATUS;
import com.nepalibazar.domain.PAYMENT_ORDER_STATUS;
import com.nepalibazar.entity.OrderEntity;
import com.nepalibazar.repository.OrderRepository;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import java.util.Optional;

@Controller("/api/v1")
public class StripeWebHookController {

    private final OrderRepository orderRepository;
    private final String endPointSecret;

    @Inject
    public StripeWebHookController(OrderRepository orderRepository,
                                   @jakarta.inject.Named("stripe.webhook.secret") String endPointSecret) {
        this.orderRepository = orderRepository;
        this.endPointSecret = endPointSecret;
    }

    @Post("/checkout/stripe")
    public RestResponse<String> handleWebHook(@Body String payload,
                                              @Header("Stripe-Signature") String sigHeader) {
        try {
            Event event = Webhook.constructEvent(payload, sigHeader, endPointSecret);

            switch (event.getType()) {
                case "checkout.session.completed": {
                    EventDataObjectDeserializer deserializer = event.getDataObjectDeserializer();
                    if (deserializer.getObject().isPresent()) {
                        Session session = (Session) deserializer.getObject().get();
                        Integer orderId = Integer.parseInt(session.getMetadata().get("orderId"));
                        updateOrderStatus(orderId, PAYMENT_ORDER_STATUS.SUCCESS, ORDER_STATUS.CONFIRMED);
                    }
                    break;
                }

                case "checkout.session.expired": {
                    EventDataObjectDeserializer deserializer = event.getDataObjectDeserializer();
                    if (deserializer.getObject().isPresent()) {
                        Session session = (Session) deserializer.getObject().get();
                        Integer orderId = Integer.parseInt(session.getMetadata().get("orderId"));
                        updateOrderStatus(orderId, PAYMENT_ORDER_STATUS.FAILED, ORDER_STATUS.CANCELLED);
                    }
                    break;
                }

                case "payment_intent.payment_failed": {
                    EventDataObjectDeserializer deserializer = event.getDataObjectDeserializer();
                    if (deserializer.getObject().isPresent()) {
                        PaymentIntent intent = (PaymentIntent) deserializer.getObject().get();
                        Integer orderId = Integer.parseInt(intent.getMetadata().get("orderId"));
                        updateOrderStatus(orderId, PAYMENT_ORDER_STATUS.FAILED, ORDER_STATUS.CANCELLED);
                    }
                    break;
                }

                default:
                    // ignore unhandled events
                    break;
            }

            return RestResponse.success("Webhook processed: " + event.getType());

        } catch (Exception e) {
            return RestResponse.error("Webhook error: " + e.getMessage());
        }
    }

    private void updateOrderStatus(Integer orderId,
                                   PAYMENT_ORDER_STATUS paymentStatus,
                                   ORDER_STATUS orderStatus) {
        Optional<OrderEntity> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            OrderEntity orderEntity = order.get();
            orderEntity.setPaymentOrderStatus(paymentStatus);
            orderEntity.setOrderStatus(orderStatus);
            orderRepository.update(orderEntity);
        }
    }
}
