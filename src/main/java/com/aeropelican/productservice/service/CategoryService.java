package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.CategoryRequest;
import com.aeropelican.productservice.dto.response.CategoryResponse;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.exceptions.BadRequestException;
import com.aeropelican.productservice.exceptions.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.CategoryMapper;
import com.aeropelican.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        log.debug("Attempting to create a category: {}", categoryRequest.categoryName());

        if (categoryRepository.existsByCategoryNameIgnoreCase(categoryRequest.categoryName())) {
            log.error("Category {} is already exist. Cannot process the request", categoryRequest.categoryName());
            throw new BadRequestException("Category '%s' already exist".formatted(categoryRequest.categoryName()));
        }
        if (categoryRequest.parentCategoryId() != null && !categoryRepository.existsById(categoryRequest.parentCategoryId())) {
            log.error("Parent category not found with provided parent_category_id: {}. Cannot process the request", categoryRequest.categoryName());
            throw new ResourceNotFoundException("Category", String.valueOf(categoryRequest.parentCategoryId()));
        }
        log.info("Create category request is accepted. Proceeding to create category");
        Category category = categoryMapper.toEntity(categoryRequest);
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    public CategoryResponse getCategory(Long categoryId) {
        log.debug("Fetching category with ID: {}", categoryId);
        CategoryResponse response = categoryRepository.findById(categoryId)
                .map(categoryMapper::toResponseWithProducts)
                .orElseThrow(() -> {
                    log.error("Category not found with ID: {}", categoryId);
                    return new ResourceNotFoundException("Category", String.valueOf(categoryId));
                });
        log.info("Successfully retrieved category with ID: {}", categoryId);
        return response;
    }

    public List<CategoryResponse> getParentCategories() {
        log.debug("Fetching all parent categories");
        List<CategoryResponse> results = categoryRepository.findByParentCategoryIdIsNullAndIsActiveIsTrue()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
        log.info("Successfully fetched {} parent categories", results.size());
        return results;
    }

    public List<CategoryResponse> getChildren(Long categoryId) {
        log.debug("Fetching child categories for parent ID: {}", categoryId);
        if (!categoryRepository.existsById(categoryId)) {
            log.error("Parent category not found with ID: {}", categoryId);
            throw new ResourceNotFoundException("Category", String.valueOf(categoryId));
        }
        List<CategoryResponse> results = categoryRepository.findByParentCategoryId(categoryId)
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
        log.info("Successfully fetched {} child categories for parent ID: {}", results.size(), categoryId);
        return results;
    }

    public CategoryResponse updateCategory(Long categoryId, CategoryRequest request) {
        log.info("Attempting to update category ID: {} with name: {}", categoryId, request.categoryName());
        
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> {
                    log.error("Category not found with ID: {} for update", categoryId);
                    return new ResourceNotFoundException("Category", String.valueOf(categoryId));
                });
        if (request.parentCategoryId() != null && !categoryRepository.existsById(request.parentCategoryId())) {
            log.error("Parent category not found with ID: {}", request.parentCategoryId());
            throw new ResourceNotFoundException("Parent category", String.valueOf(request.parentCategoryId()));
        }
        if (request.categoryName() != null && categoryRepository.existsByCategoryNameIgnoreCase(request.categoryName())) {
            log.warn("Category with name {} already exists. Cannot update category ID: {}", request.categoryName(), categoryId);
            throw new BadRequestException("Category is already present");
        }

        existingCategory.setCategoryName(request.categoryName());
        existingCategory.setDescription(request.description());
        existingCategory.setUpdatedAt(LocalDateTime.now());

        CategoryResponse response = categoryMapper.toResponse(categoryRepository.save(existingCategory));
        log.info("Successfully updated category ID: {}", categoryId);
        return response;
    }
    public void deleteCategory(Long categoryId) {
        log.info("Attempting to delete category ID: {}", categoryId);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> {
                    log.error("Category not found with ID: {} for deletion", categoryId);
                    return new ResourceNotFoundException("Category not found: " + categoryId);
                });
        categoryRepository.delete(category);
        log.info("Successfully deleted category ID: {}", categoryId);
    }
}
