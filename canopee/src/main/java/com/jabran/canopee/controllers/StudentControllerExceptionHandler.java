package com.jabran.canopee.controllers;

import com.jabran.canopee.entities.StudentErrorResponse;
import com.jabran.canopee.exceptions.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentControllerExceptionHandler {
    @ExceptionHandler(StudentNotFoundException.class)
    //Takes the adequate thrown exception
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException e) {

        StudentErrorResponse error = new StudentErrorResponse();

        error.setMessage(e.getMessage());
        error.setStatus(String.valueOf(HttpStatus.NOT_FOUND.value()));

        return new ResponseEntity<StudentErrorResponse>(error, HttpStatus.NOT_FOUND);
    }
}
