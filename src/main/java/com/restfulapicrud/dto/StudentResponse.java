package com.restfulapicrud.dto;

import lombok.Getter;
import lombok.Setter;

// Data Transfer Object (DTO) representing a student response.
@Getter
@Setter
public class StudentResponse {
	private int studentId;
	private String studentName;
	private int studentMarks;
	private String email;
	private String department;

}
