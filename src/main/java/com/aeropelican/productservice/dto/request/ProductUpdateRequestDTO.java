package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
    public class ProductUpdateRequestDTO {

        @NotNull(message = "Category ID is required")
        @Positive(message = "Category ID must be greater than 0")
        private Long categoryId;

        @NotBlank(message = "Product name is required")
        @Size(min = 2, max = 100,
                message = "Product name must be between 2 and 100 characters")
        @Pattern(regexp = "^[A-Za-z0-9()\\-\\s]+$",
                message = "Product name can contain only letters, numbers, spaces, hyphens (-), and parentheses")
        private String productName;

        @NotBlank(message = "Description is required")
        @Size(min = 10, max = 1000,
                message = "Description must be between 10 and 1000 characters")
        private String description;

        @NotBlank(message = "Brand is required")
        @Size(min = 2, max = 50,
                message = "Brand must be between 2 and 50 characters")
        @Pattern(regexp = "^[A-Za-z0-9\\s&.-]+$",
                message = "Brand contains invalid characters")
        private String brand;

        @NotNull(message = "Active status is required")
        private Boolean isActive;
}
