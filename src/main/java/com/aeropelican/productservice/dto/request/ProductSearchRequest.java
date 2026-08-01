package com.aeropelican.productservice.dto.request;

public record ProductSearchRequest(
        String keyword,
        Long categoryId,
        String brand,
        Boolean active,
        Integer page,
        Integer size,
        String sortBy,
        String sortDir
) {
}
