package com.codebean.ProductService.handler;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Jan 2025 14:11
@Last Modified 13 Jan 2025 14:11
Version 1.0
*/

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public ResponseEntity<Object> handleResponse(
            String message,
            HttpStatus status,
            Object data,
            Object errorCode,
            HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        if (status.isError()) {
            map.put("status", status.value());
            map.put("timestamp", LocalDateTime.now());
            map.put("success", !status.isError());
            if (errorCode != null) {
                map.put("error_code", errorCode);
                map.put("error_detail", data == null ? "" : data);
                map.put("path", request == null ? "" : request.getRequestURI());
                map.put("path2", request == null ? "" : request.getContextPath());
                map.put("method", request == null ? "" : request.getMethod());
            }
        } else {
            map.put("message", message);
            map.put("status", status.value());
            map.put("data", data == null ? "" : data);
            map.put("timestamp", LocalDateTime.now());
            map.put("success", !status.isError());
        }

        return new ResponseEntity<>(map, status);
    }
}
