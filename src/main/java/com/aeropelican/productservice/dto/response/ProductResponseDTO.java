package com.aeropelican.productservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponseDTO {
    private Long productId;
    private String productName;
    private String description;
    private String brand;
    private Boolean isActive = true;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}
