package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO {
        @NotBlank(message = "Category name is required")
        @Size(min = 3, max = 100, message = "Category name must be between 3 and 100 characters")
        @Pattern(regexp = "^[A-Za-z0-9\\s&-]+$", message = "Category name contains invalid characters")
        private String categoryName;

        @NotBlank(message = "Description is required")
        @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
        private String description;

        @NotNull(message = "Parent category ID cannot be null")
        @PositiveOrZero(message = "Parent category ID must be zero or positive")
        private Long parentCategoryId;

        @NotNull(message = "Active status is required")
        private Boolean active;
    }
