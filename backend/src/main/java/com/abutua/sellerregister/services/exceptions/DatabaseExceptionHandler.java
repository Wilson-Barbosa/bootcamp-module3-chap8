package com.abutua.sellerregister.services.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.abutua.sellerregister.controllers.exceptions.StandardError;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class DatabaseExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFoundException(HttpServletRequest request, EntityNotFoundException exception){

        // Creating a StandardError
        StandardError standardError = new StandardError();

        // Initializing an attribute with the status code 404 (NOT FOUND)
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        // Populating the standartError object with data from the request
        standardError.setError("Database Validation Error");
        standardError.setPath(request.getRequestURI());
        standardError.setTime(Instant.now());
        standardError.setStatusCode(httpStatus.value());
        standardError.setMessage(exception.getMessage());

        return ResponseEntity.status(httpStatus).body(standardError);
    }

}
