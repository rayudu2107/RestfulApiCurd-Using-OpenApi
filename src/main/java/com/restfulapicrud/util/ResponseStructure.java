package com.restfulapicrud.util;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

// Utility class to represent response structures.
@Component
@Getter
@Setter
public class ResponseStructure<T> {
	private int status;
	private String message;
	private T data;
}
