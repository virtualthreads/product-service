package com.aeropelican.productservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponseDTO {

    private Integer productId;

    private Long categoryId;

    private String productName;
    private String description;
    private String brand;

    @Builder.Default
    private Boolean isActive = true;

    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}