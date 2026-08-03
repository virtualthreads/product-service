package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.CategoryRequestDTO;
import com.aeropelican.productservice.dto.response.APIResponse;
import com.aeropelican.productservice.dto.response.CategoryResponseDTO;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{page}/{size}/{sortBy}/{sortDir}")
    public ResponseEntity<APIResponse<PageResponse<CategoryResponseDTO>>> getAllCategories(
            @PathVariable @Min(value = 0, message = "Page cannot be negative") Integer page,
            @PathVariable @Min(value = 1, message = "Size must be greater than 0") Integer size,
            @PathVariable String sortBy,
            @PathVariable String sortDir) {

        PageResponse<CategoryResponseDTO> result =
                categoryService.fetchAllCategories(page, size, sortBy, sortDir);

        return ResponseEntity.ok(
                APIResponse.success(
                        result,
                        "Categories fetched successfully"
                )
        );
    }

    @GetMapping("/search")
    public ResponseEntity<APIResponse<List<CategoryResponseDTO>>> searchCategories(
            @RequestParam String keyword) {

        List<CategoryResponseDTO> categories =
                categoryService.searchCategories(keyword);

        return ResponseEntity.ok(
                APIResponse.success(
                        categories,
                        "Categories fetched successfully"
                )
        );
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<APIResponse<CategoryResponseDTO>> getCategory(
            @PathVariable @Min(value = 1, message = "Category Id must be greater than 0")
            Long categoryId) {

        CategoryResponseDTO category =
                categoryService.getCategory(categoryId);

        return ResponseEntity.ok(
                APIResponse.success(
                        category,
                        "Category details fetched successfully"
                )
        );
    }

    @PostMapping
    public ResponseEntity<APIResponse<CategoryResponseDTO>> createCategory(
            @Valid @RequestBody CategoryRequestDTO request) {

        CategoryResponseDTO response =
                categoryService.createCategory(request);

        return ResponseEntity.ok(
                APIResponse.success(
                        response,
                        "Category successfully created."
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<CategoryResponseDTO>> updateCategory(
            @PathVariable @Min(value = 1, message = "Category Id must be greater than 0")
            Long id,
            @Valid @RequestBody CategoryRequestDTO request) {

        return ResponseEntity.ok(
                APIResponse.success(
                        categoryService.updateCategory(id, request),
                        "Category updated successfully"
                )
        );
    }
}