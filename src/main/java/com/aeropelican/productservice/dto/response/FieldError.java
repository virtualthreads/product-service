package com.aeropelican.productservice.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldError {
    private String field;
    private Object rejectedValue;
    private String message;
}
