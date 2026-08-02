package com.aeropelican.productservice.service;

<<<<<<< HEAD
import com.aeropelican.productservice.dto.request.CategoryRequest;
import com.aeropelican.productservice.dto.response.CategoryResponse;
=======
import com.aeropelican.productservice.dto.request.CategoryRequestDTO;
import com.aeropelican.productservice.dto.request.PageRequestDTO;
import com.aeropelican.productservice.dto.response.CategoryResponseDTO;
import com.aeropelican.productservice.dto.response.PageResponse;
>>>>>>> 47ca83c (Added Validations and changes  of Product,Category, Product_Variants,Product_Images API's.)
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.exceptions.BadRequestException;
import com.aeropelican.productservice.exceptions.CategoryNotFound;
import com.aeropelican.productservice.exceptions.ProductNotFound;
import com.aeropelican.productservice.exceptions.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.CategoryMapper;
import com.aeropelican.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        if (categoryRepository.existsByCategoryNameIgnoreCase(categoryRequest.categoryName())) {
            throw new BadRequestException("Category '%s' already exist".formatted(categoryRequest.categoryName()));
        }
        if (categoryRequest.parentCategoryId() != null && categoryRepository.existsById(categoryRequest.parentCategoryId())) {
            throw new ResourceNotFoundException("Category", String.valueOf(categoryRequest.parentCategoryId()));
        }
        Category category = categoryMapper.toEntity(categoryRequest);
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

<<<<<<< HEAD
    public CategoryResponse getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(categoryMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Category", String.valueOf(categoryId)));
    }

    public List<CategoryResponse> getParentCategories() {
        return categoryRepository.findByParentCategoryIdIsNullAndIsActiveIsTrue()
                .stream()
                .map(categoryMapper::toResponse)
=======
    public PageResponse<CategoryResponseDTO> fetchAllCategories(PageRequestDTO requestDTO) {
        Sort sort = requestDTO.getSortDir().equalsIgnoreCase("DESC")
                            ? Sort.by(requestDTO.getSortBy()).descending()
                             : Sort.by(requestDTO.getSortBy()).ascending();

        Pageable pageable = PageRequest.of(requestDTO.getPage(),requestDTO.getSize(), sort);
        Page<Category> pageResult = categoryRepository.findAll(pageable);

        List<CategoryResponseDTO> content = pageResult.getContent()
                .stream()
                .map(CategoryMapper::toDTO)
>>>>>>> 47ca83c (Added Validations and changes  of Product,Category, Product_Variants,Product_Images API's.)
                .toList();
    }
<<<<<<< HEAD

    public List<CategoryResponse> getChildren(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category", String.valueOf(categoryId));
=======
    public CategoryResponseDTO createCategory(CategoryRequestDTO request) throws BadRequestException {

        if (!request.getCategoryName().equalsIgnoreCase(request.getCategoryName())
                && categoryRepository.existsByCategoryNameIgnoreCase(request.getCategoryName())) {
            throw new BadRequestException("Category already exists");
        } else if (request.getParentCategoryId() != null &&
                !categoryRepository.existsById(request.getParentCategoryId())) {
            throw new BadRequestException("Parent category not found");
>>>>>>> 47ca83c (Added Validations and changes  of Product,Category, Product_Variants,Product_Images API's.)
        }
        return categoryRepository.findByParentCategoryId(categoryId)
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    public CategoryResponse updateCategory(Long categoryId, CategoryRequest request) {

        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", String.valueOf(categoryId)));
        if (request.parentCategoryId() != null && !categoryRepository.existsById(request.parentCategoryId())) {
            throw new ResourceNotFoundException("Parent category", String.valueOf(request.parentCategoryId()));
        }
        if (request.categoryName() != null && categoryRepository.existsByCategoryNameIgnoreCase(request.categoryName())) {
            throw new BadRequestException("Category is already present");
        }

        existingCategory.setCategoryName(request.categoryName());
        existingCategory.setDescription(request.description());
        existingCategory.setUpdatedAt(LocalDateTime.now());

        return categoryMapper.toResponse(categoryRepository.save(existingCategory));
    }

    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + categoryId));
        categoryRepository.delete(category);
    }
    //To delete a product
    public Category deleteCategory(long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFound("Category not found"));
        categoryRepository.delete(category);
        return category;
    }


}
