package com.example.assignment2_restful_ecommerce.product;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Could not find product %d".formatted(id));
    }
}
