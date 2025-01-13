package com.codebean.UserService.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Jan 2025 14:01
@Last Modified 13 Jan 2025 14:01
Version 1.0
*/

import com.codebean.UserService.exception.ValidateException;
import com.codebean.UserService.hadler.ResponseHandler;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> constraintViolationException(ConstraintViolationException exception) {
//        System.out.println("xxxxxxx -- ConstraintViolationException");
        return new ResponseHandler().handleResponse(
                exception.getMessage(), // String message,
                HttpStatus.BAD_REQUEST,// HttpStatus status,
                null, // Object obj,
                null,// Object errorCode,
                null// HttpServletRequest request
        );
    }

    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<Object> validationException(ValidateException exception) {
        System.out.println("xxxxxxx -- validationException");
        System.out.println(exception.getErrorCode());
        System.out.println(exception.getMessage());
        System.out.println(exception.getRequest().getRequestURI());
        return new ResponseHandler().handleResponse(
                exception.getMessage(), // String message,
                HttpStatus.BAD_REQUEST,// HttpStatus status,
                null, // Object obj,
                exception.getErrorCode(),// Object errorCode,
                exception.getRequest()// HttpServletRequest request
        );
    }
}
