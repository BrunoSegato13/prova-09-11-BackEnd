package com.prova.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exceptions.class)
    public ResponseEntity<CustomException> error(Exceptions e, HttpServletRequest request){
        CustomException exception = new CustomException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }
}
