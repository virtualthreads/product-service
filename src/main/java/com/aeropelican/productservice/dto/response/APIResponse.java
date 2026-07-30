package com.aeropelican.productservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private ErrorResponse error;
    private LocalDateTime timestamp;

    public static <T> APIResponse<T> success(T data, String message) {
        return APIResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> APIResponse<T> success(T data) {
        return success(data, "Request processed successfully");
    }

    public static APIResponse<Void> failure(String message, ErrorResponse error) {
        return APIResponse.<Void>builder()
                .success(false)
                .message(message)
                .error(error)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
