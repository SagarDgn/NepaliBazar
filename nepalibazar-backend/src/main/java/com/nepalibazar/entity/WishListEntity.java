package com.nepalibazar.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "wishlist")
public class WishListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private UserEntity userEntity;

    @ManyToMany
    private Set<ProductEntity> productEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Set<ProductEntity> getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(Set<ProductEntity> productEntity) {
        this.productEntity = productEntity;
    }
}
