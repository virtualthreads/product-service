package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.ProductSearchRequest;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/search")
@RequiredArgsConstructor
@Slf4j
public class ProductSearchController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> searchProducts(@RequestBody ProductSearchRequest request) {
        log.info("Received a request to search for the products using keyword: {}", request.keyword());
        long startTime = System.nanoTime();
        List<ProductResponse> results = productService.searchProducts(request);
        long endTime = System.nanoTime();
        long totalExecutionTime = endTime - startTime;
        log.info("Total execution time: {}", totalExecutionTime/1000000.0);
        return ResponseEntity.ok(ApiResponse.success(results, "Products fetched successfully"));
    }
}

