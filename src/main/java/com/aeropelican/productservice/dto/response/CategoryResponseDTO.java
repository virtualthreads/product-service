package com.aeropelican.productservice.dto.response;

import com.aeropelican.productservice.entity.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponseDTO {
    private Long categoryId;
    private String categoryName;
    private String description;
    private Category parentCategory;
    private Boolean isActive = true;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}
