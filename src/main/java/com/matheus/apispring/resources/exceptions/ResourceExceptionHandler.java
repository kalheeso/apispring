package com.matheus.apispring.resources.exceptions;

import com.matheus.apispring.services.exceptions.DataIntegrityException;
import com.matheus.apispring.services.exceptions.NullFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.matheus.apispring.services.exceptions.ObjectNotFoundException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e) {
        StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(),
                e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrityViolation(DataIntegrityException e) {
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(),
                e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(NullFieldException.class)
    public ResponseEntity<StandardError> dataIntegrityViolation(NullFieldException e) {
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(),
                e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandardError> dataIntegrityViolation(NoSuchElementException e) {
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(),
                "No object matches this ID", System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> methodArgumentNotValid(MethodArgumentNotValidException e) {
        ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(),
                "Validation error", System.currentTimeMillis());
        for (FieldError fe : e.getBindingResult().getFieldErrors()){
            error.addError(fe.getField(), fe.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


}