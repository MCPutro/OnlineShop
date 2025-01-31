package com.codebean.UserService.entrypoint;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 21 Jan 2025 20:13
@Last Modified 21 Jan 2025 20:13
Version 1.0
*/

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Autowired
    ObjectMapper mapper ;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        // Menyediakan respons 401 Unauthorized dengan pesan JSON
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//        response.setContentType("application/json");
////        response.getWriter().write("{\"error\": \"Unauthorized access\", {\"error\":\""+authException.getMessage()+"\"}}");
//        response.getWriter().write("{\"error\": \"Unauthorized access\"}");

        response.setHeader("Content-Type", "application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Map<String, Object> data = new HashMap<>();
        data.put("status", false);
        data.put("timestamp", LocalDateTime.now());
        data.put("error", authException.getMessage());
//		data.put("error", "Lakukan Otentikasi Terlebih Dahulu !!");
        response.getOutputStream().println(mapper.writeValueAsString(data));
    }
}
