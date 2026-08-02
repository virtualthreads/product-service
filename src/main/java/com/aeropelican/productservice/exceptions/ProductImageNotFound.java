package com.aeropelican.productservice.exceptions;

public class ProductImageNotFound extends RuntimeException {
    public ProductImageNotFound(String message) {
        super(message);
    }
}
