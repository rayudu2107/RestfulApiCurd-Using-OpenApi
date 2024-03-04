package com.restfulapicrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restfulapicrud.model.Student;

/**
 * Repository interface for accessing and managing student entities in the
 * database. Extends JpaRepository to inherit basic CRUD operations.
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
