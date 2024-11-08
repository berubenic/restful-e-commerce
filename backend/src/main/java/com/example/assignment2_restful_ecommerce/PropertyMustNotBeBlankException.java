package com.example.assignment2_restful_ecommerce;

public class PropertyMustNotBeBlankException extends RuntimeException {
    /**
     * Constructor.
     *
     * @param property the property that must not be blank
     */
    public PropertyMustNotBeBlankException(final String property) {
        super("Property must not be blank: %s".formatted(property));
    }
}
