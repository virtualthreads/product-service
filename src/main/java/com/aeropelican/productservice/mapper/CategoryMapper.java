package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.request.CategoryRequestDTO;
import com.aeropelican.productservice.dto.response.CategoryResponseDTO;
import com.aeropelican.productservice.entity.Category;

import java.time.LocalDateTime;

public class CategoryMapper {

    public static CategoryResponseDTO toDTO(Category category) {
        return CategoryResponseDTO.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .parentCategory(category.getParentCategory())
                .isActive(category.getIsActive())
                .description(category.getDescription())
                .createAt(category.getCreateAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }

    public static Category toEntity(CategoryRequestDTO requestDTO) {
        Category category = new Category();
        category.setCategoryName(requestDTO.getCategoryName());
        category.setDescription(requestDTO.getDescription());
        category.setIsActive(true);
        category.setCreateAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
        return category;
    }
}
