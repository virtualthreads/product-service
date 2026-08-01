package com.aeropelican.productservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariantsResponseDTO {
    private Long variantId;
    private String variantName;
    private String color;
    private String size;
    private BigDecimal price;
    private Long productId;
}