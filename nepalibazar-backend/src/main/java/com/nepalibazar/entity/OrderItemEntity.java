package com.nepalibazar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
@Serdeable
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderItemId")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private OrderEntity order;

    @ManyToOne
    private ProductEntity productEntity;

    @ManyToOne
    private SellerEntity sellerEntity;

    private Integer quantity;

    private Double mrpPrice;

    private Double sellingPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public SellerEntity getSellerEntity() {
        return sellerEntity;
    }

    public void setSellerEntity(SellerEntity sellerEntity) {
        this.sellerEntity = sellerEntity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getMrpPrice() {
        return mrpPrice;
    }

    public void setMrpPrice(Double mrpPrice) {
        this.mrpPrice = mrpPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
