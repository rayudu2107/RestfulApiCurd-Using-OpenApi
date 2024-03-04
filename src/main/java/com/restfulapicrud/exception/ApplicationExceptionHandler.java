package com.restfulapicrud.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.restfulapicrud.util.ErrorStructure;
import com.restfulapicrud.util.ResponseStructure;

// Global exception handler for handling application-specific exceptions.
@RestControllerAdvice
public class ApplicationExceptionHandler {

	@Autowired
	private ErrorStructure<String> errorStructure;

	@ExceptionHandler(StudentNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> handleStudentNotFoundByIdException(StudentNotFoundByIdException ex) {
		errorStructure.setStatus(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(ex.getMessage());
		errorStructure.setRootCause("The requested student with the given ID is not found");
		return new ResponseEntity<>(errorStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(StudentDataNotPresentException.class)
	public ResponseEntity<ErrorStructure<String>> handleStudentDataNotPresentException(
			StudentDataNotPresentException ex) {
		errorStructure.setStatus(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(ex.getMessage());
		errorStructure.setRootCause("Student record not present");
		return new ResponseEntity<>(errorStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CustomMethodArgumentNotValidException.class)
	public ResponseEntity<ResponseStructure<String>> handleValidationException(
			CustomMethodArgumentNotValidException ex) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage("Validation error");
		responseStructure.setData("Invalid input: " + ex.getMessage());
		return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
	}
}
