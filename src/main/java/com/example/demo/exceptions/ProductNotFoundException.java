package com.example.demo.exceptions;

/**
 * Exception thrown when a product is not found in the system.
 * Typically used in the service layer when a product with the given identifier or name does not exist.
 */
public class ProductNotFoundException extends RuntimeException {

    /**
     * Constructs a new ProductNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining the exception
     */
    public ProductNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new ProductNotFoundException for the given product ID.
     * 
     *
     * @param id the ID of the product that was not found
     */
    public ProductNotFoundException(Long id) {
        super("Product not found with ID: " );  
    }
}
