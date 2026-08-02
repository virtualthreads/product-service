package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductImagesCreateRequestDTO {

        @NotNull(message = "Variant ID is required")
        @Positive(message = "Variant ID must be greater than 0")
        private Long variantId;

        @NotBlank(message = "Image URL is required")
        @Pattern(regexp = "^(https?|ftp)://.+$",
                message = "Please provide a valid image URL")
        private String imageUrl;

        @NotNull(message = "Primary image status is required")
        private Boolean isPrimary;

        @NotNull(message = "Display order is required")
        @PositiveOrZero(message = "Display order must be zero or greater")
        private Integer displayOrder;

}
