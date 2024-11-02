package com.example.assignment2_restful_ecommerce;

public class ErrorResponse {
    private String message;

    public ErrorResponse() { }

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
