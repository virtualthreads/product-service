package com.aeropelican.productservice.dto.request;

import java.time.LocalDateTime;

public record ProductResponse(
        Long productId,
        Long categoryId,
        String productName,
        String description,
        String brand,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}

