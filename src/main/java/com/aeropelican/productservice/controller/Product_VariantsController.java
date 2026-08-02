package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.ProductVariantsCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductVariantsUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.Product_VariantsResponseDTO;
import com.aeropelican.productservice.entity.Product_Variants;
import com.aeropelican.productservice.service.ProductVariantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product-variants")
@RequiredArgsConstructor
public class Product_VariantsController {

    private final ProductVariantService productVariantService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product_VariantsResponseDTO>>> getAllVariants() {

        return ResponseEntity.ok(
                ApiResponse.<List<Product_VariantsResponseDTO>>builder()
                        .success(true)
                        .message("Product variants fetched successfully")
                        .data(productVariantService.getAllVariants())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product_VariantsResponseDTO>> getVariant(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                ApiResponse.<Product_VariantsResponseDTO>builder()
                        .success(true)
                        .message("Product Variant found")
                        .data(productVariantService.getVariant(id))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product_VariantsResponseDTO>> create(
            @Valid @RequestBody ProductVariantsCreateRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                productVariantService.createVariant(request),
                                "Product Variant created successfully"
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product_VariantsResponseDTO>> update(
            @PathVariable Integer id,
            @RequestBody ProductVariantsUpdateRequestDTO request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        productVariantService.updateVariant(id, request),
                        "Product Variant updated successfully"
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Product_Variants>> delete(
            @PathVariable Integer id) {

        Product_Variants variant = productVariantService.deleteVariant(id);

        return ResponseEntity.ok(
                ApiResponse.<Product_Variants>builder()
                        .success(true)
                        .message("Product Variant deleted successfully")
                        .data(variant)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}