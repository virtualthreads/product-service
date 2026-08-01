package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.request.ProductImageCreateRequestDTO;
import com.aeropelican.productservice.dto.response.ProductImageResponseDTO;
import com.aeropelican.productservice.entity.ProductImage;
import org.springframework.stereotype.Component;

@Component
public class ProductImageMapper {

    public ProductImage toEntity(ProductImageCreateRequestDTO dto) {
        if (dto == null) return null;
        return ProductImage.builder()
                .variantId(dto.getVariantId())
                .imageUrl(dto.getImageUrl())
                .isPrimary(dto.getIsPrimary() != null ? dto.getIsPrimary() : false)
                .displayOrder(dto.getDisplayOrder() != null ? dto.getDisplayOrder() : 0)
                .build();
    }

    public ProductImageResponseDTO toResponseDTO(ProductImage entity) {
        if (entity == null) return null;
        return ProductImageResponseDTO.builder()
                .imageId(entity.getImageId())
                .variantId(entity.getVariantId())
                .imageUrl(entity.getImageUrl())
                .isPrimary(entity.getIsPrimary())
                .displayOrder(entity.getDisplayOrder())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}