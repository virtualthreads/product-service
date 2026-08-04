package com.aeropelican.productservice.dto.response;

public record VariantAttributeResponseDTO(
        Long variantAttributeId,
        Long variantId,
        String attrName,
        String attrValue
) {}