package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequestDTO {

    @NotNull(message = "Category Id is required")
    private Long categoryId;

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 150, message = "Product name must be between 2 and 150 characters")
    private String productName;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotBlank(message = "Brand is required")
    @Size(min = 2, max = 100, message = "Brand name must be between 2 and 100 characters")
    private String brand;

    private Boolean isActive = true;
}