package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.CategoryRequestDTO;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.CategoryResponseDTO;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{page}/{size}/{sortBy}/{sortDir}")
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponseDTO>>> getAllCategories(
            @PathVariable Integer page,
            @PathVariable Integer size,
            @PathVariable String sortBy,
            @PathVariable String sortDir) {

        PageResponse<CategoryResponseDTO> result = categoryService.fetchAllCategories(page, size, sortBy, sortDir);
        return ResponseEntity.ok(ApiResponse.success(
                result,
                "Categories fetched successfully"
        ));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> getCategory(@PathVariable("categoryId") Long catId) {
        CategoryResponseDTO category = categoryService.getCategory(catId);
        return ResponseEntity.ok(ApiResponse.success(category, "Category details fetched successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> createCategory(@RequestBody CategoryRequestDTO request) {
        CategoryResponseDTO response = categoryService.createCategory(request);
        return ResponseEntity.ok(ApiResponse.success(
                response,
                "Category successfully created."
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDTO request) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        categoryService.updateCategory(id, request),
                        "Category updated successfully"
                )
        );
    }
}
