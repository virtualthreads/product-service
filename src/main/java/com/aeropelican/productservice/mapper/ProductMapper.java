package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.request.ProductRequest;
import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final ProductVariantMapper productVariantMapper;

    public Product toEntity(ProductRequest request) {
        Product product = new Product();
        //product.setCategoryId(request.categoryId());
        product.setProductName(request.productName());
        product.setDescription(request.description());
        product.setBrand(request.brand());
        product.setIsActive(request.isActive() != null ? request.isActive() : true);
        product.setCreateAt(LocalDateTime.now());
        return product;
    }

    public ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .brand(product.getBrand())
                .isActive(product.getIsActive())
                .createdAt(product.getCreateAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public ProductResponse toResponseWithVariants(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .brand(product.getBrand())
                .isActive(product.getIsActive())
                .createdAt(product.getCreateAt())
                .updatedAt(product.getUpdatedAt())
                .variants(product.getVariants()
                        .stream()
                        .map(productVariantMapper::toResponse)
                        .toList()
                )
                .build();
    }
}