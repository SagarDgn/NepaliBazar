package com.nepalibazar.entity;

import com.nepalibazar.domain.PAYMENT_METHOD;
import com.nepalibazar.domain.PAYMENT_ORDER_STATUS;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "payment_order")
public class PaymentOrderEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "productOrderId")
    private Integer id;

    @Enumerated(EnumType.STRING)
    private PAYMENT_ORDER_STATUS status = PAYMENT_ORDER_STATUS.PENDING;

    @Enumerated(EnumType.STRING)
    private PAYMENT_METHOD paymentMethod;

    private String paymentGatewayTransactionId;

    @ManyToOne
    private UserEntity user;

    @OneToMany(mappedBy = "paymentOrder", cascade = CascadeType.ALL)
    private Set<OrderEntity> orders = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PAYMENT_ORDER_STATUS getStatus() {
        return status;
    }

    public void setStatus(PAYMENT_ORDER_STATUS status) {
        this.status = status;
    }

    public PAYMENT_METHOD getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PAYMENT_METHOD paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentGatewayTransactionId() {
        return paymentGatewayTransactionId;
    }

    public void setPaymentGatewayTransactionId(String paymentGatewayTransactionId) {
        this.paymentGatewayTransactionId = paymentGatewayTransactionId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }
}
