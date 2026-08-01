package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.Size;

public record CategoryRequest(
        String categoryName,
        String description,
        Long parentCategoryId
) {
}
