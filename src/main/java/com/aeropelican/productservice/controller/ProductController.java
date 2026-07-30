package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.ProductCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.APIResponse;
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
    public ResponseEntity<APIResponse<PageResponse<ProductResponseDTO>>> getAllProducts(
            @PathVariable Integer page,
            @PathVariable Integer size,
            @PathVariable String sortBy,
            @PathVariable String sortDir) {

        PageResponse<ProductResponseDTO> result = productService.listProducts(page, size, sortBy, sortDir);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(APIResponse.<PageResponse<ProductResponseDTO>>builder()
                        .success(true)
                        .message("Product details fetched successfully")
                        .data(result)
                        .timestamp(LocalDateTime.now())
                        .build()
                );
    }

    @GetMapping("/{pid}")
    public ResponseEntity<APIResponse<ProductResponseDTO>> getProduct(@PathVariable(name = "pid") Long productId) {
        ProductResponseDTO product = productService.getProduct(productId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(APIResponse.<ProductResponseDTO>builder()
                        .success(true)
                        .message("Product found")
                        .data(product)
                        .timestamp(LocalDateTime.now())
                        .build()
                );
    }

    @PostMapping
    public ResponseEntity<APIResponse<ProductResponseDTO>> create(@RequestBody ProductCreateRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.success(productService.createProduct(request), "Product created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<ProductResponseDTO>> update(@PathVariable Long id, @RequestBody ProductUpdateRequestDTO request) {

        return ResponseEntity.ok(APIResponse.success(productService.updateProduct(id, request), "Product updated successfully"));
    }
}
