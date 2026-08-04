package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.request.VariantAttributeCreateRequestDTO;
import com.aeropelican.productservice.dto.response.VariantAttributeResponseDTO;
import com.aeropelican.productservice.entity.ProductVariants;
import com.aeropelican.productservice.entity.VariantAttribute;
import org.springframework.stereotype.Component;

@Component
public class VariantAttributeMapper {

    public VariantAttribute toEntity(VariantAttributeCreateRequestDTO dto, ProductVariants variant) {
        if (dto == null) {
            return null;
        }

        return VariantAttribute.builder()
                .productVariant(variant)
                .attrName(dto.attrName())
                .attrValue(dto.attrValue())
                .build();
    }

    public VariantAttributeResponseDTO toDTO(VariantAttribute entity) {
        if (entity == null) {
            return null;
        }

        // Print statements for tracking execution in console
        System.out.println("Before fetching variant attribute entity");
        System.out.println("Attempting to fetch variant attribute entity");

        Long vId = null;
        if (entity.getProductVariant() != null && entity.getProductVariant().getVariantId() != null) {
            vId = ((Number) entity.getProductVariant().getVariantId()).longValue();
        }

        return new VariantAttributeResponseDTO(
                entity.getVariantAttributeId(),
                vId,
                entity.getAttrName(),
                entity.getAttrValue()
        );
    }
}