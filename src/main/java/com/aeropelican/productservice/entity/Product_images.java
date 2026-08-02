package com.aeropelican.productservice.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "product_images")
public class Product_images {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "image_id")
        private Long imageId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "variant_id", nullable = false)
        private Product_Variants productVariant;

        @Column(name = "image_url", nullable = false, length = 255)
        private String imageUrl;

        @Column(name = "is_primary", nullable = false)
        private Boolean isPrimary;

        @Column(name = "display_order", nullable = false)
        private Integer displayOrder;

        @Column(name = "created_at", insertable = false, updatable = false)
        private Timestamp createdAt;

        public Product_images () {
        }

        public Long getImageId() {
            return imageId;
        }

        public void setImageId(Long imageId) {
            this.imageId = imageId;
        }

        public Product_Variants getProductVariant() {
            return productVariant;
        }

        public void setProductVariant(Product_Variants productVariant) {
            this.productVariant = productVariant;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Boolean getIsPrimary() {
            return isPrimary;
        }

        public void setIsPrimary(Boolean primary) {
            isPrimary = primary;
        }

        public Integer getDisplayOrder() {
            return displayOrder;
        }

        public void setDisplayOrder(Integer displayOrder) {
            this.displayOrder = displayOrder;
        }

        public Timestamp getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Timestamp createdAt) {
            this.createdAt = createdAt;
        }
    }


