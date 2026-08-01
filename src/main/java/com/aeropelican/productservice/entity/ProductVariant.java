package com.aeropelican.productservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_variants")
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variant_id", nullable = false)
    private Long variantId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "sku", nullable = false, length = 50)
    private String sku;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @ColumnDefault("1")
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}