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
    private Boolean isActive = true;
}
