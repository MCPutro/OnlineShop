package com.codebean.ProductService.exception;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Jan 2025 16:48
@Last Modified 13 Jan 2025 16:48
Version 1.0
*/

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private final String ErrorCode;

    private final HttpServletRequest request;

//    private final HttpStatusCode status;

    public ApiException(String errorMessage, String ErrorCode, HttpServletRequest request) {
        super(errorMessage);
        this.ErrorCode = ErrorCode;
        this.request = request;
//        super(status, errorMessage, cause, messageDetailCode, messageDetailArguments);
    }
}
