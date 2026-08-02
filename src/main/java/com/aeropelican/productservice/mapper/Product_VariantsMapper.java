package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.request.ProductVariantsCreateRequestDTO;
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
        variant.setCreated_at(new Timestamp(System.currentTimeMillis()));
        variant.setUpdated_at(new Timestamp(System.currentTimeMillis()));

        return variant;
    }

        public static Product_VariantsResponseDTO toResponseDTO(Product_Variants variant) {
            return Product_VariantsResponseDTO.builder()
                    .variant_id(variant.getVariantId())
                    .product_id(variant.getProductId())
                    .sku(variant.getSku())
                    .color(variant.getColor())
                    .storage_capacity(variant.getStorageCapacity())
                    .price(variant.getPrice())
                    .is_active(variant.getIsActive())
                    .created_at(variant.getCreated_at())
                    .updated_at(variant.getUpdated_at())
                    .build();
        }

}
