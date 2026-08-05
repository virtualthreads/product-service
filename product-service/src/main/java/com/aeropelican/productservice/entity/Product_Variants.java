package com.aeropelican.productservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
@Entity
@Data
@Table(name="product_variants")
public class Product_Variants {
    @Id
    @Column(name = "variant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer variantId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "storage_capacity")
    private String storageCapacity;

    @Column(name = "is_active")
    private Boolean isActive=true;

    @Column(name = "sku")
    private String sku;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;

    public void setPrice(@NotNull(message = "Price is required") @Positive(message = "Price must be greater than 0") Double price) {

    }
}
