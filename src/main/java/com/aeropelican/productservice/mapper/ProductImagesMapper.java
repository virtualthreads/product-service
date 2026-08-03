package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.request.ProductImagesCreateRequestDTO;
import com.aeropelican.productservice.dto.response.ProductImagesResponseDTO;
import com.aeropelican.productservice.entity.Product_Images;

import java.time.LocalDateTime;

public class ProductImagesMapper {

    public static Product_Images toEntity(ProductImagesCreateRequestDTO dto) {

        Product_Images image = new Product_Images();

        image.setVariantId(dto.getVariantId());
        image.setImageUrl(dto.getImageUrl());
        image.setIsPrimary(dto.getIsPrimary());
        image.setDisplayOrder(dto.getDisplayOrder());
        image.setCreatedAt(LocalDateTime.now());

        return image;
    }

    public static ProductImagesResponseDTO toResponseDTO(Product_Images image) {

        return ProductImagesResponseDTO.builder()
                .imageId(image.getImageId())
                .variantId(image.getVariantId())
                .imageUrl(image.getImageUrl())
                .isPrimary(image.getIsPrimary())
                .displayOrder(image.getDisplayOrder())
                .createdAt(image.getCreatedAt())
                .build();
    }
}