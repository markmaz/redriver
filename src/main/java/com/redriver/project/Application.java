package com.redriver.project;

import com.redriver.project.repository.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

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
@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = StudentRepository.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
