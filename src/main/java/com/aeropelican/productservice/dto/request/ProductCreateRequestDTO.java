package com.aeropelican.productservice.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCreateRequestDTO {
    private Long categoryId;
    private String productName;
    private String description;
    private String brand;
    private Boolean isActive = true;
}
