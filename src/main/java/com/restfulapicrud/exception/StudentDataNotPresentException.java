package com.restfulapicrud.exception;

// Exception thrown when student data is not found.
public class StudentDataNotPresentException extends RuntimeException {

	public StudentDataNotPresentException(String message) {
		super(message);
	}
}
