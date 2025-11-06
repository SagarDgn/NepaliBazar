package com.nepalibazar.entity;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cart")
@Serdeable
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private UserEntity user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<CartItemEntity>cartItemEntities= new HashSet<>();

    private Double sellingPrice;

    private Integer totalItem;

    private Double totalMrpPrice;

    private Integer discount;

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

    public Set<CartItemEntity> getCartItemEntities() {
        return cartItemEntities;
    }

    public void setCartItemEntities(Set<CartItemEntity> cartItemEntities) {
        this.cartItemEntities = cartItemEntities;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public Double getTotalMrpPrice() {
        return totalMrpPrice;
    }

    public void setTotalMrpPrice(double totalMrpPrice) {
        this.totalMrpPrice = totalMrpPrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
