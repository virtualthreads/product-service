package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.request.CategoryRequestDTO;
import com.aeropelican.productservice.dto.response.CategoryResponseDTO;
import com.aeropelican.productservice.entity.Category;

public class CategoryMapper {

    public static CategoryResponseDTO toResponseDTO(Category category) {

        return CategoryResponseDTO.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .parentCategory(category.getParentCategory())
                .isActive(category.getIsActive())
                .createAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }

    public static Category toEntity(CategoryRequestDTO request) {

        Category category = new Category();

        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());
        category.setIsActive(true);

        return category;
    }
}