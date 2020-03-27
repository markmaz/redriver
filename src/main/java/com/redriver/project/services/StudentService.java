package com.redriver.project.services;

import com.redriver.project.entity.Student;
import com.redriver.project.exceptions.NotFoundException;

import java.util.List;

/*
    Write a web application to:

    Display a list of students with the ability to search by last name and/or first name.
    Edit a Student
    View Student Information
    Left Menu
    Suggested Development Environment:

    Java
    Spring Boot
    MongoDB
    Rest (Backend application)
    Bonus:

    Implement Pagination
    Spring Boot JPA Custom Query. Dynamic query based on what search criteria is used (first name, last name or both).
    How to Deliver the Assignment

    GIT
    Instructions on how to run the application including environment set up.
    Your application should automatically create the tables if they do not exist.
    Run on Tomcat
    Thanks,
 */
public interface StudentService {
    Student getStudentById(String id) throws NotFoundException;
    List<Student> getStudents(Student student, int page, int size, String sort);
    Student saveStudent(Student student);
}
