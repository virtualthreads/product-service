package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.ProductVariantResponse;
import com.aeropelican.productservice.dto.response.ProductVariantResponseDTO;
import com.aeropelican.productservice.repository.ProductVariantRepository;
import com.aeropelican.productservice.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/{productId}/variants")
@RequiredArgsConstructor
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductVariantResponse>>> getVariants(@PathVariable Long productId) {
        return ResponseEntity.ok(ApiResponse.success(
                productVariantService.getProductVariants(productId),
                "Variants fetched"
        ));
    }

    @GetMapping("/{variantId}")
    public ResponseEntity<ApiResponse<ProductVariantResponse>> getVariant(@PathVariable Long productId, @PathVariable Long variantId) {
        return ResponseEntity.ok(ApiResponse.success(
                productVariantService.getProductVariant(productId, variantId),
                "Fetched Product Variant"
        ));
    }
}
