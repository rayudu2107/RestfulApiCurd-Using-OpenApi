package com.restfulapicrud.util;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

// Utility class to represent error responses.
@Component
@Getter
@Setter
public class ErrorStructure<T> {
	private int status;
	private String message;
	private T rootCause;

}
