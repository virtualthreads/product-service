package com.aeropelican.productservice.mapper;
import com.aeropelican.productservice.dto.request.ProductImagesCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductImagesUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.ProductImagesResponseDTO;
import com.aeropelican.productservice.entity.Product_Images;
import java.sql.Timestamp;

    public class Product_ImagesMapper {
        public static ProductImagesResponseDTO toResponseDTO(Product_Images entity) {
            return ProductImagesResponseDTO.builder()
                    .imageId(entity.getImageId())
                    .variantId(entity.getVariantId())
                    .imageUrl(entity.getImageUrl())
                    .isPrimary(entity.isPrimary())
                    .displayOrder(entity.getDisplayOrder())
                    .createdAt(entity.getCreatedAt())
                    .build();
        }
        public static Product_Images toEntity(ProductImagesCreateRequestDTO request) {
            Product_Images entity = new Product_Images();
            entity.setVariantId(request.getVariantId());
            entity.setImageUrl(request.getImageUrl());
            entity.setPrimary(request.getIsPrimary());
            entity.setDisplayOrder(request.getDisplayOrder());
            entity.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            return entity;
        }

}
