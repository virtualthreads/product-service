package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.CategoryRequestDTO;
import com.aeropelican.productservice.dto.response.CategoryResponseDTO;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.exceptions.BadRequestException;
import com.aeropelican.productservice.exceptions.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.CategoryMapper;
import com.aeropelican.productservice.mapper.PageResponseMapper;
import com.aeropelican.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // ==========================================
    // GET ALL CATEGORIES
    // ==========================================

    public PageResponse<CategoryResponseDTO> fetchAllCategories(
            Integer page,
            Integer size,
            String sortBy,
            String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("DESC")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        List<CategoryResponseDTO> content = categoryPage.getContent()
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();

        return PageResponseMapper.toPageResponse(categoryPage, content);
    }

    // ==========================================
    // GET CATEGORY BY ID
    // ==========================================

    public CategoryResponseDTO getCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id : " + categoryId));

        return CategoryMapper.toResponse(category);
    }

    // ==========================================
    // CREATE CATEGORY
    // ==========================================

    public CategoryResponseDTO createCategory(CategoryRequestDTO request) {

        if (categoryRepository.existsByCategoryName(request.getCategoryName())) {
            throw new BadRequestException("Category already exists.");
        }

        Category category = CategoryMapper.toEntity(request);

        category = categoryRepository.save(category);

        return CategoryMapper.toResponse(category);
    }

    // ==========================================
    // UPDATE CATEGORY
    // ==========================================

    public CategoryResponseDTO updateCategory(
            Long categoryId,
            CategoryRequestDTO request) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id : " + categoryId));

        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());
        category.setIsActive(request.getActive());

        category = categoryRepository.save(category);

        return CategoryMapper.toResponse(category);
    }

    // ==========================================
    // SEARCH CATEGORY
    // ==========================================

    public List<CategoryResponseDTO> searchCategories(String keyword) {

        List<Category> categories = categoryRepository
                .findByCategoryNameContainingIgnoreCase(keyword);

        return categories.stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }
}