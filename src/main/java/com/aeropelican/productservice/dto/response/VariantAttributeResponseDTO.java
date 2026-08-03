package com.aeropelican.productservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VariantAttributeResponseDTO {

    private Long id;
    private Long variantId;
    private String attrName;
    private String attrValue;
}