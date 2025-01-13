package com.codebean.UserService.exception;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Jan 2025 17:29
@Last Modified 13 Jan 2025 17:29
Version 1.0
*/

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.Getter;

import java.util.Set;

@Getter
public class ValidateException extends ConstraintViolationException {

    private final String ErrorCode;

    private final HttpServletRequest request;

    public ValidateException(Set<? extends ConstraintViolation<?>> constraintViolations, String ErrorCode, HttpServletRequest request) {
        super( constraintViolations);
        this.ErrorCode = ErrorCode;
        this.request = request;
    }
}
