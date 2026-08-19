package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.ProductVariantsCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductVariantsUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductVariantsResponseDTO;
import com.aeropelican.productservice.service.ProductVariantsService;
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
@RequestMapping("/api/v1/variants")
@RequiredArgsConstructor
@Validated
public class ProductVariantsController {

    private final ProductVariantsService productVariantsService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductVariantsResponseDTO>> createVariant(@Valid @RequestBody ProductVariantsCreateRequestDTO request) {
        return new ResponseEntity<>(productVariantsService.saveVariant(request), HttpStatus.CREATED);
    }

    // URL: GET /api/v1/variants/0/10
    @GetMapping("/{page}/{size}")
    public ResponseEntity<ApiResponse<PageResponse<ProductVariantsResponseDTO>>> getAllVariants(
            @PathVariable @Min(value = 0, message = "Page index must be zero or positive") int page,
            @PathVariable @Positive(message = "Page size must be greater than zero") int size) {
        return ResponseEntity.ok(productVariantsService.getAllVariants(page, size, "variantId", "ASC"));
    }

    // URL: GET /api/v1/variants/0/10/variantName
    @GetMapping("/{page}/{size}/{sortBy}")
    public ResponseEntity<ApiResponse<PageResponse<ProductVariantsResponseDTO>>> getAllVariantsWithSort(
            @PathVariable @Min(value = 0, message = "Page index must be zero or positive") int page,
            @PathVariable @Positive(message = "Page size must be greater than zero") int size,
            @PathVariable String sortBy) {
        return ResponseEntity.ok(productVariantsService.getAllVariants(page, size, sortBy, "ASC"));
    }

    // URL: GET /api/v1/variants/0/10/variantName/desc
    @GetMapping("/{page}/{size}/{sortBy}/{sortDirection}")
    public ResponseEntity<ApiResponse<PageResponse<ProductVariantsResponseDTO>>> getAllVariantsWithSortDir(
            @PathVariable @Min(value = 0, message = "Page index must be zero or positive") int page,
            @PathVariable @Positive(message = "Page size must be greater than zero") int size,
            @PathVariable String sortBy,
            @PathVariable @Pattern(regexp = "(?i)asc|desc", message = "Sort direction must be 'ASC' or 'DESC'") String sortDirection) {
        return ResponseEntity.ok(productVariantsService.getAllVariants(page, size, sortBy, sortDirection));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<ProductVariantsResponseDTO>> getVariantById(
            @PathVariable @Positive(message = "Variant ID must be positive") Long id) {
        return ResponseEntity.ok(productVariantsService.getVariantById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductVariantsResponseDTO>> updateVariant(
            @PathVariable @Positive(message = "Variant ID must be positive") Long id,
            @Valid @RequestBody ProductVariantsUpdateRequestDTO request) {
        return ResponseEntity.ok(productVariantsService.updateVariant(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteVariant(
            @PathVariable @Positive(message = "Variant ID must be positive") Long id) {
        return ResponseEntity.ok(productVariantsService.deleteVariant(id));
    }
}