package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.response.APIResponse;
import com.aeropelican.productservice.dto.response.ProductVariantResponseDTO;
import com.aeropelican.productservice.service.ProductVariantService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products/{productId}/variants")
@RequiredArgsConstructor
@Validated
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    @GetMapping("/{variantId}")
    public ResponseEntity<APIResponse<ProductVariantResponseDTO>> getVariant(

            @PathVariable("productId")
            @Min(value = 1, message = "Product Id must be greater than 0")
            Long productId,

            @PathVariable("variantId")
            @Min(value = 1, message = "Variant Id must be greater than 0")
            Long variantId) {

        return ResponseEntity.ok(
                APIResponse.success(
                        productVariantService.getById(variantId)
                )
        );
    }
}