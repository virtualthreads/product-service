package com.aeropelican.productservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductVariantResponseDTO {
    private Long variantId;
    private String sku;
    private String color;
    private String storageCapacity;
    private BigDecimal price;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
