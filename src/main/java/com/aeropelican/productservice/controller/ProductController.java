package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.ProductRequest;
import com.aeropelican.productservice.dto.request.ProductResponse;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(@Valid @RequestBody ProductRequest request) {
        ProductResponse saved = productService.createProduct(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(saved, "Product created"));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProduct(@Positive(message = "Product ID must be a positive number") @PathVariable Long productId) {
        ProductResponse product = productService.getProduct(productId);
        return ResponseEntity.ok(ApiResponse.success(product, "Product fetched"));
    }

    @GetMapping("/brands/{brand}")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getProductsByBrand(
            @NotBlank(message = "Brand name cannot be blank")
            @Size(min = 1, max = 100, message = "Brand name must be between 1 and 100 characters")
            @PathVariable String brand) {
        List<ProductResponse> results = productService.getProductsByBrand(brand);
        return ResponseEntity.ok(ApiResponse.success(results, "Products fetched by brand"));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@Positive(message = "Product ID must be a positive number") @PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok(ApiResponse.success(null, "Product deleted"));
    }
}