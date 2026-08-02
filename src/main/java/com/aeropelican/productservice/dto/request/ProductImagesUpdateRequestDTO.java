package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImagesUpdateRequestDTO {

        @NotNull(message = "Variant ID is required")
        @Positive(message = "Variant ID must be greater than 0")
        private Long variantId;

        @NotBlank(message = "Image URL is required")
        @Size(max = 500, message = "Image URL cannot exceed 500 characters")
        @Pattern(regexp = "^(https?://).+\\.(jpg|jpeg|png|gif|webp)$",
                flags = Pattern.Flag.CASE_INSENSITIVE,
                message = "Image URL must be a valid HTTP/HTTPS URL ending with .jpg, .jpeg, .png, .gif, or .webp")
        private String imageUrl;

        @NotNull(message = "Primary image status is required")
        private Boolean isPrimary;

        @NotNull(message = "Display order is required")
        @PositiveOrZero(message = "Display order cannot be negative")
        @Max(value = 1000, message = "Display order cannot exceed 1000")
        private Integer displayOrder;
}
