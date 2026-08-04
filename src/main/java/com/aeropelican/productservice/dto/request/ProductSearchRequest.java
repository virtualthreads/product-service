package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductSearchRequest(
        String keyword,
        String brand,
        String color,
        BigDecimal minPrice,
        BigDecimal maxPrice
) {

}
