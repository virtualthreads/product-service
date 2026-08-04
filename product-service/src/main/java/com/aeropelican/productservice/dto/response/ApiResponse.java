package com.aeropelican.productservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
        T data,
        boolean success,
        String message,
        ErrorResponse error,
        LocalDateTime timestamp

) {
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, true, message, null, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> failure(String message, ErrorResponse error) {
        return new ApiResponse<>(null, false, message, error, LocalDateTime.now());
    }
}
