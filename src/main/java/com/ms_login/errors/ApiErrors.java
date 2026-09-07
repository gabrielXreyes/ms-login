package com.ms_login.errors;

import org.springframework.http.HttpStatusCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ApiErrors {
    String message;
    List<String>  details;
    HttpStatusCode status;
    LocalDateTime timestamp;

    public ApiErrors(String message, List<String> details, HttpStatusCode status, LocalDateTime timestamp) {
        this.message = message;
        this.details = details;
        this.status = status;
        this.timestamp = timestamp;
    }

    public ApiErrors(HttpStatusCode status, String message, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public HttpStatusCode getStatus() {
        return status;
    }

    public void setStatus(HttpStatusCode status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
