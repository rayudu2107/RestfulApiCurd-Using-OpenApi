package com.restfulapicrud.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.restfulapicrud.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Unit tests for the StudentRepository class. 
@DataJpaTest
public class StudentRepositoryTest {

	@Mock
	private StudentRepository studentRepository;

	public StudentRepositoryTest() {
		MockitoAnnotations.openMocks(this);
	}

	// findById method with an existing student.
	@Test
	public void testFindById_ExistingStudent() {
		// Setup
		int studentId = 1;
		Student expectedStudent = new Student();
		expectedStudent.setStudentId(studentId);
		when(studentRepository.findById(studentId)).thenReturn(Optional.of(expectedStudent));

		// Execute
		Optional<Student> result = studentRepository.findById(studentId);

		// Verify
		assertTrue(result.isPresent());
		assertEquals(expectedStudent, result.get());
	}

	// findById method with a non-existing student.
	@Test
	public void testFindById_NonExistingStudent() {
		// Setup
		int studentId = 1;
		when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

		// Execute
		Optional<Student> result = studentRepository.findById(studentId);

		// Verify
		assertFalse(result.isPresent());
	}

	// saveStudent method.
	@Test
	public void testSaveStudent() {
		// Setup
		Student student = new Student();
		student.setStudentName("John Doe");
		student.setStudentMarks(95);
		student.setEmail("john.doe@example.com");
		student.setDepartment("Computer Science");

		// Execute
		studentRepository.save(student);

		// Verify
		verify(studentRepository, times(1)).save(student);
	}

	// deleteStudent method.
	@Test
	public void testDeleteStudent() {
		// Setup
		int studentId = 1;

		// Execute
		studentRepository.deleteById(studentId);

		// Verify
		verify(studentRepository, times(1)).deleteById(studentId);
	}

	// updateStudent method.
	@Test
	public void testUpdateStudent() {
		// Setup
		int studentId = 1;
		Student student = new Student();
		student.setStudentId(studentId);
		student.setStudentName("Updated Name");
		when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

		// Execute
		student.setStudentName("New Name");
		studentRepository.save(student);

		// Verify
		verify(studentRepository, times(1)).save(student);
	}

	// findAllStudents method.
	@Test
	public void testFindAllStudents() {
		// Setup
		List<Student> students = new ArrayList<>();
		Student student1 = new Student();
		student1.setStudentId(1);
		student1.setStudentName("John Doe");
		students.add(student1);
		Student student2 = new Student();
		student2.setStudentId(2);
		student2.setStudentName("Jane Doe");
		students.add(student2);
		when(studentRepository.findAll()).thenReturn(students);

		// Execute
		List<Student> result = studentRepository.findAll();

		// Verify
		assertEquals(2, result.size());
		assertEquals("John Doe", result.get(0).getStudentName());
		assertEquals("Jane Doe", result.get(1).getStudentName());
	}

}
