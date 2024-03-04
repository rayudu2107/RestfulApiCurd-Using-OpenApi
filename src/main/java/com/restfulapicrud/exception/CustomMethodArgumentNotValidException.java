package com.restfulapicrud.exception;

// Custom exception class for method argument validation errors.
public class CustomMethodArgumentNotValidException extends RuntimeException {
	public CustomMethodArgumentNotValidException(String message) {
		super(message);
	}

}
