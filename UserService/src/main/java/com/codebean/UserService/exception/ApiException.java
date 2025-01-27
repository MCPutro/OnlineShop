package com.codebean.UserService.exception;

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
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class ApiException extends ResponseStatusException {
    private final String ErrorCode;

    private final HttpServletRequest request;

    public ApiException(HttpStatusCode status, String reason, String errorCode, HttpServletRequest request) {
        super(status, reason);
        this.ErrorCode = errorCode;
        this.request = request;
    }


}
