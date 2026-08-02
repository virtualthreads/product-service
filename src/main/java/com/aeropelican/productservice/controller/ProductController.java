package com.aeropelican.productservice.controller;
import com.aeropelican.productservice.dto.request.PageRequestDTO;
import com.aeropelican.productservice.dto.request.ProductCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductResponseDTO;
import com.aeropelican.productservice.dto.response.Product_VariantsResponseDTO;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.mapper.PageResponseMapper;
import com.aeropelican.productservice.mapper.ProductMapper;
import com.aeropelican.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/{page}/{size}/{sortBy}/{sortDir}")
    public ResponseEntity<ApiResponse<PageResponse<ProductResponseDTO>>> getAllProducts(
            @Valid @RequestBody PageRequestDTO requestDTO) {
        PageResponse<ProductResponseDTO> result =
                productService.listProducts(requestDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.<PageResponse<ProductResponseDTO>>builder()
                        .success(true)
                        .message("Product details fetched successfully")
                        .data(result)
                        .timestamp(LocalDateTime.now())
                        .build());
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
    public ResponseEntity<ApiResponse<ProductResponseDTO>> create(@Valid @RequestBody ProductCreateRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(productService.createProduct(request), "Product created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> update(@PathVariable Integer id, @RequestBody ProductUpdateRequestDTO request) {

        return ResponseEntity.ok(ApiResponse.success(productService.updateProduct(id, request), "Product updated successfully"));
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse<Product>> deleteProduct(
            @PathVariable Integer productId) {
        Product product = productService.deleteProduct(productId);
        ApiResponse<Product> response = ApiResponse.<Product>builder()
                .data(product)
                .message("Product deleted successfully")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}