package com.nepalibazar.convertor;

import com.nepalibazar.entity.*;
import com.nepalibazar.usecase.order.place.PlaceOrderUseCaseRequest;

public class OrderConvertor {
    private OrderConvertor(){}

    public static OrderEntity fromCartAndRequest(UserEntity userEntity,
                                                 CartEntity cartEntity,
                                                 AddressEntity addressEntity,
                                                 PlaceOrderUseCaseRequest request) {
        OrderEntity order = new OrderEntity();

        order.setUser(userEntity);
        order.setShippingAddress(addressEntity);
        order.setPaymentMethod(request.paymentMethod());
        order.setOrderStatus(com.nepalibazar.domain.ORDER_STATUS.PENDING);
        order.setTotalMrpPrice(cartEntity.getTotalMrpPrice());
        order.setTotalSellingPrice(cartEntity.getSellingPrice());
        order.setDiscount(cartEntity.getDiscount());
        order.setTotalItem(cartEntity.getTotalItem());


        for (CartItemEntity cartItem : cartEntity.getCartItemEntities()) {
            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setOrder(order);
            orderItem.setProductEntity(cartItem.getProductEntity());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setMrpPrice(cartItem.getPrice());
            order.getOrderItem().add(orderItem);
        }

        return order;
    }
}
