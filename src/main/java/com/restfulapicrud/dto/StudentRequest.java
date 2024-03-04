package com.restfulapicrud.dto;
import lombok.Getter;
import lombok.Setter;


// Data Transfer Object (DTO) representing a student request.
@Getter
@Setter
public class StudentRequest {
	private String studentName;
	private int studentMarks;
	private String email;
	private String department;

}
