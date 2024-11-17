package com.example.assignment2_restful_ecommerce;

import com.example.assignment2_restful_ecommerce.category.CategoryNotFoundException;
import com.example.assignment2_restful_ecommerce.product.ProductNotFoundException;
import com.example.assignment2_restful_ecommerce.product.ProductPriceMustBePositiveException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ProductNotFoundException.class, CategoryNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String, Object> handleNotFoundException(RuntimeException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("error", e.getMessage());
        return response;
    }

    @ExceptionHandler({
            PropertyMustBeUniqueException.class,
            ProductPriceMustBePositiveException.class,
            PropertyMustNotBeBlankException.class,
            IllegalArgumentException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Map<String, Object> handleBadRequestException(RuntimeException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("error", e.getMessage());
        return response;
    }
}