package com.example.demo.exceptions;

/**
 * Exception thrown when a product category is not found in the system.
 * Typically used in service layer when querying by category returns no results.
 */
public class CategoryNotFoundException extends RuntimeException {

    /**
     * Constructs a new CategoryNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining the exception
     */
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
