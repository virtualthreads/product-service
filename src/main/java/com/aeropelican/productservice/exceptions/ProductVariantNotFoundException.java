package com.aeropelican.productservice.exceptions;

public class ProductVariantNotFoundException extends RuntimeException {

    public ProductVariantNotFoundException(String message) {
        super(message);
    }
}