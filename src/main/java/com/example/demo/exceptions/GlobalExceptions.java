package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the application.
 * Catches and processes specific exceptions thrown from controllers and services,
 * returning meaningful HTTP responses.
 */
@RestControllerAdvice
public class GlobalExceptions {

    /**
     * Handles ProductNotFoundException and returns a 404 Not Found response.
     *
     * @param ex the exception thrown when a product is not found
     * @return a ResponseEntity with error message and HTTP status 404
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductException(ProductNotFoundException ex) {
        return new ResponseEntity<>("Product not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles CategoryNotFoundException and returns a 404 Not Found response.
     *
     * @param ex the exception thrown when a category is not found
     * @return a ResponseEntity with error message and HTTP status 404
     */
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleCategoryException(CategoryNotFoundException ex) {
        return new ResponseEntity<>("Category not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
