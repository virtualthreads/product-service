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
public class ProductVariantsUpdateRequestDTO {
        @NotNull(message = "Variant ID is required")
        @Positive(message = "Variant ID must be greater than 0")
        private Integer variantId;

        @NotNull(message = "Product ID is required")
        @Positive(message = "Product ID must be greater than 0")
        private Integer productId;

        @NotBlank(message = "SKU is required")
        @Size(min = 3, max = 50, message = "SKU must be between 3 and 50 characters")
        @Pattern(regexp = "^[A-Za-z0-9_-]+$",
                message = "SKU can contain only letters, numbers, hyphens (-), and underscores (_)")
        private String sku;

        @NotBlank(message = "Color is required")
        @Size(min = 2, max = 30, message = "Color must be between 2 and 30 characters")
        @Pattern(regexp = "^[A-Za-z ]+$",
                message = "Color can contain only letters and spaces")
        private String color;

        @NotBlank(message = "Storage capacity is required")
        @Pattern(regexp = "^\\d+(GB|TB)$",
                message = "Storage capacity must be in the format like 64GB, 128GB, 256GB, or 1TB")
        private String storageCapacity;

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be greater than 0")
        private Double price;

        @NotNull(message = "Active status is required")
        private Boolean isActive;

}
