package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductImagesUpdateRequestDTO {

    @NotNull(message = "Variant Id is required")
    @Positive(message = "Variant Id must be positive")
    private Integer variantId;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;

    @NotNull(message = "Primary flag is required")
    private Boolean isPrimary;

    @Positive(message = "Display order must be positive")
    private Integer displayOrder;
}