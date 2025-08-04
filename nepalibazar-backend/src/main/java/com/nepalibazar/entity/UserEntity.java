package com.nepalibazar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nepalibazar.domain.USER_ROLE;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;


@Entity
@Table(name = "users_details")
public class UserEntity {

    @Id
//    @AutoPopulated
    @Column(name = "user-id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "username is required")
    @Column(name = "username")
    private String userName;

    @Column(name = "user-email-phone")
    private String emailPhone;

    @NotBlank(message = "password is required")
    @Column(name = "user-password")
    private String password;

    @Column(name = "user-address")
    private String address;

    @Column(name = "user-role")
    @Enumerated(EnumType.STRING)
    private USER_ROLE userRole= USER_ROLE.BUYER;

    @JsonIgnore
    @DateCreated
    @Column(name = "created-date")
    private Instant createdAt;

    @JsonIgnore
    @DateUpdated
    @Column(name = "updated-date")
    private Instant updatedAt;

//    @Column(name = "user-cart")
//    private List<CartEntity> myCart= new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "username is required") String getUserName() {
        return userName;
    }

    public void setUserName(@NotBlank(message = "username is required") String userName) {
        this.userName = userName;
    }

    public String getEmailPhone() {
        return emailPhone;
    }

    public void setEmailPhone(String emailPhone) {
        this.emailPhone = emailPhone;
    }

    public @NotBlank(message = "password is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "password is required") String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public USER_ROLE getUserRole() {
        return userRole;
    }

    public void setUserRole(USER_ROLE userRole) {
        this.userRole = userRole;
    }

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

//    public List<CartEntity> getMyCart() {
//        return myCart;
//    }
//
//    public void setMyCart(List<CartEntity> myCart) {
//        this.myCart = myCart;
//    }
}

