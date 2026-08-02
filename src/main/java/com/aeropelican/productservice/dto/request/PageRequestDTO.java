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
/*@NotNull @NotBlank @NotEmpty @Size @Min @Max @Positive
@PositiveOrZero @Email @Pattern
@Past @PastOrPresent @Future @FutureOrPresent*/

    public class PageRequestDTO {
        @NotNull(message = "Page number is required")
        @Min(value =0,message = "Page must be greater than 0")
        private Integer page;

        @Positive(message ="Size must be a positive number")
        @NotNull(message = "Page size is required")
        @Min(value = 1, message = "Page size must be at least 1")
        @Max(value = 100, message = "Page size cannot exceed 100")
        private Integer size;

         @NotBlank(message = "SortBy cannot be blank")
         @Size(min = 2, max = 50, message = "SortBy must be between 2 and 50 characters")
         @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]*$",
            message = "SortBy must start with a letter and contain only letters, numbers, and underscores")
        private String sortBy;

        @NotBlank(message = "Sort direction is required")
        @Pattern(regexp = "asc|desc",
                flags = Pattern.Flag.CASE_INSENSITIVE,
                message = "Sort direction must be either 'asc' or 'desc'")
        private  String sortDir;

}
