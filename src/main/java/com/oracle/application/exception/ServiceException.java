package com.oracle.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ServiceException extends RuntimeException {

    private String message;
    private HttpStatus status;
}
