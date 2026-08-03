package com.aeropelican.productservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductImagesResponseDTO {

    private Integer imageId;
    private Integer variantId;
    private String imageUrl;
    private Boolean isPrimary;
    private Integer displayOrder;
    private LocalDateTime createdAt;
}