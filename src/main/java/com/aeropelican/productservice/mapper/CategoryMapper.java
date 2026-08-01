package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.request.CategoryRequest;
import com.aeropelican.productservice.dto.response.CategoryResponse;
import com.aeropelican.productservice.entity.Category;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .parentCategoryId(category.getParentCategoryId())
                .isActive(category.getIsActive())
                .createdAt(category.getCreateAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }

    public Category toEntity(CategoryRequest categoryRequest) {
        return Category.builder()
                .categoryName(categoryRequest.categoryName())
                .description(categoryRequest.description())
                .parentCategoryId(categoryRequest.parentCategoryId())
                .isActive(false)
                .createAt(LocalDateTime.now())
                .build();
    }
}
