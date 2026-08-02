package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductSearchRequest(
        @Size(max = 255, message = "Keyword must not exceed 255 characters")
        String keyword,

        @Positive(message = "Category ID must be a positive number if provided")
        Long categoryId,

        @Size(max = 100, message = "Brand must not exceed 100 characters")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-&.,']*$", message = "Brand can only contain letters, numbers, spaces, hyphens, ampersands, dots, commas, and single quotes")
        String brand,

        Boolean active,

        @Min(value = 0, message = "Page must be 0 or greater")
        Integer page,

        @Positive(message = "Size must be a positive number")
        Integer size,

        @Size(max = 50, message = "Sort field must not exceed 50 characters")
        @Pattern(regexp = "^[a-zA-Z_]*$", message = "Sort field can only contain letters and underscores")
        String sortBy,

        @Size(max = 4, message = "Sort direction must not exceed 4 characters")
        @Pattern(regexp = "^(ASC|DESC|asc|desc)?$", message = "Sort direction must be ASC or DESC")
        String sortDir
) {
}
