package com.redriver.project.exceptions;

import com.redriver.project.entity.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Just a simple handler to show how to handle an exception
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(NotFoundException e){
        CustomError error = new CustomError(HttpStatus.NOT_FOUND);
        error.setDebugMessage(e.getMessage());
        error.setMessage("Student Not Found");
        return new ResponseEntity<>(error, error.getStatus());
    }
}
