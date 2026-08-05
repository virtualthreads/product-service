package com.aeropelican.productservice.exceptions;

public class CategoryNotFound extends RuntimeException {
    public CategoryNotFound (String message){
        super(message);
    }

}
