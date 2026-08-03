package com.aeropelican.productservice.dto.request;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PageRequestDTO {

    @NotNull
    @PositiveOrZero(message = "Page number cannot be negative")
    private Integer page;

    @NotNull
    @Positive(message = "Size must be greater than 0")
    @Max(value = 100, message = "Maximum page size is 100")
    private Integer size;

    @NotBlank(message = "Sort field is required")
    private String sortBy;

    @Pattern(
            regexp = "ASC|DESC",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Sort direction must be ASC or DESC")
    private String sortDir;
}