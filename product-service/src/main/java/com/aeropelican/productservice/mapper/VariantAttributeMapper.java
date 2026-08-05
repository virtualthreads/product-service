package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.response.VariantAttributeResponse;
import com.aeropelican.productservice.entity.VariantAttribute;
import org.springframework.stereotype.Component;

@Component
public class VariantAttributeMapper {

    public VariantAttributeResponse toResponse(VariantAttribute variantAttribute) {
        return VariantAttributeResponse.builder()
                .attributeId(variantAttribute.getId())
                .variantId(variantAttribute.getVariantId())
                .attributeName(variantAttribute.getAttrName())
                .attributeValue(variantAttribute.getAttrValue())
                .build();
    }
}
