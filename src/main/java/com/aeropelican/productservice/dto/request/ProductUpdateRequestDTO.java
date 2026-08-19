package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequestDTO {

    private Long categoryId;

    @Size(min = 4, max = 500, message = "Product name must be between 4 and 500 characters")
    private String productName;

    @Size(min = 10, max = 3000, message = "Description must be between 10 and 3000 characters")
    private String description;

    @Size(min = 1, max = 30, message = "Brand must be between 1 and 30 characters")
    private String brand;

    private Boolean isActive;
}