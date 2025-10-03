package com.example.demo.exceptions;

public class ProductNotFoundException extends RuntimeException{
	
	public ProductNotFoundException(String message) {
		super(message);
	}
	
	public ProductNotFoundException(Long id) {
		super();
	}
}