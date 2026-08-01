package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.request.ProductRequest;
import com.aeropelican.productservice.dto.request.ProductResponse;
import com.aeropelican.productservice.entity.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest request) {
        Product product = new Product();
        product.setCategoryId(request.categoryId());
        product.setProductName(request.productName());
        product.setDescription(request.description());
        product.setBrand(request.brand());
        product.setIsActive(request.isActive() != null ? request.isActive() : true);
        product.setCreateAt(LocalDateTime.now());
        return product;
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getProductId(),
                product.getCategoryId(),
                product.getProductName(),
                product.getDescription(),
                product.getBrand(),
                product.getIsActive(),
                product.getCreateAt(),
                product.getUpdatedAt()
        );
    }
}