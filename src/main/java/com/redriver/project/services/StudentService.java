package com.redriver.project.services;

import com.redriver.project.entity.Student;
import com.redriver.project.exceptions.NotFoundException;

import java.util.List;

public interface StudentService {
    Student getStudentById(String id) throws NotFoundException;
    List<Student> getStudents(Student student, int page, int size, String sort);
    Student saveStudent(Student student);
}
