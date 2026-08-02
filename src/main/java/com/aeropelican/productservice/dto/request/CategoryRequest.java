package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        @NotBlank(message = "Category name cannot be blank")
        @Size(min = 1, max = 100, message = "Category name must be between 1 and 100 characters")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-&.,']+$", message = "Category name can only contain letters, numbers, spaces, hyphens, ampersands, dots, commas, and single quotes")
        String categoryName,

        @Size(max = 500, message = "Description must not exceed 500 characters")
        String description,

        @Positive(message = "Parent category ID must be a positive number if provided")
        Long parentCategoryId
) {
}
