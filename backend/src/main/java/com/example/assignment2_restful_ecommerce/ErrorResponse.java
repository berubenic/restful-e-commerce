package com.example.assignment2_restful_ecommerce;

public class ErrorResponse {
    private final boolean success;
    private final String error;

    public ErrorResponse(boolean success, String error) {
        this.success = success;
        this.error = error;
    }
}