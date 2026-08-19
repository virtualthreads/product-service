package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.request.CategoryRequestDTO;
import com.aeropelican.productservice.dto.response.CategoryResponseDTO;
import com.aeropelican.productservice.entity.Category;

public class CategoryMapper {

    public static Category toEntity(CategoryRequestDTO request) {
        if (request == null) {
            return null;
        }
        return Category.builder()
                .categoryName(request.getCategoryName())
                .description(request.getDescription())
                .isActive(request.getActive() != null ? request.getActive() : true)
                .build();
    }

    public static CategoryResponseDTO toDTO(Category category) {
        if (category == null) {
            return null;
        }

        // THESE TWO LINES ARE WHAT PRINT THE MESSAGES TO THE CONSOLE:
        System.out.println("Before fetching product entity");
        System.out.println("Attempting to fetch product entity");

        return CategoryResponseDTO.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .isActive(category.getIsActive())
                .createAt(category.getCreateAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}