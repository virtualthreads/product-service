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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@Valid @RequestBody CategoryRequest request) {
        log.info("Received a request to create a category: {}", request.categoryName());

        CategoryResponse saved = categoryService.createCategory(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(saved, "Category created"));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategory(@Positive(message = "Category ID must be a positive number") @PathVariable Long categoryId) {
        log.debug("Fetching category with ID: {}", categoryId);
        CategoryResponse category = categoryService.getCategory(categoryId);
        log.info("Successfully fetched category with ID: {}", categoryId);
        return ResponseEntity.ok(ApiResponse.success(category, "Category fetched"));
    }

    @GetMapping("/parents")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getParentCategories() {
        log.debug("Fetching all parent categories");
        List<CategoryResponse> results = categoryService.getParentCategories();
        log.info("Successfully fetched {} parent categories", results.size());
        return ResponseEntity.ok(ApiResponse.success(results, "Parent categories fetched"));
    }

    @GetMapping("/{categoryId}/children")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getChildren(@Positive(message = "Category ID must be a positive number") @PathVariable Long categoryId) {
        log.debug("Fetching child categories for parent ID: {}", categoryId);
        List<CategoryResponse> results = categoryService.getChildren(categoryId);
        log.info("Successfully fetched {} child categories for parent ID: {}", results.size(), categoryId);
        return ResponseEntity.ok(ApiResponse.success(results, "Child categories fetched"));
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getProductsByCategory(@Positive(message = "Category ID must be a positive number") @PathVariable Long categoryId) {
        log.debug("Fetching products for category ID: {}", categoryId);
        List<ProductResponse> results = productService.getProductsByCategory(categoryId);
        log.info("Successfully fetched {} products for category ID: {}", results.size(), categoryId);
        return ResponseEntity.ok(ApiResponse.success(results, "Products fetched by category"));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(
            @Positive(message = "Category ID must be a positive number") @PathVariable Long categoryId,
            @Valid @RequestBody CategoryRequest request) {
        log.info("Received request to update category ID: {} with name: {}", categoryId, request.categoryName());
        CategoryResponse updated = categoryService.updateCategory(categoryId, request);
        log.info("Successfully updated category ID: {}", categoryId);
        return ResponseEntity.ok(ApiResponse.success(updated, "Category updated"));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@Positive(message = "Category ID must be a positive number") @PathVariable Long categoryId) {
        log.info("Received request to delete category ID: {}", categoryId);
        categoryService.deleteCategory(categoryId);
        log.info("Successfully deleted category ID: {}", categoryId);
        return ResponseEntity.ok(ApiResponse.success(null, "Category deleted"));
    }
}
