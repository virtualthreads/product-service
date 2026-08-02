package com.aeropelican.productservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product_VariantsResponseDTO {

    private Integer variantId;
    private Integer productId;
    private String sku;
    private String color;
    private String storageCapacity;
    private Double price;
    private Boolean isActive;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}