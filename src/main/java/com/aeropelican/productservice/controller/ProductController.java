package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.ProductCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductResponseDTO;
import com.aeropelican.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{page}/{size}/{sortBy}/{sortDir}")
    public ResponseEntity<ApiResponse<PageResponse<ProductResponseDTO>>> getAllProducts(
            @PathVariable Integer page,
            @PathVariable Integer size,
            @PathVariable String sortBy,
            @PathVariable String sortDir) {

        PageResponse<ProductResponseDTO> result = productService.listProducts(page, size, sortBy, sortDir);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.<PageResponse<ProductResponseDTO>>builder()
                        .success(true)
                        .message("Product details fetched successfully")
                        .data(result)
                        .timestamp(LocalDateTime.now())
                        .build()
                );
    }

    @GetMapping("/{pid}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> getProduct(@PathVariable(name = "pid") Integer productId) {
        ProductResponseDTO product = productService.getProduct(productId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.<ProductResponseDTO>builder()
                        .success(true)
                        .message("Product found")
                        .data(product)
                        .timestamp(LocalDateTime.now())
                        .build()
                );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDTO>> create(@RequestBody ProductCreateRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(productService.createProduct(request), "Product created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> update(@PathVariable Integer id, @RequestBody ProductUpdateRequestDTO request) {

        return ResponseEntity.ok(ApiResponse.success(productService.updateProduct(id, request), "Product updated successfully"));
    }
}
