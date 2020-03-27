package com.redriver.project.controller;

import com.redriver.project.entity.Student;
import com.redriver.project.exceptions.NotFoundException;
import com.redriver.project.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping(value = "/student/{id}", produces = "application/json")
    public @ResponseBody Student getStudentById(@PathVariable String id) throws NotFoundException {
        return this.studentService.getStudentById(id);
    }

    @GetMapping(value="/student", produces = "application/json")
    public @ResponseBody List<Student> getStudents(@RequestParam("lastName") Optional<String> lastName,
                                                   @RequestParam("firstName") Optional<String> firstName,
                                                   @RequestParam("sort") Optional<String> sort,
                                                   @RequestParam("page") Optional<Integer> page,
                                                   @RequestParam("size") Optional<Integer> size){
        Student student = new Student();
        lastName.ifPresent(student::setLastName);
        firstName.ifPresent(student::setFirstName);

        return this.studentService.getStudents(student, page.orElse(0), size.orElse(10), sort.orElse("lastName"));
    }

    @PostMapping(value = "/student")
    public @ResponseBody Student saveStudent(@RequestBody Student student){
        return  this.studentService.saveStudent(student);
    }
}
