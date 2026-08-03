package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.request.ProductCreateRequestDTO;
import com.aeropelican.productservice.dto.response.ProductResponseDTO;
import com.aeropelican.productservice.entity.Product;

import java.time.LocalDateTime;

public class ProductMapper {

    public static Product toEntity(ProductCreateRequestDTO requestDTO) {

        Product product = new Product();

        product.setProductName(requestDTO.getProductName());
        product.setDescription(requestDTO.getDescription());
        product.setBrand(requestDTO.getBrand());
        product.setIsActive(requestDTO.getIsActive());

        product.setCreateAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        return product;
    }

    public static ProductResponseDTO toResponseDTO(Product product) {

        return ProductResponseDTO.builder()
                .productId(product.getProductId())
                .categoryId(product.getCategory().getCategoryId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .brand(product.getBrand())
                .isActive(product.getIsActive())
                .createdAt(product.getCreateAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}