package com.codebean.UserService.service;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Jan 2025 15:34
@Last Modified 13 Jan 2025 15:34
Version 1.0
*/

import com.codebean.UserService.exception.ValidateException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ValidationService {

    @Autowired
    private Validator validator;

    public void validate(Object dto, String errorCode, HttpServletRequest request) {
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(dto);
        if (!constraintViolationSet.isEmpty()) {
            throw new ValidateException(constraintViolationSet, errorCode, request);
        }

    }


}
