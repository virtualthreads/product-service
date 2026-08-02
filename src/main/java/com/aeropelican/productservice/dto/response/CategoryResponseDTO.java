package com.aeropelican.productservice.dto.response;

import com.aeropelican.productservice.entity.Category;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CategoryResponseDTO {
    private Long categoryId;
    private String categoryName;
    private String description;
    private Category parentCategory;
    @Builder.Default
    private Boolean isActive = true;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}
