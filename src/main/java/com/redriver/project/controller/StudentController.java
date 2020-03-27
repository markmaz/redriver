package com.redriver.project.controller;

import com.redriver.project.entity.Student;
import com.redriver.project.exceptions.NotFoundException;
import com.redriver.project.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Basic controller. I have the Get by ID to get a specific user for editing, a PUT for updating, a POST for creating.
 * If I were to flush this out more, I would probably add a PATCH for partial updates and of course a DELETE - a lot of
 * times my API deletes are soft deletes just making the record inactive. In which I case I would use a PATCH.
 */
@Controller
public class StudentController {
    private StudentService studentService;

    /**
     * Went with the Constructor injection because technically it is best practice. However, I know I could have done
     * field level injection in this case and been ok.
     * @param studentService
     */
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    /**
     * Basic GET by ID - This will throw a 404 if it can't be found.
     * @param id
     * @return
     * @throws NotFoundException
     */
    @GetMapping(value = "/student/{id}", produces = "application/json")
    public @ResponseBody Student getStudentById(@PathVariable String id) throws NotFoundException {
        return this.studentService.getStudentById(id);
    }

    /**
     * Optional request parameters. Performs the search, pagination and sort based on what is passed in. I have set some
     * default values when things are not passed in.
     * @param lastName
     * @param firstName
     * @param sort
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value="/student", produces = "application/json")
    public @ResponseBody List<Student> getStudents(@RequestParam("lastName") Optional<String> lastName,
                                                   @RequestParam("firstName") Optional<String> firstName,
                                                   @RequestParam("sort") Optional<String> sort,
                                                   @RequestParam("page") Optional<Integer> page,
                                                   @RequestParam("size") Optional<Integer> size){

        //Setting values based on presence
        Student student = new Student();
        lastName.ifPresent(student::setLastName);
        firstName.ifPresent(student::setFirstName);

        //Want to make sure the sorting is one of the acceptable fields. If it isn't we will default it.
        if(sort.isPresent()){
            if(!sort.get().equals(Student.FIRST_NAME) && !sort.get().equals(Student.LAST_NAME)){
                return this.studentService.getStudents(student, page.orElse(0), size.orElse(10), Student.LAST_NAME);
            }
        }

        //This will cover the rest.
        return this.studentService.getStudents(student, page.orElse(0), size.orElse(10), sort.orElse(Student.LAST_NAME));
    }

    @PostMapping(value = "/student")
    public @ResponseBody Student saveStudent(@RequestBody Student student){
        return  this.studentService.saveStudent(student);
    }

    /**
     * For now my Post and the Put are the same. Only 2 fields to change. Mainly here to show that I know what the Rest
     * API means. I added the Error in there to check for an ID and to prevent a new record being created.
     * @param student
     * @return
     */
    @PutMapping(value = "/student")
    public @ResponseBody Student editStudent(@RequestBody Student student) throws NotFoundException {
        if(StringUtils.isEmpty(student.getId())){
            throw new NotFoundException("The ID is missing from this message");
        }

        return this.saveStudent(student);
    }
}
