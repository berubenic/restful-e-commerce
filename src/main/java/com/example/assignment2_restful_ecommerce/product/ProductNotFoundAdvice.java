package com.example.assignment2_restful_ecommerce.product;

import com.example.assignment2_restful_ecommerce.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductNotFoundAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse productNotFoundHandler(ProductNotFoundException ex) {
        return new ErrorResponse(ex.getMessage());
    }
}