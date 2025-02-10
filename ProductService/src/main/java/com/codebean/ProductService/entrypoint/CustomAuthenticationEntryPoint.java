package com.codebean.ProductService.entrypoint;

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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper mapper ;

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
        data.put("success", false);
        data.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        data.put("timestamp", LocalDateTime.now());
        data.put("error_detail", authException.getMessage());
        data.put("error_code", "FVAUTH401");
//		data.put("error", "Lakukan Otentikasi Terlebih Dahulu !!");
        response.getOutputStream().println(mapper.writeValueAsString(data));
    }
}
