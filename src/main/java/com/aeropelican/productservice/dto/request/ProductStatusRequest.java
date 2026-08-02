package com.aeropelican.productservice.dto.request;

import jakarta.validation.constraints.NotNull;

public record ProductStatusRequest(
        @NotNull(message = "isActive status cannot be null")
        Boolean isActive
) {

}
