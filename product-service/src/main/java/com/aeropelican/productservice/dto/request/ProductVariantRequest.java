package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record ProductVariantRequest(
        @Positive(message = "Product ID must be a positive number")
        Long productId,

        @NotBlank(message = "SKU cannot be blank")
        @Size(min = 1, max = 50, message = "SKU must be between 1 and 50 characters")
        @Pattern(regexp = "^[a-zA-Z0-9\\-_]+$", message = "SKU can only contain alphanumeric characters, hyphens, and underscores")
        String sku,

        @Size(max = 50, message = "Color must not exceed 50 characters")
        String color,

        @Size(max = 20, message = "Storage capacity must not exceed 20 characters")
        String storageCapacity,

        @Positive(message = "Price must be greater than 0")
        @DecimalMin(value = "0.01", message = "Price must be at least 0.01")
        @DecimalMax(value = "999999.99", message = "Price must not exceed 999999.99")
        BigDecimal price,

        Boolean isActive
) {
}