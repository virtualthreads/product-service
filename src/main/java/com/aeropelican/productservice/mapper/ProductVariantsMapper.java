package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.request.ProductVariantsCreateRequestDTO;
import com.aeropelican.productservice.dto.response.ProductVariantsResponseDTO;
import com.aeropelican.productservice.entity.ProductVariants;

import java.time.LocalDateTime;

public class ProductVariantsMapper {

    public static ProductVariants toEntity(ProductVariantsCreateRequestDTO request) {

        ProductVariants variant = new ProductVariants();

        variant.setProductId(request.getProductId());
        variant.setSku(request.getSku());
        variant.setColor(request.getColor());
        variant.setStorageCapacity(request.getStorageCapacity());
        variant.setPrice(request.getPrice());
        variant.setIsActive(request.getIsActive());

        variant.setCreatedAt(LocalDateTime.now());
        variant.setUpdatedAt(LocalDateTime.now());

        return variant;
    }

    public static ProductVariantsResponseDTO toResponseDTO(ProductVariants variant) {

        return ProductVariantsResponseDTO.builder()
                .variantId(variant.getVariantId())
                .productId(variant.getProductId())
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