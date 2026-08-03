package com.aeropelican.productservice.exceptions;

import com.aeropelican.productservice.dto.response.ErrorResponse;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.FieldError;
import jakarta.servlet.http.HttpServletRequest;
//import org.apache.coyote.BadRequestException;
import org.springframework.data.core.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.aeropelican.productservice.exceptions.BadRequestException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode("RESOURCE_NOT_FOUND")
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.<Void>builder()
                        .success(false)
                        .error(errorResponse)
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build()
                );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadRequest(BadRequestException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode("BAD_REQUEST")
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .build();
        return build(HttpStatus.BAD_REQUEST, errorResponse, ex.getMessage());
    }

    // Generic Exceptions
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<Void>> handleMissingParams(MissingServletRequestParameterException ex, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.builder()
                .errorCode("MISSING_PARAMETER")
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .build();
        return build(HttpStatus.BAD_REQUEST, error, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Void>> handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {

        String message = String.format("Parameter '%s' should be of type %s", ex.getName(), ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "a different type");

        ErrorResponse error = ErrorResponse.builder()
                .errorCode("TYPE_MISMATCH")
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .build();
        return build(HttpStatus.BAD_REQUEST, error, message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage()));

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode("VALIDATION_FAILED")
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest()
                .body(
                        ApiResponse.<Map<String, String>>builder()
                                .success(false)
                                .message("Validation failed")
                                .data(errors)
                                .error(errorResponse)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNoHandlerFound(NoHandlerFoundException ex, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.builder()
                .errorCode("ROUTE_NOT_FOUND")
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequestURI())
                .build();
        return build(HttpStatus.NOT_FOUND, error, "No handler found for this route");
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ApiResponse<Void>> handleHandlerMethodValidationException(HandlerMethodValidationException ex, HttpServletRequest request) {

        List<FieldError> errors = ex.getParameterValidationResults()
                .stream()
                .flatMap(result -> {
                    String parameterName = result.getMethodParameter().getParameterName();
                    Object rejectedValue = result.getArgument();
                    return result.getResolvableErrors().stream()
                            .map(error -> FieldError.builder()
                                    .field(parameterName)
                                    .rejectedValue(rejectedValue)
                                    .message(error.getDefaultMessage())
                                    .build()
                            );
                }).toList();
        ErrorResponse error = ErrorResponse.builder()
                .errorCode("VALIDATION_FAILED")
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .fieldErrors(errors)
                .build();
        return build(HttpStatus.BAD_REQUEST, error, "Validation failed");
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ApiResponse<Void>> handlePropertyReferenceException(PropertyReferenceException ex, HttpServletRequest request) {

        String message = String.format("Invalid property '%s'. Please provide a valid property name.", ex.getPropertyName());

        ErrorResponse error = ErrorResponse.builder()
                .errorCode("INVALID_PROPERTY")
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .build();

        return build(HttpStatus.BAD_REQUEST, error, message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode("INTERNAL_SERVER_ERROR")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(request.getRequestURI())
                .build();
        return build(
                HttpStatus.INTERNAL_SERVER_ERROR,
                errorResponse,
                "An unexpected error occurred. Please try again later."
        );
    }

    //Helper methods
    private ResponseEntity<ApiResponse<Void>> build(HttpStatus status, ErrorResponse error, String message) {
        return ResponseEntity.status(status).body(ApiResponse.failure(message, error));
    }
}
