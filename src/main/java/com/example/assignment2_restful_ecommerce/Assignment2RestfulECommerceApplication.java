package com.example.assignment2_restful_ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Assignment2RestfulECommerceApplication {

    /**
     * Private constructor to prevent instantiation.
     */
    private Assignment2RestfulECommerceApplication() {
        throw new UnsupportedOperationException(
                "This is a utility class and cannot be instantiated"
        );
    }

    /**
     * Main method to run the application.
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        SpringApplication.run(
                Assignment2RestfulECommerceApplication.class,
                args
        );
    }

}
