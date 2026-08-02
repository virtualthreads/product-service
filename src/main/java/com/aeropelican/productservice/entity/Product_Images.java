package com.aeropelican.productservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "product_images")

public class Product_Images {
        @Id
        @Column(name = "image_id")
        private Long imageId;

        @Column(name = "variant_id")
        private Long variantId;

        @Column(name = "image_url")
        private String imageUrl;

        @Column(name = "is_primary")
        private boolean isPrimary;

        @Column(name = "display_order")
        private Integer displayOrder;

        @Column(name = "created_at")
        private Timestamp createdAt;

}
