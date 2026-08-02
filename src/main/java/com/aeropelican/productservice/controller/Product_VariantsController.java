package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.*;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductResponseDTO;
import com.aeropelican.productservice.dto.response.Product_VariantsResponseDTO;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.entity.Product_Variants;
import com.aeropelican.productservice.service.Product_VariantsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/product-variants")
@RequiredArgsConstructor
public class Product_VariantsController {
    private final Product_VariantsService productVariantsService;

    @PostMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<Product_VariantsResponseDTO>>> getAllProductVariants(
            @Valid @RequestBody PageRequestDTO requestDTO) {

        PageResponse<Product_VariantsResponseDTO> result =
                productVariantsService.listProductVariants(requestDTO);

        return ResponseEntity.ok(
                ApiResponse.<PageResponse<Product_VariantsResponseDTO>>builder()
                        .success(true)
                        .message("Product variants fetched successfully")
                        .data(result)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // Get by ID
    @GetMapping("/{variantId}")
    public ResponseEntity<ApiResponse<Product_VariantsResponseDTO>> getProductVariant(
            @PathVariable @Positive Integer variantId) {

        Product_VariantsResponseDTO response =
                productVariantsService.getProductVariant(variantId);

        return ResponseEntity.ok(
                ApiResponse.<Product_VariantsResponseDTO>builder()
                        .success(true)
                        .message("Product Variant fetched successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // Create
    @PostMapping
    public ResponseEntity<ApiResponse<Product_VariantsResponseDTO>> create(
            @Valid @RequestBody ProductVariantsCreateRequestDTO request) {

        Product_VariantsResponseDTO response =
                productVariantsService.createProductVariant(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response, "Product Variant created successfully"));
    }

    // Update
    @PutMapping("/{variantId}")
    public ResponseEntity<ApiResponse<Product_VariantsResponseDTO>> update(
            @PathVariable @Positive Integer variantId,
            @Valid @RequestBody ProductVariantsUpdateRequestDTO request) {

        Product_VariantsResponseDTO response =
                productVariantsService.updateProductVariant(variantId, request);

        return ResponseEntity.ok(
                ApiResponse.success(response, "Product Variant updated successfully"));
    }

    // Delete
    @DeleteMapping("/{variantId}")
    public ResponseEntity<ApiResponse<Void>> deleteProductVariant(
            @PathVariable @Positive Integer variantId) {

        productVariantsService.deleteProductVariant(variantId);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Product Variant deleted successfully")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
