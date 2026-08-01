package com.aeropelican.productservice.dto.request;

public record ProductRequest(
        Long categoryId,
        String productName,
        String description,
        String brand,
        Boolean isActive
) {
}