package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.request.ProductCreateRequestDTO;
import com.aeropelican.productservice.dto.response.ProductResponseDTO;
import com.aeropelican.productservice.entity.Product;

import java.time.LocalDateTime;

public class ProductMapper {

    public static Product toEntity(ProductCreateRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }

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
        if (product == null) {
            return null;
        }

        // Print statements for tracking execution in console
        System.out.println("Before fetching product entity");
        System.out.println("Attempting to fetch product entity");

        return ProductResponseDTO.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .brand(product.getBrand())
                .isActive(product.getIsActive())
                .createAt(product.getCreateAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}