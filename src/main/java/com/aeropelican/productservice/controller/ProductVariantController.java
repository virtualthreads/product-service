package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.ProductVariantResponse;
import com.aeropelican.productservice.dto.response.ProductVariantResponseDTO;
import com.aeropelican.productservice.repository.ProductVariantRepository;
import com.aeropelican.productservice.service.ProductVariantService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/{productId}/variants")
@RequiredArgsConstructor
@Slf4j
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductVariantResponse>>> getVariants(
            @Positive(message = "Product ID must be a positive number")
            @PathVariable Long productId) {
        log.debug("Fetching all variants for product ID: {}", productId);
        List<ProductVariantResponse> variants = productVariantService.getProductVariants(productId);
        log.info("Successfully fetched {} variants for product ID: {}", variants.size(), productId);
        return ResponseEntity.ok(ApiResponse.success(variants, "Variants fetched"));
    }

    @GetMapping("/{variantId}")
    public ResponseEntity<ApiResponse<ProductVariantResponse>> getVariant(
            @Positive(message = "Product ID must be a positive number")
            @PathVariable Long productId,
            @Positive(message = "Variant ID must be a positive number")
            @PathVariable Long variantId) {
        log.debug("Fetching variant ID: {} for product ID: {}", variantId, productId);
        ProductVariantResponse variant = productVariantService.getProductVariant(productId, variantId);
        log.info("Successfully fetched variant ID: {} for product ID: {}", variantId, productId);
        return ResponseEntity.ok(ApiResponse.success(variant, "Fetched Product Variant"));
    }
}
