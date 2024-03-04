package com.restfulapicrud.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.restfulapicrud.dto.StudentRequest;
import com.restfulapicrud.dto.StudentResponse;
import com.restfulapicrud.util.ResponseStructure;

// Service interface for managing student-related operations.
public interface StudentService {

	// Add a new student.
	ResponseEntity<ResponseStructure<StudentResponse>> addStudent(StudentRequest studentRequest);

	// Find a student by ID.
	ResponseEntity<ResponseStructure<StudentResponse>> findStudentById(int studentId);

	// Update a student by ID.
	ResponseEntity<ResponseStructure<StudentResponse>> updateStudentById(StudentRequest studentRequest, int studentId);

	// Find all students.
	ResponseEntity<ResponseStructure<List<StudentResponse>>> findAllStudent();

	// Delete a student by ID.
	ResponseEntity<ResponseStructure<StudentResponse>> deleteStudentById(int studentId);
}
