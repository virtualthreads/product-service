package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.PageRequestDTO;
import com.aeropelican.productservice.dto.request.ProductVariantsCreateRequestDTO;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductVariantsResponseDTO;
import com.aeropelican.productservice.dto.response.ProductVariantsUpdateRequestDTO;
import com.aeropelican.productservice.service.ProductVariantsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-variants")
@RequiredArgsConstructor
@Validated
public class ProductVariantsController {

    private final ProductVariantsService productVariantsService;

    @GetMapping("/{page}/{size}/{sortBy}/{sortDir}")
    public ResponseEntity<ApiResponse<PageResponse<ProductVariantsResponseDTO>>> getAllVariants(

            @PositiveOrZero(message = "Page cannot be negative")
            @PathVariable Integer page,

            @Positive(message = "Size must be greater than zero")
            @Max(value = 100, message = "Maximum page size is 100")
            @PathVariable Integer size,

            @NotBlank(message = "Sort field cannot be blank")
            @PathVariable String sortBy,

            @Pattern(
                    regexp = "ASC|DESC",
                    flags = Pattern.Flag.CASE_INSENSITIVE,
                    message = "Sort direction must be ASC or DESC")
            @PathVariable String sortDir) {

        PageRequestDTO request = new PageRequestDTO();
        request.setPage(page);
        request.setSize(size);
        request.setSortBy(sortBy);
        request.setSortDir(sortDir);

        return ResponseEntity.ok(
                ApiResponse.success(
                        productVariantsService.listVariants(request),
                        "Variants fetched successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductVariantsResponseDTO>> getVariant(

            @Positive(message = "Variant ID must be positive")
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        productVariantsService.getVariant(id),
                        "Variant fetched successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductVariantsResponseDTO>> createVariant(

            @Valid
            @RequestBody ProductVariantsCreateRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        productVariantsService.createVariant(request),
                        "Variant created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductVariantsResponseDTO>> updateVariant(

            @Positive(message = "Variant ID must be positive")
            @PathVariable Integer id,

            @Valid
            @RequestBody ProductVariantsUpdateRequestDTO request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        productVariantsService.updateVariant(id, request),
                        "Variant updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteVariant(

            @Positive(message = "Variant ID must be positive")
            @PathVariable Integer id) {

        productVariantsService.deleteVariant(id);

        return ResponseEntity.ok(
                ApiResponse.success(null,
                        "Variant deleted successfully"));
    }
}