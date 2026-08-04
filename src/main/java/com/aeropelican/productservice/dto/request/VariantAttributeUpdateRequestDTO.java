package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VariantAttributeUpdateRequestDTO(
        @NotBlank(message = "Attribute name is required")
        @Size(max = 50, message = "Attribute name cannot exceed 50 characters")
        String attrName,

        @NotBlank(message = "Attribute value is required")
        @Size(max = 100, message = "Attribute value cannot exceed 100 characters")
        String attrValue
) {}