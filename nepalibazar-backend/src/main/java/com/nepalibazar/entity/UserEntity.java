package com.nepalibazar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nepalibazar.domain.USER_ROLE;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import io.micronaut.serde.annotation.SerdeImport;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@SerdeImport
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

    @Column(name = "user-email_phone")
    private String emailPhone;

    @NotBlank(message = "password is required")
    @Column(name = "user_password")
    private String password;

    @Column(name = "user_address")
    private String address;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private USER_ROLE userRole= USER_ROLE.BUYER;

    @JsonIgnore
    @DateCreated
    @Column(name = "created_date")
    private Instant createdAt;

    @JsonIgnore
    @DateUpdated
    @Column(name = "updated_date")
    private Instant updatedAt;


    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<AddressEntity> addressEntityset= new HashSet<>();

    @Column(name = "is_logged-in")
    private Boolean isLoggedIin;

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

    @Transactional
    public void setAddressEntityset(Set<AddressEntity> addressEntityset) {
        this.addressEntityset = addressEntityset;
    }

    @Transactional
    public Set<AddressEntity> getAddressEntityset() {
        return addressEntityset;
    }

    public Boolean getLoggedIin() {
        return isLoggedIin;
    }

    public void setLoggedIin(Boolean loggedIin) {
        isLoggedIin = loggedIin;
    }
}

