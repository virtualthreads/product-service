package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductImageCreateRequestDTO {
    @NotNull(message = "Variant ID is required")
    private Long variantId;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;

    private Boolean isPrimary = false;
    private Integer displayOrder = 0;
}