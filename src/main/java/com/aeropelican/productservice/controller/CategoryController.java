package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.CategoryRequestDTO;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.CategoryResponseDTO;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@Validated
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> createCategory(@Valid @RequestBody CategoryRequestDTO request) {
        return new ResponseEntity<>(
                ApiResponse.success(categoryService.createCategory(request), "Category created successfully"),
                HttpStatus.CREATED
        );
    }

    // URL: GET /api/v1/categories/0/10
    @GetMapping("/{page}/{size}")
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponseDTO>>> getAllCategories(
            @PathVariable @Min(value = 0, message = "Page index must be zero or positive") int page,
            @PathVariable @Positive(message = "Page size must be greater than zero") int size) {
        return ResponseEntity.ok(
                ApiResponse.success(categoryService.fetchAllCategories(page, size, "categoryId", "ASC"), "Categories fetched successfully")
        );
    }

    // URL: GET /api/v1/categories/0/10/categoryName
    @GetMapping("/{page}/{size}/{sortBy}")
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponseDTO>>> getAllCategoriesWithSort(
            @PathVariable @Min(value = 0, message = "Page index must be zero or positive") int page,
            @PathVariable @Positive(message = "Page size must be greater than zero") int size,
            @PathVariable String sortBy) {
        return ResponseEntity.ok(
                ApiResponse.success(categoryService.fetchAllCategories(page, size, sortBy, "ASC"), "Categories fetched successfully")
        );
    }

    // URL: GET /api/v1/categories/0/10/categoryName/desc
    @GetMapping("/{page}/{size}/{sortBy}/{sortDirection}")
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponseDTO>>> getAllCategoriesWithSortDir(
            @PathVariable @Min(value = 0, message = "Page index must be zero or positive") int page,
            @PathVariable @Positive(message = "Page size must be greater than zero") int size,
            @PathVariable String sortBy,
            @PathVariable @Pattern(regexp = "(?i)asc|desc", message = "Sort direction must be 'ASC' or 'DESC'") String sortDirection) {
        return ResponseEntity.ok(
                ApiResponse.success(categoryService.fetchAllCategories(page, size, sortBy, sortDirection), "Categories fetched successfully")
        );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> getCategoryById(
            @PathVariable @Positive(message = "Category ID must be positive") Long id) {
        return ResponseEntity.ok(
                ApiResponse.success(categoryService.getCategory(id), "Category details fetched successfully")
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> updateCategory(
            @PathVariable @Positive(message = "Category ID must be positive") Long id,
            @Valid @RequestBody CategoryRequestDTO request) {
        return ResponseEntity.ok(
                ApiResponse.success(categoryService.updateCategory(id, request), "Category updated successfully")
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCategory(
            @PathVariable @Positive(message = "Category ID must be positive") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(
                ApiResponse.success("Category deleted successfully", "Category deleted successfully")
        );
    }
}