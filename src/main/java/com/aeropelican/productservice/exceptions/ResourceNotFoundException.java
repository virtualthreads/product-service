package com.aeropelican.productservice.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resource, String id) {
        super("'%s' not found with id: '%s'".formatted(resource, id));
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super("%s not found with %s = '%s'".formatted(resourceName, fieldName, fieldValue));
    }
}
