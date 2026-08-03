package com.aeropelican.productservice.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductVariantsUpdateRequestDTO {

    private Integer productId;
    private String sku;
    private String color;
    private String storageCapacity;
    private BigDecimal price;
    private Boolean isActive;

}