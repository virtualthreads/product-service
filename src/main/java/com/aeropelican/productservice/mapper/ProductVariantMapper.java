package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.request.ProductVariantRequest;
import com.aeropelican.productservice.dto.response.ProductVariantResponse;
import com.aeropelican.productservice.dto.response.ProductVariantResponseDTO;
import com.aeropelican.productservice.entity.ProductVariant;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class ProductVariantMapper {

    public ProductVariant toEntity(ProductVariantRequest request) {
        LocalDateTime now = LocalDateTime.now();
        return ProductVariant.builder()
                .productId(request.productId())
                .sku(request.sku())
                .price(request.price())
                .isActive(request.isActive() != null ? request.isActive() : true)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    public ProductVariantResponse toResponse(ProductVariant variant) {
        return ProductVariantResponse.builder()
                .variantId(variant.getVariantId())
                .sku(variant.getSku())
                .price(variant.getPrice())
                .isActive(variant.getIsActive())
                .createdAt(variant.getCreatedAt())
                .updatedAt(variant.getUpdatedAt())
                .build();
    }
}