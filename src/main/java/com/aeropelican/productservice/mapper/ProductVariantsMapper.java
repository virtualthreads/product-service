package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.response.ProductVariantsResponseDTO;
import com.aeropelican.productservice.entity.ProductVariants;

import java.math.BigDecimal;

public class ProductVariantsMapper {

    public static ProductVariantsResponseDTO toProductVariantsResponse(ProductVariants variant) {
        if (variant == null) {
            return null;
        }

        // Print statements for tracking execution in console
        System.out.println("Before fetching product variant entity");
        System.out.println("Attempting to fetch product variant entity");

        Long variantId = variant.getVariantId() != null ? variant.getVariantId().longValue() : null;
        Long productId = (variant.getProduct() != null && variant.getProduct().getProductId() != null)
                ? variant.getProduct().getProductId().longValue()
                : null;

        BigDecimal price = variant.getPrice() != null ? BigDecimal.valueOf(variant.getPrice()) : null;

        return ProductVariantsResponseDTO.builder()
                .variantId(variantId)
                .productId(productId)
                .variantName(variant.getVariantName())
                .color(variant.getColor())
                .size(variant.getSize())
                .price(price)
                .build();
    }
}