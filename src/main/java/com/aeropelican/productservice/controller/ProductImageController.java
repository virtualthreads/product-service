package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.ProductImageCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductImageUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.APIResponse;
import com.aeropelican.productservice.dto.response.ProductImageResponseDTO;
import com.aeropelican.productservice.service.ProductImageService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
@Validated
public class ProductImageController {

    private final ProductImageService productImageService;

    // ==========================================
    // CREATE IMAGE
    // ==========================================

    @PostMapping
    public ResponseEntity<APIResponse<ProductImageResponseDTO>> createImage(
            @Valid @RequestBody ProductImageCreateRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.success(
                        productImageService.createImage(request),
                        "Product image created successfully"));
    }

    // ==========================================
    // GET IMAGE BY ID
    // ==========================================

    @GetMapping("/{imageId}")
    public ResponseEntity<APIResponse<ProductImageResponseDTO>> getImage(
            @PathVariable
            @Min(value = 1, message = "Image Id must be greater than 0")
            Long imageId) {

        return ResponseEntity.ok(
                APIResponse.success(
                        productImageService.getImage(imageId),
                        "Product image fetched successfully"));
    }

    // ==========================================
    // GET ALL IMAGES OF A VARIANT
    // ==========================================

    @GetMapping("/variant/{variantId}")
    public ResponseEntity<APIResponse<List<ProductImageResponseDTO>>> getImagesByVariant(
            @PathVariable
            @Min(value = 1, message = "Variant Id must be greater than 0")
            Long variantId) {

        return ResponseEntity.ok(
                APIResponse.success(
                        productImageService.getImagesByVariant(variantId),
                        "Product images fetched successfully"));
    }

    // ==========================================
    // UPDATE IMAGE
    // ==========================================

    @PutMapping("/{imageId}")
    public ResponseEntity<APIResponse<ProductImageResponseDTO>> updateImage(
            @PathVariable
            @Min(value = 1, message = "Image Id must be greater than 0")
            Long imageId,

            @Valid @RequestBody ProductImageUpdateRequestDTO request) {

        return ResponseEntity.ok(
                APIResponse.success(
                        productImageService.updateImage(imageId, request),
                        "Product image updated successfully"));
    }

    // ==========================================
    // DELETE IMAGE
    // ==========================================

    @DeleteMapping("/{imageId}")
    public ResponseEntity<APIResponse<String>> deleteImage(
            @PathVariable
            @Min(value = 1, message = "Image Id must be greater than 0")
            Long imageId) {

        productImageService.deleteImage(imageId);

        return ResponseEntity.ok(
                APIResponse.success(
                        "Product image deleted successfully",
                        "Product image deleted successfully"));
    }
}