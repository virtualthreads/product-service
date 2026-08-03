package com.aeropelican.productservice.dto.request;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductVariantsCreateRequestDTO {
    @NotNull
    @Positive
    private Integer productId;

    @NotBlank
    @Size(min = 3, max = 50)
    private String sku;

    @NotBlank
    @Size(max = 50)
    private String color;

    @NotBlank
    @Size(max = 50)
    private String storageCapacity;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    private Boolean isActive;


}
