package com.example.assignment2_restful_ecommerce.product;

public class ProductPriceMustBePositiveException extends RuntimeException {
    /**
     * Constructor.
     */
    public ProductPriceMustBePositiveException() {
        super("Product price must be positive");
    }
}
