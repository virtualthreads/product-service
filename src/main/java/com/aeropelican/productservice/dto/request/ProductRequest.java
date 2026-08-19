package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record ProductRequest(
        @Positive(message = "Category ID must be a positive number")
        Long categoryId,

        @NotBlank(message = "Product name cannot be blank")
        @Size(min = 1, max = 150, message = "Product name must be between 1 and 150 characters")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-&.,'()]+$", message = "Product name can only contain letters, numbers, spaces, and common punctuation")
        String productName,

        @Size(max = 65535, message = "Description must not exceed 65535 characters")
        String description,

        @Size(max = 100, message = "Brand must not exceed 100 characters")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-&.,']+$", message = "Brand can only contain letters, numbers, spaces, hyphens, ampersands, dots, commas, and single quotes")
        String brand,

        Boolean isActive,
        UUID userId
) {
}