package com.complexica.test.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionController {
    @ExceptionHandler(value = ErrorResponseException.class)
    public ResponseEntity<Object> exception(ErrorResponseException exception) {
        return new ResponseEntity<>("Respose Error", HttpStatus.REQUEST_TIMEOUT);
    }
}