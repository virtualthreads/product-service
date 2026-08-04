package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.VariantAttributeResponse;
import com.aeropelican.productservice.service.VariantAttributeService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/variants/{variantId}")
@RequiredArgsConstructor
public class VariantAttributeController {

    private final VariantAttributeService variantAttributeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<VariantAttributeResponse>>> getVariantAttributes(
            @Positive(message = "Variant ID must be a positive number")
            @PathVariable Long variantId) {
        return ResponseEntity.ok(ApiResponse.success(
                variantAttributeService.fetchVariantAttributes(variantId),
                "Variant attributes fetched"
        ));
    }
}
