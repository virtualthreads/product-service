package com.aeropelican.productservice.dto.request;

import lombok.Data;

@Data
public class ProductImageUpdateRequestDTO {
    private String imageUrl;
    private Boolean isPrimary;
    private Integer displayOrder;
}