package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptions {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> handleProductException(ProductNotFoundException ex){
		return new ResponseEntity<>("Product not found : "+ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<String> handleCategoryException(CategoryNotFoundException ex){
		return new ResponseEntity<>("Category not found : "+ex.getMessage(),HttpStatus.NOT_FOUND);
	}

}