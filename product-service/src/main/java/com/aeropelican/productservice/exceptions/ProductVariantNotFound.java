package com.aeropelican.productservice.exceptions;

public class ProductVariantNotFound extends RuntimeException {
    public ProductVariantNotFound (String message){
        super(message);
    }

}
