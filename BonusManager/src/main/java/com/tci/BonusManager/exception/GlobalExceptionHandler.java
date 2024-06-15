package com.tci.BonusManager.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoHandlerFoundException.class)
    // Handles NoHandlerFoundException and returns an HTTP response with a BAD_REQUEST status code
    public ResponseEntity<ErrorDetails> noHandler(NoHandlerFoundException ex, WebRequest wr) {
        // Constructs and returns an ErrorDetails object with the exception message, request description, and current date-time
        return new ResponseEntity<>(new ErrorDetails(ex.getMessage(), wr.getDescription(false), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    // Handles generic exceptions and returns an HTTP response with a BAD_REQUEST status code
    public ResponseEntity<ErrorDetails> parentException(Exception ex, WebRequest wr) {
        // Constructs and returns an ErrorDetails object with the exception message, request description, and current date-time
        return new ResponseEntity<>(new ErrorDetails(ex.getMessage(), wr.getDescription(false), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    // Handles MethodArgumentNotValidException and returns an HTTP response with a BAD_REQUEST status code
    public ResponseEntity<ErrorDetails> methodArgValidException(MethodArgumentNotValidException ex, WebRequest wr) {
        // Constructs and returns an ErrorDetails object with the exception message, request description, and current date-time
        return new ResponseEntity<>(new ErrorDetails(ex.getMessage(), wr.getDescription(false), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TCIBonusManagerException.class)
    // Handles TCIBonusManagerException and returns an HTTP response with a BAD_REQUEST status code
    public ResponseEntity<ErrorDetails> gymException(TCIBonusManagerException ex, WebRequest wr) {
        // Constructs and returns an ErrorDetails object with the exception message, request description, and current date-time
        return new ResponseEntity<>(new ErrorDetails(ex.getMessage(), wr.getDescription(false), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }


}
