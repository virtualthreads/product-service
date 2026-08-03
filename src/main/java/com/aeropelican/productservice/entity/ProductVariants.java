package com.aeropelican.productservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_variants")
@Data
public class ProductVariants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variant_id")
    private Integer variantId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "sku")
    private String sku;

    @Column(name = "color")
    private String color;

    @Column(name = "storage_capacity")
    private String storageCapacity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}