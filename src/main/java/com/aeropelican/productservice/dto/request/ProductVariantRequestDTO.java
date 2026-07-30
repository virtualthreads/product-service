package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariantRequestDTO {

    @NotNull(message = "productId is required")
    private Long productId;

    @NotNull(message = "sku is required")
    @Size(max = 50, message = "sku must not exceed 50 characters")
    private String sku;

    @Size(max = 50, message = "color must not exceed 50 characters")
    private String color;

    @Size(max = 20, message = "storageCapacity must not exceed 20 characters")
    private String storageCapacity;

    @NotNull(message = "price is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "price must be >= 0")
    private BigDecimal price;

    // Optional on create (defaults to true); honored on update.
    private Boolean isActive;
}
