package com.example.assignment2_restful_ecommerce.category;

public class CategoryNotFoundException extends RuntimeException {
    /**
     * Constructor.
     *
     * @param id the id of the category
     */
    public CategoryNotFoundException(final Long id) {
        super("Could not find category %d".formatted(id));
    }
}
