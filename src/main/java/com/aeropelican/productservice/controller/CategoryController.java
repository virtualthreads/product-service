package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.CategoryRequestDTO;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.CategoryResponseDTO;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{page}/{size}/{sortBy}/{sortDir}")
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponseDTO>>> getAllCategories(
            @PathVariable Integer page,
            @PathVariable Integer size,
            @PathVariable String sortBy,
            @PathVariable String sortDir) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        categoryService.fetchAllCategories(page, size, sortBy, sortDir),
                        "Categories fetched successfully"
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> getCategory(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        categoryService.getCategory(id),
                        "Category fetched successfully"
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> createCategory(
            @RequestBody CategoryRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                categoryService.createCategory(request),
                                "Category created successfully"
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDTO request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        categoryService.updateCategory(id, request),
                        "Category updated successfully"
                )
        );
    }
}