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

    public CategoryResponseDTO getCategory(Long catId) {
        return categoryRepository.findById(catId)
                .map(CategoryMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Category", String.valueOf(catId)));

    }

    public PageResponse<CategoryResponseDTO> fetchAllCategories(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("DESC")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Category> pageResult = categoryRepository.findAll(pageable);

        List<CategoryResponseDTO> content = pageResult.stream()
                .map(CategoryMapper::toDTO)
                .toList();
        return PageResponseMapper.toPageResponse(pageResult, content);
    }

    public CategoryResponseDTO createCategory(CategoryRequestDTO request) throws BadRequestException {

        if (!request.getCategoryName().equalsIgnoreCase(request.getCategoryName())
                && categoryRepository.existsByCategoryNameIgnoreCase(request.getCategoryName())) {
            throw new BadRequestException("Category already exists");
        } else if (request.getParentCategoryId() != null &&
                !categoryRepository.existsById(request.getParentCategoryId())) {
            throw new BadRequestException("Parent category not found");
        }

        Category category = CategoryMapper.toEntity(request);
        category = categoryRepository.save(category);

        return CategoryMapper.toDTO(category);
    }

    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", String.valueOf(id)));

        String categoryName = request.getCategoryName().trim();

        if (!category.getCategoryName().equalsIgnoreCase(categoryName)
                && categoryRepository.existsByCategoryNameIgnoreCase(categoryName)) {
            throw new BadRequestException("Category name already exists.");
        }

        if (request.getParentCategoryId() == null) {
            category.setParentCategory(null);
        } else {
            if (request.getParentCategoryId().equals(id)) {
                throw new BadRequestException("Category cannot be its own parent.");
            }

            Category parent = categoryRepository.findById(request.getParentCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent Category", String.valueOf(request.getParentCategoryId())));
            category.setParentCategory(parent);
        }

        category.setCategoryName(categoryName);
        category.setDescription(request.getDescription());

        if (request.getActive() != null) {
            category.setIsActive(request.getActive());
        }

        return CategoryMapper.toDTO(categoryRepository.save(category));
    }
}
