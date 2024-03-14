package com.abutua.sellerregister.controllers.exceptions;

import java.time.Instant;

public class StandardError {

    // Information that will compose an error response
    private Instant time;
    private String message;
    private String error;
    private Integer statusCode;
    private String path;

    // Empty Constructor
    public StandardError(){

    }

    // Constructor
    public StandardError(Instant time, String message, String error, Integer statusCode, String path) {
        this.time = time;
        this.message = message;
        this.error = error;
        this.statusCode = statusCode;
        this.path = path;
    }

    // Setters and Getters
    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
