package com.aeropelican.productservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequestDTO {
    private Long categoryId;
    private String productName;
    private String description;
    private String brand;

    @Builder.Default
    private Boolean isActive = true;
}
