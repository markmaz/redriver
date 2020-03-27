package com.redriver.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class CustomError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private HttpStatus status;


    public CustomError(){
        this.timestamp = LocalDateTime.now();
    }
    public CustomError(HttpStatus status) {
        this();
        this.status = status;
    }

    public CustomError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public CustomError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

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

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomError error = (CustomError) o;
        return status == error.status &&
                Objects.equals(timestamp, error.timestamp) &&
                Objects.equals(message, error.message) &&
                Objects.equals(debugMessage, error.debugMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, timestamp, message, debugMessage);
    }
}
