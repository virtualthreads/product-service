package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.request.VariantAttributeCreateRequestDTO;
import com.aeropelican.productservice.dto.request.VariantAttributeUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.VariantAttributeResponseDTO;
import com.aeropelican.productservice.entity.VariantAttribute;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VariantAttributeMapper {

    /**
     * Create DTO -> Entity
     */
    public VariantAttribute toEntity(VariantAttributeCreateRequestDTO request) {

        if (request == null) {
            return null;
        }

        return VariantAttribute.builder()
                .variantId(request.getVariantId())
                .attrName(request.getAttrName())
                .attrValue(request.getAttrValue())
                .build();
    }

    /**
     * Entity -> Response DTO
     */
    public VariantAttributeResponseDTO toResponseDTO(VariantAttribute entity) {

        if (entity == null) {
            return null;
        }

        return VariantAttributeResponseDTO.builder()
                .id(entity.getId())
                .variantId(entity.getVariantId())
                .attrName(entity.getAttrName())
                .attrValue(entity.getAttrValue())
                .build();
    }

    /**
     * Update Entity from Update DTO
     */
    public void updateEntity(VariantAttribute entity,
                             VariantAttributeUpdateRequestDTO request) {

        entity.setVariantId(request.getVariantId());
        entity.setAttrName(request.getAttrName());
        entity.setAttrValue(request.getAttrValue());
    }

    /**
     * Entity List -> Response DTO List
     */
    public List<VariantAttributeResponseDTO> toResponseDTOList(
            List<VariantAttribute> entityList) {

        return entityList.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}