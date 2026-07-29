package com.aeropelican.productservice.dto.request;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductRequestDTO {
    private Integer productId;
    private Long categoryId;
    private String productName;
    private String description;
    private String brand;
    private Boolean isActive = true;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}
