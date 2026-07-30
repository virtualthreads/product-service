package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.entity.ProductVariant;
import com.aeropelican.productservice.dto.response.ProductVariantResponseDTO;

public class ProductVariantMapper {

    public static ProductVariantResponseDTO toResponseDTO(ProductVariant variant) {
        if (variant == null) {
            return null;
        }
        return ProductVariantResponseDTO.builder()
                .variantId(variant.getVariantId())
                .sku(variant.getSku())
                .color(variant.getColor())
                .storageCapacity(variant.getStorageCapacity())
                .price(variant.getPrice())
                .isActive(variant.getIsActive())
                .createdAt(variant.getCreatedAt())
                .updatedAt(variant.getUpdatedAt())
                .build();
    }
}
