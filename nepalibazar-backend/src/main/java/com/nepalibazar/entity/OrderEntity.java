package com.nepalibazar.entity;

import com.nepalibazar.domain.ORDER_STATUS;
import com.nepalibazar.domain.PAYMENT_METHOD;
import com.nepalibazar.domain.PAYMENT_ORDER_STATUS;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.serde.annotation.SerdeImport;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Serdeable
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private UserEntity user;

    @OneToMany(mappedBy = "order" ,cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<OrderItemEntity> orderItem= new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_address")
    private AddressEntity shippingAddress;

    private Double totalMrpPrice;

    private Double totalSellingPrice;

    private Integer discount;

    private Integer totalItem;

    @ManyToOne
    @JoinColumn(name = "payment_order_id")
    private PaymentOrderEntity paymentOrder;

    @Embedded
    private PaymentDetails paymentDetails = new PaymentDetails();

    @Enumerated(EnumType.STRING)
    private ORDER_STATUS orderStatus= ORDER_STATUS.PENDING;

    @Enumerated(EnumType.STRING)
    private PAYMENT_METHOD paymentMethod;

    @Enumerated(EnumType.STRING)
    private PAYMENT_ORDER_STATUS paymentOrderStatus= PAYMENT_ORDER_STATUS.PENDING;

    @DateCreated
    private Instant orderDate;

//    @DateCreated
    private Instant deliverDate;

    public PaymentOrderEntity getPaymentOrder() {
        return paymentOrder;
    }

    public void setPaymentOrder(PaymentOrderEntity paymentOrder) {
        this.paymentOrder = paymentOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Set<OrderItemEntity> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(Set<OrderItemEntity> orderItem) {
        this.orderItem= orderItem;
    }

    public Double getTotalMrpPrice() {
        return totalMrpPrice;
    }

    public void setTotalMrpPrice(Double totalMrpPrice) {
        this.totalMrpPrice = totalMrpPrice;
    }

    public Double getTotalSellingPrice() {
        return totalSellingPrice;
    }

    public void setTotalSellingPrice(Double totalSellingPrice) {
        this.totalSellingPrice = totalSellingPrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public ORDER_STATUS getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(ORDER_STATUS orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PAYMENT_METHOD getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PAYMENT_METHOD paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PAYMENT_ORDER_STATUS getPaymentOrderStatus() {
        return paymentOrderStatus;
    }

    public void setPaymentOrderStatus(PAYMENT_ORDER_STATUS paymentOrderStatus) {
        this.paymentOrderStatus = paymentOrderStatus;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }

    public Instant getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Instant deliverDate) {
        this.deliverDate = deliverDate;
    }

    public AddressEntity getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(AddressEntity shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
}
