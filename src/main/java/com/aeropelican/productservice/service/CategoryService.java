package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.CategoryRequestDTO;
import com.aeropelican.productservice.dto.response.CategoryResponseDTO;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.exceptions.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.CategoryMapper;
import com.aeropelican.productservice.mapper.PageResponseMapper;
import com.aeropelican.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public PageResponse<CategoryResponseDTO> fetchAllCategories(
            Integer page,
            Integer size,
            String sortBy,
            String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("DESC")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Category> pageResult = categoryRepository.findAll(pageable);

        List<CategoryResponseDTO> content = pageResult.getContent()
                .stream()
                .map(CategoryMapper::toResponseDTO)
                .toList();

        return PageResponseMapper.toPageResponse(pageResult, content);
    }

    public CategoryResponseDTO getCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category",
                                String.valueOf(id)));

        return CategoryMapper.toResponseDTO(category);
    }

    public CategoryResponseDTO createCategory(CategoryRequestDTO request) {

        Category category = CategoryMapper.toEntity(request);

        if (request.getParentCategoryId() != null) {

            Category parent = categoryRepository.findById(request.getParentCategoryId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Category",
                                    String.valueOf(request.getParentCategoryId())));

            category.setParentCategory(parent);
        }

        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
        category.setIsActive(true);

        return CategoryMapper.toResponseDTO(
                categoryRepository.save(category)
        );
    }

    public CategoryResponseDTO updateCategory(
            Long id,
            CategoryRequestDTO request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category",
                                String.valueOf(id)));

        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        if (request.getParentCategoryId() != null) {

            Category parent = categoryRepository.findById(request.getParentCategoryId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Category",
                                    String.valueOf(request.getParentCategoryId())));

            category.setParentCategory(parent);
        } else {
            category.setParentCategory(null);
        }

        category.setUpdatedAt(LocalDateTime.now());

        return CategoryMapper.toResponseDTO(
                categoryRepository.save(category)
        );
    }
}