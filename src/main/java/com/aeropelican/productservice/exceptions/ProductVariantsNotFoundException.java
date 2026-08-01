package com.aeropelican.productservice.exceptions;

public class ProductVariantsNotFoundException extends RuntimeException {
    public ProductVariantsNotFoundException(String message) {
        super(message);
    }
}
