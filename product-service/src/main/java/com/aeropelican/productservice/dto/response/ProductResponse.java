package com.aeropelican.productservice.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ProductResponse(
        Long productId,
        String productName,
        String description,
        String brand,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<ProductVariantResponse> variants
) {
}
