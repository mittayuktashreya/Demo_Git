package com.capgemini.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(NoSuchCustomerException.class)
    public ResponseEntity handleNoSuchCustomerException(NoSuchCustomerException ex) {
       return new ResponseEntity(ex.getLocalizedMessage(),HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex) {
        Set<String> errors=ex.getBindingResult().getFieldErrors().stream().map(error->error.getDefaultMessage()).collect(Collectors.toSet());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

