package com.aeropelican.productservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
public record VariantAttributeResponse (
        long attributeId,
        long variantId,
        String attributeName,
        String attributeValue
){
}
