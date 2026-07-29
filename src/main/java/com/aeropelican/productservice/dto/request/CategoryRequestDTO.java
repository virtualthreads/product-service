package com.aeropelican.productservice.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryRequestDTO {
    private String categoryName;
    private String description;
    private Long parentCategoryId;
    private Boolean active;
}
