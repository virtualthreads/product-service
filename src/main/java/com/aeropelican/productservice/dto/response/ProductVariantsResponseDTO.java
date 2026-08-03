package com.aeropelican.productservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductVariantsResponseDTO {

    private Integer variantId;
    private Integer productId;
    private String sku;
    private String color;
    private String storageCapacity;
    private BigDecimal price;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}