package com.aeropelican.productservice.dto.response;


@Builder
public record VariantAttributeResponseDTO (
        long attributeId,
        long variantId,
        String attrName,
        String attrValue
){
}
