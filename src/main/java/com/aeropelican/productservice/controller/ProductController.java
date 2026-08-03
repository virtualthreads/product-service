package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.PageRequestDTO;
import com.aeropelican.productservice.dto.request.ProductCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductResponseDTO;
import com.aeropelican.productservice.service.ProductService;
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

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{page}/{size}/{sortBy}/{sortDir}")
    public ResponseEntity<ApiResponse<PageResponse<ProductResponseDTO>>> getAllProducts(

            @PositiveOrZero(message = "Page number cannot be negative")
            @PathVariable Integer page,

            @Positive(message = "Page size must be greater than 0")
            @Max(value = 100, message = "Maximum page size is 100")
            @PathVariable Integer size,

            @NotBlank(message = "Sort field cannot be blank")
            @PathVariable String sortBy,

            @Pattern(
                    regexp = "ASC|DESC",
                    flags = Pattern.Flag.CASE_INSENSITIVE,
                    message = "Sort direction must be ASC or DESC")
            @PathVariable String sortDir) {

        PageRequestDTO requestDTO = new PageRequestDTO();
        requestDTO.setPage(page);
        requestDTO.setSize(size);
        requestDTO.setSortBy(sortBy);
        requestDTO.setSortDir(sortDir);

        PageResponse<ProductResponseDTO> result =
                productService.listProducts(requestDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ApiResponse.<PageResponse<ProductResponseDTO>>builder()
                                .success(true)
                                .message("Products fetched successfully")
                                .data(result)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> getProduct(

            @Positive(message = "Product ID must be a positive number")
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        productService.getProduct(id),
                        "Product fetched successfully")
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDTO>> createProduct(

            @Valid
            @RequestBody ProductCreateRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                productService.createProduct(request),
                                "Product created successfully")
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> updateProduct(

            @Positive(message = "Product ID must be a positive number")
            @PathVariable Integer id,

            @Valid
            @RequestBody ProductUpdateRequestDTO request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        productService.updateProduct(id, request),
                        "Product updated successfully")
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(

            @Positive(message = "Product ID must be a positive number")
            @PathVariable Integer id) {

        productService.deleteProduct(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        null,
                        "Product deleted successfully")
        );
    }
}
//GET http://localhost:8080/api/v1/products/0/10/productName/ASC**
// GET http://localhost:8080/api/v1/products
// post http://localhost:8080/api/v1/products