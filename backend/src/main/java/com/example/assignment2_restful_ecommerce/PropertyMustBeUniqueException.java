package com.example.assignment2_restful_ecommerce;

public class PropertyMustBeUniqueException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param property property
     */
    public PropertyMustBeUniqueException(final String property) {
        super(property + " must be unique");
    }
}
