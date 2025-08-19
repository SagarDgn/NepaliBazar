package com.nepalibazar.entity;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.DateCreated;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity

@Table(name = "review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id")
    private Integer id;

   @Column(name = "product_review")
    private String reviewText;

    @Column(name = "product_rating")
    private double rating;

    @Column(name = "product_image")
    @ElementCollection
    private List<String> productImage;

    @JoinColumn(name = "product_data")
    @ManyToOne
    private ProductEntity productEntity;

    @JoinColumn(name = "user_data")
    @ManyToOne
    private UserEntity userEntity;

    @DateCreated
    @Column(name = "created_date")
    private Instant createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<String> getProductImage() {
        return productImage;
    }

    public void setProductImage(List<String> productImage) {
        this.productImage = productImage;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
