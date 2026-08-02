package com.aeropelican.productservice.dto.request;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequestDTO {

    private Long categoryId;
    private String productName;
    private String description;
    private String brand;

    @Builder.Default
    private Boolean isActive = true;
}