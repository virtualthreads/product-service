package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.request.ProductVariantsCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductVariantsUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.Product_VariantsResponseDTO;
import com.aeropelican.productservice.entity.Product_Variants;

import java.sql.Timestamp;

public class Product_VariantsMapper {

    public static Product_Variants toEntity(ProductVariantsCreateRequestDTO requestDTO) {

        Product_Variants variant = new Product_Variants();

        variant.setProductId(requestDTO.getProductId());
        variant.setSku(requestDTO.getSku());
        variant.setColor(requestDTO.getColor());
        variant.setStorageCapacity(requestDTO.getStorageCapacity());
        variant.setPrice(requestDTO.getPrice());
        variant.setIsActive(requestDTO.getIsActive());

        Timestamp now = new Timestamp(System.currentTimeMillis());
        variant.setCreatedAt(now);
        variant.setUpdatedAt(now);

        return variant;
    }

    public static Product_VariantsResponseDTO toResponseDTO(Product_Variants variant) {

        return Product_VariantsResponseDTO.builder()
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

    public static void updateEntity(Product_Variants variant,
                                    ProductVariantsUpdateRequestDTO requestDTO) {

        variant.setProductId(requestDTO.getProductId());
        variant.setSku(requestDTO.getSku());
        variant.setColor(requestDTO.getColor());
        variant.setStorageCapacity(requestDTO.getStorageCapacity());
        variant.setPrice(requestDTO.getPrice());

        if (requestDTO.getIsActive() != null) {
            variant.setIsActive(requestDTO.getIsActive());
        }

        variant.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
    }
}