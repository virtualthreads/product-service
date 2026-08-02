package com.aeropelican.productservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantsCreateRequestDTO {

    private Integer productId;
    private String sku;
    private String color;
    private String storageCapacity;
    private Double price;
    @Builder.Default
    private Boolean isActive = true;

}