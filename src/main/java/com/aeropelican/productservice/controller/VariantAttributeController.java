package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.VariantAttributeCreateRequestDTO;
import com.aeropelican.productservice.dto.request.VariantAttributeUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.VariantAttributeResponseDTO;
import com.aeropelican.productservice.service.VariantAttributeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/variant-attributes")
public class VariantAttributeController {

    private final VariantAttributeService variantAttributeService;

    public VariantAttributeController(VariantAttributeService variantAttributeService) {
        this.variantAttributeService = variantAttributeService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VariantAttributeResponseDTO>> createAttribute(
            @Valid @RequestBody VariantAttributeCreateRequestDTO requestDTO) {

        VariantAttributeResponseDTO response = variantAttributeService.createAttribute(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response, "Variant attribute created successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VariantAttributeResponseDTO>> getAttributeById(@PathVariable("id") Long id) {
        VariantAttributeResponseDTO response = variantAttributeService.getAttributeById(id);
        return ResponseEntity.ok(ApiResponse.success(response, "Variant attribute fetched successfully"));
    }

    @GetMapping("/variant/{variantId}")
    public ResponseEntity<ApiResponse<List<VariantAttributeResponseDTO>>> getAttributesByVariantId(
            @PathVariable("variantId") Long variantId) {

        List<VariantAttributeResponseDTO> response = variantAttributeService.getAttributesByVariantId(variantId);
        return ResponseEntity.ok(ApiResponse.success(response, "Variant attributes fetched successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<VariantAttributeResponseDTO>> updateAttribute(
            @PathVariable("id") Long id,
            @Valid @RequestBody VariantAttributeUpdateRequestDTO requestDTO) {

        VariantAttributeResponseDTO response = variantAttributeService.updateAttribute(id, requestDTO);
        return ResponseEntity.ok(ApiResponse.success(response, "Variant attribute updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAttribute(@PathVariable("id") Long id) {
        variantAttributeService.deleteAttribute(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Variant attribute deleted successfully"));
    }
}