package com.example.assignment2_restful_ecommerce.product;

import com.example.assignment2_restful_ecommerce.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductNameMustBeUniqueAdvice {

    @ExceptionHandler(ProductNameMustBeUniqueException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorResponse productNameMustBeUniqueHandler(ProductNameMustBeUniqueException ex) {
        return new ErrorResponse(ex.getMessage());    }
}
