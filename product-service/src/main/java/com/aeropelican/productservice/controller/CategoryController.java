package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.CategoryRequest;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.CategoryResponse;
import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.service.CategoryService;
import com.aeropelican.productservice.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@Valid @RequestBody CategoryRequest request) {
        CategoryResponse saved = categoryService.createCategory(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(saved, "Category created"));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategory(@Positive(message = "Category ID must be a positive number") @PathVariable Long categoryId) {
        CategoryResponse category = categoryService.getCategory(categoryId);
        return ResponseEntity.ok(ApiResponse.success(category, "Category fetched"));
    }

    @GetMapping("/parents")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getParentCategories() {
        List<CategoryResponse> results = categoryService.getParentCategories();
        return ResponseEntity.ok(ApiResponse.success(results, "Parent categories fetched"));
    }

    @GetMapping("/{categoryId}/children")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getChildren(@Positive(message = "Category ID must be a positive number") @PathVariable Long categoryId) {
        List<CategoryResponse> results = categoryService.getChildren(categoryId);
        return ResponseEntity.ok(ApiResponse.success(results, "Child categories fetched"));
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getProductsByCategory(@Positive(message = "Category ID must be a positive number") @PathVariable Long categoryId) {
        List<ProductResponse> results = productService.getProductsByCategory(categoryId);
        return ResponseEntity.ok(ApiResponse.success(results, "Products fetched by category"));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(
            @Positive(message = "Category ID must be a positive number") @PathVariable Long categoryId,
            @Valid @RequestBody CategoryRequest request) {
        CategoryResponse updated = categoryService.updateCategory(categoryId, request);
        return ResponseEntity.ok(ApiResponse.success(updated, "Category updated"));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@Positive(message = "Category ID must be a positive number") @PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(ApiResponse.success(null, "Category deleted"));
    }
}
