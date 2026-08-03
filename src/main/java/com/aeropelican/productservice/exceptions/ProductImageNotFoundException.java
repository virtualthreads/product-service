package com.aeropelican.productservice.exceptions;

public class ProductImageNotFoundException extends RuntimeException {

    public ProductImageNotFoundException(String message) {
        super(message);
    }
}