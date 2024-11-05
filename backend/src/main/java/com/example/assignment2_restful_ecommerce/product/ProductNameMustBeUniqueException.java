package com.example.assignment2_restful_ecommerce.product;

public class ProductNameMustBeUniqueException extends RuntimeException {
    /**
     * Constructor.
     *
     * @param name the name of the product
     */
    public ProductNameMustBeUniqueException(final String name) {
        super("Product name must be unique: %s".formatted(name));
    }
}
