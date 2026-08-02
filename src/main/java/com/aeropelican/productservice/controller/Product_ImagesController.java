package com.aeropelican.productservice.controller;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.request.PageRequestDTO;
import com.aeropelican.productservice.dto.request.ProductImagesCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductImagesUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductImagesResponseDTO;
import com.aeropelican.productservice.service.Product_ImagesService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

        @RestController
        @RequestMapping("/api/v1/product-images")
        @RequiredArgsConstructor
        @Validated
        public class Product_ImagesController {

            private final Product_ImagesService productImagesService;

            // List Images
            @PostMapping("/search")
            public ResponseEntity<ApiResponse<PageResponse<ProductImagesResponseDTO>>> getAllProductImages(
                    @Valid @RequestBody PageRequestDTO requestDTO) {

                PageResponse<ProductImagesResponseDTO> result =
                        productImagesService.listProductImages(requestDTO);

                return ResponseEntity.ok(
                        ApiResponse.<PageResponse<ProductImagesResponseDTO>>builder()
                                .success(true)
                                .message("Product Images fetched successfully")
                                .data(result)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
            }

            // Get By Id
            @GetMapping("/{imageId}")
            public ResponseEntity<ApiResponse<ProductImagesResponseDTO>> getProductImage(
                    @PathVariable @Positive Long imageId) {

                ProductImagesResponseDTO response =
                        productImagesService.getProductImage(imageId);

                return ResponseEntity.ok(
                        ApiResponse.<ProductImagesResponseDTO>builder()
                                .success(true)
                                .message("Product Image fetched successfully")
                                .data(response)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
            }

            // Create
            @PostMapping
            public ResponseEntity<ApiResponse<ProductImagesResponseDTO>> create(
                    @Valid @RequestBody ProductImagesCreateRequestDTO request) {

                ProductImagesResponseDTO response =
                        productImagesService.createProductImage(request);

                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(ApiResponse.success(response, "Product Image created successfully"));
            }

            // Update
            @PutMapping("/{imageId}")
            public ResponseEntity<ApiResponse<ProductImagesResponseDTO>> update(
                    @PathVariable @Positive Long imageId,
                    @Valid @RequestBody ProductImagesUpdateRequestDTO request) {

                ProductImagesResponseDTO response =
                        productImagesService.updateProductImage(imageId, request);

                return ResponseEntity.ok(
                        ApiResponse.success(response, "Product Image updated successfully"));
            }

            // Delete
            @DeleteMapping("/{imageId}")
            public ResponseEntity<ApiResponse<Void>> delete(
                    @PathVariable @Positive Long imageId) {

                productImagesService.deleteProductImage(imageId);

                return ResponseEntity.ok(
                        ApiResponse.<Void>builder()
                                .success(true)
                                .message("Product Image deleted successfully")
                                .timestamp(LocalDateTime.now())
                                .build()
                );
            }
        }