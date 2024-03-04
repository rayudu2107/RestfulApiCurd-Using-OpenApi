package com.restfulapicrud.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restfulapicrud.dto.StudentRequest;
import com.restfulapicrud.dto.StudentResponse;
import com.restfulapicrud.service.StudentService;
import com.restfulapicrud.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Common base path for all endPoints in this controller
    @PostMapping
    @Operation(summary = "Add a new student")
    public ResponseEntity<ResponseStructure<StudentResponse>> addStudent(@RequestBody StudentRequest studentRequest) {
        return studentService.addStudent(studentRequest);
    }

    @GetMapping("/{studentId}")
    @Operation(summary = "Find a student by ID")
    public ResponseEntity<ResponseStructure<StudentResponse>> findStudentById(@PathVariable int studentId) {
        return studentService.findStudentById(studentId);
    }

    @PutMapping("/{studentId}")
    @Operation(summary = "Update a student by ID")
    public ResponseEntity<ResponseStructure<StudentResponse>> updateStudentById(@PathVariable int studentId,
            @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudentById(studentRequest, studentId);
    }

    @DeleteMapping("/{studentId}")
    @Operation(summary = "Delete a student by ID")
    public ResponseEntity<ResponseStructure<StudentResponse>> deleteStudentById(@PathVariable int studentId) {
        return studentService.deleteStudentById(studentId);
    }

    @GetMapping
    @Operation(summary = "Find all students")
    public ResponseEntity<ResponseStructure<List<StudentResponse>>> findAllStudents() {
        return studentService.findAllStudent();
    }
}
