package com.nepalibazar.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private UserEntity user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<CartItemEntity>cartItemEntities= new HashSet<>();

    private double sellingPrice;

    private Integer totalItem;

    private double totalMrpPrice;

    private int discount;

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

    public double getSellingPrice() {
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

    public double getTotalMrpPrice() {
        return totalMrpPrice;
    }

    public void setTotalMrpPrice(double totalMrpPrice) {
        this.totalMrpPrice = totalMrpPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
