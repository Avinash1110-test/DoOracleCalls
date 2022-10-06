package com.oracle.application.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<String> serviceException(ServiceException e) {
        return new ResponseEntity<>(e.getMessage(), e.getStatus());
    }
}
