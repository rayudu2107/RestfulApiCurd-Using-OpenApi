package com.restfulapicrud.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.restfulapicrud.dto.StudentRequest;
import com.restfulapicrud.dto.StudentResponse;
import com.restfulapicrud.service.StudentService;
import com.restfulapicrud.util.ResponseStructure;

import java.util.List;

//Unit tests for the StudentController class.
public class StudentControllerTest {

	@Mock
	private StudentService studentService;

	@InjectMocks
	private StudentController studentController;

	// Initializes mocks before each test.
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	// addStudent method with valid input.
	@Test
	public void testAddStudent_ValidInput() {
		StudentRequest studentRequest = new StudentRequest();
		studentRequest.setStudentName("John Doe");
		studentRequest.setStudentMarks(95);
		studentRequest.setEmail("john.doe@example.com");
		studentRequest.setDepartment("Computer Science");
		ResponseEntity<ResponseStructure<StudentResponse>> expectedResponse = ResponseEntity.ok().build();
		when(studentService.addStudent(studentRequest)).thenReturn(expectedResponse);
		ResponseEntity<ResponseStructure<StudentResponse>> response = studentController.addStudent(studentRequest);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedResponse, response);
	}

	// findStudentById method for an existing student.
	@Test
	public void testFindStudentById_ExistingStudent() {
		int studentId = 1;
		ResponseEntity<ResponseStructure<StudentResponse>> expectedResponse = ResponseEntity.ok().build();
		when(studentService.findStudentById(studentId)).thenReturn(expectedResponse);
		ResponseEntity<ResponseStructure<StudentResponse>> response = studentController.findStudentById(studentId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedResponse, response);
	}

	// findStudentById method for a non-existing student.
	@Test
	public void testFindStudentById_NonExistingStudent() {
		int studentId = 1;
		ResponseEntity<ResponseStructure<StudentResponse>> expectedResponse = ResponseEntity.notFound().build();
		when(studentService.findStudentById(studentId)).thenReturn(expectedResponse);
		ResponseEntity<ResponseStructure<StudentResponse>> response = studentController.findStudentById(studentId);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(expectedResponse, response);
	}

	// updateStudentById method for an existing student.
	@Test
	public void testUpdateStudentById_ExistingStudent() {
		int studentId = 1;
		StudentRequest studentRequest = new StudentRequest();
		studentRequest.setStudentName("Updated Name");
		ResponseEntity<ResponseStructure<StudentResponse>> expectedResponse = ResponseEntity.ok().build();
		when(studentService.updateStudentById(studentRequest, studentId)).thenReturn(expectedResponse);
		ResponseEntity<ResponseStructure<StudentResponse>> response = studentController.updateStudentById(studentId,
				studentRequest);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedResponse, response);
	}

	// updateStudentById method for a non-existing student.
	@Test
	public void testUpdateStudentById_NonExistingStudent() {
		// Arrange
		int studentId = 1;
		StudentRequest studentRequest = new StudentRequest();
		studentRequest.setStudentName("John Doe");
		studentRequest.setStudentMarks(95);
		studentRequest.setEmail("john.doe@example.com");
		studentRequest.setDepartment("Computer Science");
		ResponseEntity<ResponseStructure<StudentResponse>> expectedResponse = ResponseEntity.notFound().build();
		when(studentService.updateStudentById(studentRequest, studentId)).thenReturn(expectedResponse);
		ResponseEntity<ResponseStructure<StudentResponse>> response = studentController.updateStudentById(studentId,
				studentRequest);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(expectedResponse, response);
	}

	// deleteStudentById method for an existing student.
	@Test
	public void testDeleteStudentById_ExistingStudent() {
		int studentId = 1;
		ResponseEntity<ResponseStructure<StudentResponse>> expectedResponse = ResponseEntity.ok().build();
		when(studentService.deleteStudentById(studentId)).thenReturn(expectedResponse);
		ResponseEntity<ResponseStructure<StudentResponse>> response = studentController.deleteStudentById(studentId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedResponse, response);
	}

	// deleteStudentById method for a non-existing student.
	@Test
	public void testDeleteStudentById_NonExistingStudent() {
		int studentId = 1;
		ResponseEntity<ResponseStructure<StudentResponse>> expectedResponse = ResponseEntity.notFound().build();
		when(studentService.deleteStudentById(studentId)).thenReturn(expectedResponse);
		ResponseEntity<ResponseStructure<StudentResponse>> response = studentController.deleteStudentById(studentId);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(expectedResponse, response);
	}

	// findAllStudents method with an empty list.
	@Test
	public void testFindAllStudents_EmptyList() {
		ResponseEntity<ResponseStructure<List<StudentResponse>>> expectedResponse = ResponseEntity.ok().build();
		when(studentService.findAllStudent()).thenReturn(expectedResponse);
		ResponseEntity<ResponseStructure<List<StudentResponse>>> response = studentController.findAllStudents();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedResponse, response);
	}

	// findAllStudents method with a non-empty list.
	@Test
	public void testFindAllStudents_NonEmptyList() {
		ResponseEntity<ResponseStructure<List<StudentResponse>>> expectedResponse = ResponseEntity.ok().build();
		when(studentService.findAllStudent()).thenReturn(expectedResponse);
		ResponseEntity<ResponseStructure<List<StudentResponse>>> response = studentController.findAllStudents();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedResponse, response);
	}

}
