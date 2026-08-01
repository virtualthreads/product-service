package com.aeropelican.productservice.dto.request;

import java.math.BigDecimal;

public record ProductVariantRequest(
        Long productId,
        String sku,
        String color,
        String storageCapacity,
        BigDecimal price,
        Boolean isActive
) {
}