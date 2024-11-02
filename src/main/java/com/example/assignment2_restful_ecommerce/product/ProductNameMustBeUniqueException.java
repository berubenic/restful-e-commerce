package com.example.assignment2_restful_ecommerce.product;

public class ProductNameMustBeUniqueException extends RuntimeException {
    public ProductNameMustBeUniqueException(String name) {
        super("Product name must be unique: %s".formatted(name));
    }
}
