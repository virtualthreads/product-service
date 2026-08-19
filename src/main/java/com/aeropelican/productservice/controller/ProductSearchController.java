package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.ProductImageCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductImageUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.ProductImageResponseDTO;
import com.aeropelican.productservice.service.ProductImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    @GetMapping("/variant/{variantId}")
    public ResponseEntity<List<ProductImageResponseDTO>> getImagesByVariant(@PathVariable Long variantId) {
        return ResponseEntity.ok(productImageService.getImagesByVariantId(variantId));
    }

    @PostMapping
    public ResponseEntity<ProductImageResponseDTO> createImage(@Valid @RequestBody ProductImageCreateRequestDTO requestDTO) {
        return new ResponseEntity<>(productImageService.createImage(requestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{imageId}")
    public ResponseEntity<ProductImageResponseDTO> updateImage(
            @PathVariable Long imageId,
            @RequestBody ProductImageUpdateRequestDTO requestDTO) {
        return ResponseEntity.ok(productImageService.updateImage(imageId, requestDTO));
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long imageId) {
        productImageService.deleteImage(imageId);
        return ResponseEntity.noContent().build();
    }
}