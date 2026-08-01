package com.aeropelican.productservice.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CategoryResponse(
        Long categoryId,
        String categoryName,
        String description,
        Long parentCategoryId,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
