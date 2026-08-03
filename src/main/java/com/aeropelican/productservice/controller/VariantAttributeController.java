package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.PageRequestDTO;
import com.aeropelican.productservice.dto.request.VariantAttributeCreateRequestDTO;
import com.aeropelican.productservice.dto.request.VariantAttributeUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.VariantAttributeResponseDTO;
import com.aeropelican.productservice.entity.VariantAttribute;
import com.aeropelican.productservice.service.VariantAttributeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/variant-attributes")
public class VariantAttributeController {

    private final VariantAttributeService variantAttributeService;

    /**
     * List All Variant Attributes
     */
    @PostMapping("/list")
    public ApiResponse<PageResponse<VariantAttributeResponseDTO>> listVariantAttributes(
            @RequestBody PageRequestDTO requestDTO) {

        return ApiResponse.success(
                variantAttributeService.listVariantAttributes(requestDTO)
        );
    }

    /**
     * Get Variant Attribute By Id
     */
    @GetMapping("/{id}")
    public ApiResponse<VariantAttributeResponseDTO> getVariantAttribute(
            @PathVariable Long id) {

        return ApiResponse.success(
                variantAttributeService.getVariantAttribute(id)
        );
    }

    /**
     * Create Variant Attribute
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<VariantAttributeResponseDTO> createVariantAttribute(
            @Valid @RequestBody VariantAttributeCreateRequestDTO request) {

        return ApiResponse.success(
                variantAttributeService.createVariantAttribute(request)
        );
    }

    /**
     * Update Variant Attribute
     */
    @PutMapping("/{id}")
    public ApiResponse<VariantAttributeResponseDTO> updateVariantAttribute(
            @PathVariable Long id,
            @Valid @RequestBody VariantAttributeUpdateRequestDTO request) {

        return ApiResponse.success(
                variantAttributeService.updateVariantAttribute(id, request)
        );
    }

    /**
     * Delete Variant Attribute
     */
    @DeleteMapping("/{id}")
    public ApiResponse<VariantAttribute> deleteVariantAttribute(
            @PathVariable Long id) {

        return ApiResponse.success(
                variantAttributeService.deleteVariantAttribute(id)
        );
    }
}