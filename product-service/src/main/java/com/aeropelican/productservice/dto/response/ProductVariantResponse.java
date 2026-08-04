package com.aeropelican.productservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductVariantResponse(
        Long variantId,
        Long productId,
        String sku,
        BigDecimal price,
        boolean isDefault,
        boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}
