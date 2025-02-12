package com.codebean.transactionservice.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Jan 2025 14:01
@Last Modified 13 Jan 2025 14:01
Version 1.0
*/

import com.codebean.transactionservice.exception.ValidateException;
import com.codebean.transactionservice.handler.ResponseHandler;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorController {
    private List<String> errorList = new ArrayList<>();

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> constraintViolationException(ConstraintViolationException exception) {
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
        this.errorList.clear();
        exception.getConstraintViolations().forEach(violation -> {
            this.errorList.add("invalid "+violation.getPropertyPath()+" - "+violation.getMessage());
        });
        return new ResponseHandler().handleResponse(
                "exception.getMessage()", // String message,
                HttpStatus.BAD_REQUEST,// HttpStatus status,
                String.join(", ",this.errorList), // Object data,
                exception.getErrorCode(),// Object errorCode,
                exception.getRequest()// HttpServletRequest request
        );
    }

    // Menangani AccessDeniedException ketika pengguna tidak memiliki izin
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDenied(AccessDeniedException exception) {
        return new ResponseHandler().handleResponse(
                null, // String message,
                HttpStatus.FORBIDDEN,// HttpStatus status,
                exception.getMessage(), // Object data,
                "FORBIDDEN",// Object errorCode,
                null// HttpServletRequest request
        );
    }


}
