package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.ProductCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductResponseDTO;
import com.aeropelican.productservice.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDTO>> createProduct(@Valid @RequestBody ProductCreateRequestDTO request) {
        return new ResponseEntity<>(
                ApiResponse.success(productService.createProduct(request), "Product created successfully"),
                HttpStatus.CREATED
        );
    }

    // URL: GET /api/v1/products/0/10
    @GetMapping("/{page}/{size}")
    public ResponseEntity<ApiResponse<PageResponse<ProductResponseDTO>>> getAllProducts(
            @PathVariable @Min(value = 0, message = "Page index must be zero or positive") int page,
            @PathVariable @Positive(message = "Page size must be greater than zero") int size) {
        return ResponseEntity.ok(
                ApiResponse.success(productService.listProducts(page, size, "productId", "ASC"), "Products retrieved successfully")
        );
    }

    // URL: GET /api/v1/products/0/10/brand
    @GetMapping("/{page}/{size}/{sortBy}")
    public ResponseEntity<ApiResponse<PageResponse<ProductResponseDTO>>> getAllProductsWithSort(
            @PathVariable @Min(value = 0, message = "Page index must be zero or positive") int page,
            @PathVariable @Positive(message = "Page size must be greater than zero") int size,
            @PathVariable String sortBy) {
        return ResponseEntity.ok(
                ApiResponse.success(productService.listProducts(page, size, sortBy, "ASC"), "Products retrieved successfully")
        );
    }

    // URL: GET /api/v1/products/0/10/brand/desc
    @GetMapping("/{page}/{size}/{sortBy}/{sortDirection}")
    public ResponseEntity<ApiResponse<PageResponse<ProductResponseDTO>>> getAllProductsWithSortDir(
            @PathVariable @Min(value = 0, message = "Page index must be zero or positive") int page,
            @PathVariable @Positive(message = "Page size must be greater than zero") int size,
            @PathVariable String sortBy,
            @PathVariable @Pattern(regexp = "(?i)asc|desc", message = "Sort direction must be 'ASC' or 'DESC'") String sortDirection) {
        return ResponseEntity.ok(
                ApiResponse.success(productService.listProducts(page, size, sortBy, sortDirection), "Products retrieved successfully")
        );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> getProductById(
            @PathVariable @Positive(message = "Product ID must be positive") Integer id) {
        return ResponseEntity.ok(
                ApiResponse.success(productService.getProduct(id), "Product retrieved successfully")
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> updateProduct(
            @PathVariable @Positive(message = "Product ID must be positive") Integer id,
            @Valid @RequestBody ProductUpdateRequestDTO request) {
        return ResponseEntity.ok(
                ApiResponse.success(productService.updateProduct(id, request), "Product updated successfully")
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProduct(
            @PathVariable @Positive(message = "Product ID must be positive") Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(
                ApiResponse.success("Product deleted successfully", "Product deleted successfully")
        );
    }
}