package com.nepalibazar.entity;

import com.nepalibazar.domain.USER_ROLE;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "sellers_details")
@Introspected
@Serdeable

public class SellerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seller_id")
    private Integer id;

    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "seller_email_phone")
    private String emailPhone;

    @Column(name = "seller_password")
    private String password;

    @Column(name = "seller_location")
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private USER_ROLE role= USER_ROLE.SELLER;

    @Column(name = "seller_rating")
    private String rating;

//    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ProductEntity> products = new ArrayList<>();


    @DateCreated
    @Column(name = "creation_date")
    private Instant createdAt;

    @DateUpdated
    @Column(name = "updated_date")
    private Instant updatedAt;

    @Column(name = "product_category")
    private String productCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getEmailPhone() {
        return emailPhone;
    }

    public void setEmailPhone(String emailPhone) {
        this.emailPhone = emailPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public USER_ROLE getRole() {
        return role;
    }

    public void setRole(USER_ROLE role) {
        this.role = role;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

//    public List<ProductEntity> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<ProductEntity> products) {
//        this.products = products;
//    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
