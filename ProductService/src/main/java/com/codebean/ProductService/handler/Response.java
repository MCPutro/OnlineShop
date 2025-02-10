package com.codebean.ProductService.handler;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 27 Jan 2025 16:35
@Last Modified 27 Jan 2025 16:35
Version 1.0
*/

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

public class Response {
    public static ResponseEntity<Object> success(String message, Object data, HttpServletRequest request) {
        return new ResponseHandler().handleResponse(message, HttpStatus.OK, data, null, request);
    }

    public static ResponseEntity<Object> created(String message, Object data, HttpServletRequest request) {
        return new ResponseHandler().handleResponse(message, HttpStatus.CREATED, data, null, request);
    }

    public static ResponseEntity<Object> unauthorized(Object data, Object errorCode, HttpServletRequest request) {
        return new ResponseHandler().handleResponse(null, HttpStatus.UNAUTHORIZED, data, errorCode, request);
    }

    public static ResponseEntity<Object> badRequest(Object data, Object errorCode, HttpServletRequest request) {
        return new ResponseHandler().handleResponse(null, HttpStatus.BAD_REQUEST, data, errorCode, request);
    }

    public static ResponseEntity<Object> forbidden(Object data, Object errorCode, HttpServletRequest request) {
        return new ResponseHandler().handleResponse(null, HttpStatus.FORBIDDEN, data, errorCode, request);
    }

    public static ResponseEntity<Object> internalServerError(Object data, Object errorCode, HttpServletRequest request) {
        return new ResponseHandler().handleResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, data, errorCode, request);
    }
}
