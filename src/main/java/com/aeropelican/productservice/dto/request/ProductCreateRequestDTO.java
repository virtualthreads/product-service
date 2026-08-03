package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductCreateRequestDTO {

    @NotNull(message = "Category Id is required")
    @Positive(message = "Category Id must be positive")
    private Long categoryId;    

    @NotBlank(message = "Product name is required")
    @Size(min = 3, max = 100,
            message = "Product name must be between 3 and 100 characters")
    private String productName;

    @NotBlank(message = "Description is required")
    @Size(min = 5, max = 500,
            message = "Description must be between 5 and 500 characters")
    private String description;

    @NotBlank(message = "Brand is required")
    @Size(min = 2, max = 100,
            message = "Brand must be between 2 and 100 characters")
    private String brand;

    private Boolean isActive;
}