package com.restfulapicrud.exception;

// Exception thrown when a student is not found by ID.
public class StudentNotFoundByIdException extends RuntimeException {
	public StudentNotFoundByIdException(String message) {
		super(message);
	}
}
