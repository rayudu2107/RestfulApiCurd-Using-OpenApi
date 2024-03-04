package com.restfulapicrud.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restfulapicrud.dto.StudentRequest;
import com.restfulapicrud.dto.StudentResponse;
import com.restfulapicrud.model.Student;
import com.restfulapicrud.exception.CustomMethodArgumentNotValidException;
import com.restfulapicrud.exception.StudentDataNotPresentException;
import com.restfulapicrud.exception.StudentNotFoundByIdException;
import com.restfulapicrud.repository.StudentRepository;
import com.restfulapicrud.service.StudentService;
import com.restfulapicrud.util.ResponseStructure;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private ResponseStructure<StudentResponse> structure;

    // Method to convert StudentRequest to Student entity
    private Student convertToStudent(StudentRequest studentRequest) {
        Student student = new Student();
        student.setStudentName(studentRequest.getStudentName());
        student.setStudentMarks(studentRequest.getStudentMarks());
        student.setEmail(studentRequest.getEmail());
        student.setDepartment(studentRequest.getDepartment());
        return student;
    }

    // Method to convert Student entity to StudentResponse
    private StudentResponse convertToStudentResponse(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setStudentId(student.getStudentId());
        studentResponse.setStudentName(student.getStudentName());
        studentResponse.setStudentMarks(student.getStudentMarks());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setDepartment(student.getDepartment());
        return studentResponse;
    }

    // Method to validate StudentRequest
    public  Set<ConstraintViolation<Student>> validateStudent(Student student) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        return validator.validate(student);
    }


    @Override
    // Add a new student
    public ResponseEntity<ResponseStructure<StudentResponse>> addStudent(StudentRequest studentRequest) {
    	Set<ConstraintViolation<Student>> violations = validateStudent(convertToStudent(studentRequest));
        if (!violations.isEmpty()) {
            throw new CustomMethodArgumentNotValidException(
                    "Invalid input: Name, Email, and Department are required fields, "
                            + "Marks must be a non-negative number, and Email must contain '@' and end with '.com'.");
        }

        Student student = convertToStudent(studentRequest);
        student = studentRepo.save(student);
        StudentResponse studentResponse = convertToStudentResponse(student);
        structure.setStatus(HttpStatus.OK.value());
        structure.setMessage("Student Added");
        structure.setData(studentResponse);
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    @Override
    // Find a student by ID
    public ResponseEntity<ResponseStructure<StudentResponse>> findStudentById(int studentId) {
        Optional<Student> optional = studentRepo.findById(studentId);
        if (optional.isPresent()) {
            Student student = optional.get();
            StudentResponse studentResponse = convertToStudentResponse(student);
            structure.setStatus(HttpStatus.OK.value());
            structure.setMessage("Student Data Found");
            structure.setData(studentResponse);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new StudentNotFoundByIdException("Id not Found to fetch Student Data");
        }
    }

    @Override
    // Update a student by ID
    public ResponseEntity<ResponseStructure<StudentResponse>> updateStudentById(StudentRequest studentRequest,
            int studentId) {
    	Set<ConstraintViolation<Student>> violations = validateStudent(convertToStudent(studentRequest));
        if (!violations.isEmpty()) {
            throw new CustomMethodArgumentNotValidException(
                    "Invalid input: Name, Email, and Department are required fields, "
                            + "Marks must be a non-negative number, and Email must contain '@' and end with '.com'.");
        }

        Optional<Student> optional = studentRepo.findById(studentId);
        if (optional.isPresent()) {
            Student oldStudent = optional.get();
            Student student = convertToStudent(studentRequest);
            student.setStudentId(oldStudent.getStudentId());
            Student updatedStudent = studentRepo.save(student);
            StudentResponse studentResponse = convertToStudentResponse(updatedStudent);
            structure.setStatus(HttpStatus.OK.value());
            structure.setMessage("Student Data Updated Successfully");
            structure.setData(studentResponse);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new StudentNotFoundByIdException("Student Not Found to Update");
        }
    }

    @Override
    // Delete a student by ID
    public ResponseEntity<ResponseStructure<StudentResponse>> deleteStudentById(int studentId) {
        Optional<Student> optional = studentRepo.findById(studentId);
        if (optional.isPresent()) {
            Student student = optional.get();
            studentRepo.delete(student);
            StudentResponse studentResponse = convertToStudentResponse(student);
            structure.setStatus(HttpStatus.OK.value());
            structure.setMessage("Student Data Deleted Successfully");
            structure.setData(studentResponse);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new StudentNotFoundByIdException("Student Not Found to Delete");
        }
    }

    @Override
    // Find all students
    public ResponseEntity<ResponseStructure<List<StudentResponse>>> findAllStudent() {
        List<Student> studentList = studentRepo.findAll();
        if (!studentList.isEmpty()) {
            List<StudentResponse> list = new ArrayList<>();
            for (Student student : studentList) {
                StudentResponse studentResponse = convertToStudentResponse(student);
                list.add(studentResponse);
            }
            ResponseStructure<List<StudentResponse>> responseStructure = new ResponseStructure<>();
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("User List Found");
            responseStructure.setData(list);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        } else {
            throw new StudentDataNotPresentException("No Student Data Present");
        }
    }
}
