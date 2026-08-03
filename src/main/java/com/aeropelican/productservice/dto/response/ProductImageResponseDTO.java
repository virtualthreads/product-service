package com.aeropelican.productservice.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImageResponseDTO {

    private Long imageId;

    private Long variantId;

    private String imageUrl;

    private Boolean isPrimary;

    private Integer displayOrder;

    private LocalDateTime createdAt;
}