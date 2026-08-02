package com.aeropelican.productservice.controller;

<<<<<<< HEAD
import com.aeropelican.productservice.dto.request.CategoryRequest;
import com.aeropelican.productservice.dto.request.ProductResponse;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.CategoryResponse;
import com.aeropelican.productservice.service.CategoryService;
import com.aeropelican.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
=======
import com.aeropelican.productservice.dto.request.CategoryRequestDTO;
import com.aeropelican.productservice.dto.request.PageRequestDTO;
import com.aeropelican.productservice.dto.request.ProductCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.CategoryResponseDTO;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductResponseDTO;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.mapper.CategoryMapper;
import com.aeropelican.productservice.mapper.PageResponseMapper;
import com.aeropelican.productservice.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
>>>>>>> 47ca83c (Added Validations and changes  of Product,Category, Product_Variants,Product_Images API's.)
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
=======
import java.time.LocalDateTime;
>>>>>>> 47ca83c (Added Validations and changes  of Product,Category, Product_Variants,Product_Images API's.)
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
<<<<<<< HEAD
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@RequestBody CategoryRequest request) {
        CategoryResponse saved = categoryService.createCategory(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(saved, "Category created"));
=======
    @PostMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponseDTO>>> getAllCategories(
            @Valid @RequestBody PageRequestDTO requestDTO) {

        PageResponse<CategoryResponseDTO> result =
                categoryService.fetchAllCategories(requestDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.<PageResponse<CategoryResponseDTO>>builder()
                        .success(true)
                        .message("Categories fetched successfully")
                        .data(result)
                        .timestamp(LocalDateTime.now())
                        .build());
>>>>>>> 47ca83c (Added Validations and changes  of Product,Category, Product_Variants,Product_Images API's.)
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategory(@PathVariable Long categoryId) {
        CategoryResponse category = categoryService.getCategory(categoryId);
        return ResponseEntity.ok(ApiResponse.success(category, "Category fetched"));
    }

<<<<<<< HEAD
    @GetMapping("/parents")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getParentCategories() {
        List<CategoryResponse> results = categoryService.getParentCategories();
        return ResponseEntity.ok(ApiResponse.success(results, "Parent categories fetched"));
=======
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> createCategory(@Valid @RequestBody CategoryRequestDTO request) {
        CategoryResponseDTO response = categoryService.createCategory(request);
        return ResponseEntity.ok(ApiResponse.success(
                response,
                "Category successfully created."
        ));
>>>>>>> 47ca83c (Added Validations and changes  of Product,Category, Product_Variants,Product_Images API's.)
    }

    @GetMapping("/{categoryId}/children")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getChildren(@PathVariable Long categoryId) {
        List<CategoryResponse> results = categoryService.getChildren(categoryId);
        return ResponseEntity.ok(ApiResponse.success(results, "Child categories fetched"));
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getProductsByCategory(@PathVariable Long categoryId) {
        List<ProductResponse> results = productService.getProductsByCategory(categoryId);
        return ResponseEntity.ok(ApiResponse.success(results, "Products fetched by category"));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody CategoryRequest request) {
        CategoryResponse updated = categoryService.updateCategory(categoryId, request);
        return ResponseEntity.ok(ApiResponse.success(updated, "Category updated"));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(ApiResponse.success(null, "Category deleted"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> deleteCategory(
            @PathVariable long categoryId) {
        Category category = categoryService.deleteCategory(categoryId);

        ApiResponse<Category> response = ApiResponse.<Category>builder()
                .data(category)
                .message("Product deleted successfully")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}
