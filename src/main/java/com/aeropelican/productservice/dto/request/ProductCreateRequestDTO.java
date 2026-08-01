package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequestDTO {

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    @NotBlank(message = "Product name is required")
    @Size(min = 4, max = 500, message = "Product name must be between 4 and 500 characters")
    private String productName;

    @NotBlank(message = "Description cannot be null or empty")
    @Size(min = 10, max = 3000, message = "Description must be between 10 and 3000 characters")
    private String description;

    @NotBlank(message = "Brand cannot be null or empty")
    @Size(min = 1, max = 30, message = "Brand must be between 1 and 30 characters")
    private String brand;

    @NotNull(message = "IsActive cannot be null")
    @Builder.Default
    private Boolean isActive = true;
}