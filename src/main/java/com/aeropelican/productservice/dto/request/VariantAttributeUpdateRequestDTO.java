package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VariantAttributeUpdateRequestDTO {

    @NotNull(message = "Variant Id is required")
    private Long variantId;

    @NotBlank(message = "Attribute name is required")
    @Size(max = 50)
    private String attrName;

    @NotBlank(message = "Attribute value is required")
    @Size(max = 100)
    private String attrValue;

}