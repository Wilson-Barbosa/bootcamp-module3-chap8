package com.abutua.sellerregister.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ControllerExceptionHandler {
    
    // Controller Exceptions will go here
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationExceptionError(MethodArgumentNotValidException exception, HttpServletRequest request){

        ValidationError err = new ValidationError();

        // Generating the appropriate status code 
        HttpStatus statusCode = HttpStatus.UNPROCESSABLE_ENTITY;

        // Adds each error to the list
        exception.getBindingResult().getFieldErrors().forEach(e -> err.addErrorToList(e.getDefaultMessage()));

        // Populating the ValidationError object
        err.setTime(Instant.now());
        err.setStatusCode(statusCode.value());
        err.setPath(request.getRequestURI());
        err.setError("Validation error");
        err.setMessage("Your request was not processed"); 

        // Returns a ResponseEntity with its status and the error as a body
        return ResponseEntity.status(statusCode).body(err);
    }

}
