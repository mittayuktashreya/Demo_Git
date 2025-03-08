package com.capgemini.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ExceptionResponse {
    private LocalDateTime timestamp;
    private String message;
    private String details;
    private String httpCodeMessage;
    private Map<String, String> fieldErrors; // ✅ Add this field for validation errors

    // Constructor for validation errors
    public ExceptionResponse(LocalDateTime timestamp, String message, String details, String httpCodeMessage, Map<String, String> fieldErrors) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.httpCodeMessage = httpCodeMessage;
        this.fieldErrors = fieldErrors;
    }

    // Constructor for general exceptions
    public ExceptionResponse(LocalDateTime timestamp, String message, String details, String httpCodeMessage) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.httpCodeMessage = httpCodeMessage;
    }

    // Getters and Setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getHttpCodeMessage() {
        return httpCodeMessage;
    }

    public void setHttpCodeMessage(String httpCodeMessage) {
        this.httpCodeMessage = httpCodeMessage;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
