package com.aeropelican.productservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
        T data,
        boolean success,
        String message,
        ErrorResponse error
) {
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, true, message, null);
    }

    public static <T> ApiResponse<T> failure(String message, ErrorResponse error) {
        return new ApiResponse<>(null, false, message, error);
    }
}
