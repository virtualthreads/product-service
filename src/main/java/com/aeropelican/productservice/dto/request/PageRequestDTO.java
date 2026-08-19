package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {

    @Min(value = 0, message = "Page must be greater than or equal to 0")
    private Integer page = 0;

    @Positive(message = "Size must be a positive number")
    private Integer size = 10;

    @NotBlank(message = "SortBy cannot be blank")
    private String sortBy = "id";

    @Pattern(regexp = "(?i)asc|desc", message = "Sort direction must be either 'asc' or 'desc'")
    private String sortDir = "asc";
}