package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.response.ProductImageResponseDTO;
import com.aeropelican.productservice.entity.ProductImage;

public class ProductImageMapper {

    private ProductImageMapper() {
    }

    public static ProductImageResponseDTO toResponse(ProductImage image) {

        if (image == null) {
            return null;
        }

        return ProductImageResponseDTO.builder()
                .imageId(image.getImageId())
                .variantId(image.getProductVariant().getVariantId())
                .imageUrl(image.getImageUrl())
                .isPrimary(image.getIsPrimary())
                .displayOrder(image.getDisplayOrder())
                .createdAt(image.getCreatedAt())
                .build();
    }
}