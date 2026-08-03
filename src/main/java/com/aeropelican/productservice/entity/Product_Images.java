package com.aeropelican.productservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_images")
@Data
public class Product_Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer imageId;

    @Column(name = "variant_id")
    private Integer variantId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_primary")
    private Boolean isPrimary;

    @Column(name = "display_order")
    private Integer displayOrder;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}