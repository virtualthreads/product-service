package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.PageRequestDTO;
import com.aeropelican.productservice.dto.request.ProductImagesCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductImagesUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductImagesResponseDTO;
import com.aeropelican.productservice.service.ProductImagesService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-images")
@RequiredArgsConstructor
@Validated
public class ProductImagesController {

    private final ProductImagesService productImagesService;

    @GetMapping("/{page}/{size}/{sortBy}/{sortDir}")
    public ResponseEntity<ApiResponse<PageResponse<ProductImagesResponseDTO>>> getAllImages(

            @PositiveOrZero
            @PathVariable Integer page,

            @Positive
            @Max(100)
            @PathVariable Integer size,

            @NotBlank
            @PathVariable String sortBy,

            @Pattern(
                    regexp = "ASC|DESC",
                    flags = Pattern.Flag.CASE_INSENSITIVE)
            @PathVariable String sortDir) {

        PageRequestDTO request = new PageRequestDTO();

        request.setPage(page);
        request.setSize(size);
        request.setSortBy(sortBy);
        request.setSortDir(sortDir);

        return ResponseEntity.ok(
                ApiResponse.success(
                        productImagesService.listImages(request),
                        "Images fetched successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductImagesResponseDTO>> getImage(

            @Positive
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        productImagesService.getImage(id),
                        "Image fetched successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductImagesResponseDTO>> createImage(

            @Valid
            @RequestBody ProductImagesCreateRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        productImagesService.createImage(request),
                        "Image created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductImagesResponseDTO>> updateImage(

            @Positive
            @PathVariable Integer id,

            @Valid
            @RequestBody ProductImagesUpdateRequestDTO request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        productImagesService.updateImage(id, request),
                        "Image updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteImage(

            @Positive
            @PathVariable Integer id) {

        productImagesService.deleteImage(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        null,
                        "Image deleted successfully"));
    }
}