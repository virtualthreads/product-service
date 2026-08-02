package com.aeropelican.productservice.dto.request;

import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCreateRequestDTO {

    @NotNull(message = "Category ID is required")
    @Positive(message = "Category ID must be greater than 0")
    private Long categoryId;

    @NotBlank(message = "Product name is required")
    @Size(min = 3, max = 100, message = "Product name must be between 3 and 100 characters")
    @Pattern(regexp = "^[A-Za-z0-9\\s&()\\-]+$",
            message = "Product name contains invalid characters")
    private String productName;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;

    @NotBlank(message = "Brand is required")
    @Size(min = 2, max = 50, message = "Brand name must be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-z0-9\\s&.-]+$",
            message = "Brand name contains invalid characters")
    private String brand;

    @NotNull(message = "Active status is required")
    private Boolean isActive = true;
}
