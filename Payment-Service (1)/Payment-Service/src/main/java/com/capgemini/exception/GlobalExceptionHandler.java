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

@ControllerAdvice
public class GlobalExceptionHandler  {
    @ExceptionHandler(NoSuchCustomerException.class)
    public ResponseEntity<ExceptionResponse> handleNoSuchCustomerException(NoSuchCustomerException ex) {
        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(),
                ex.getMessage(),  // ✅ Only taking the message from exception
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                null // Removing the hardcoded details
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }

        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(),
                "Validation Failed",
                "One or more fields have invalid values",
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                fieldErrors // ✅ Pass field validation errors
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

