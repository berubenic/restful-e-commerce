package com.example.assignment2_restful_ecommerce.product;

public class ProductNotFoundException extends RuntimeException {
    /**
     * Constructor.
     *
     * @param id the id of the product
     */
    public ProductNotFoundException(final Long id) {
        super("Could not find product %d".formatted(id));
    }
}
