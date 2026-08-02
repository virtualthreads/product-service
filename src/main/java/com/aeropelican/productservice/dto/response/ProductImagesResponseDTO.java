package com.aeropelican.productservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder

public class ProductImagesResponseDTO {

    private Long imageId;
    private Long variantId;
    private String imageUrl;
    private Boolean isPrimary;
    private Integer displayOrder;
    private Timestamp createdAt;
}
