package com.codebean.transactionservice.config;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 12 Feb 2025 13:03
@Last Modified 12 Feb 2025 13:03
Version 1.0
*/

import com.codebean.sharemodule.Jwt.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public AuditorAwareConfig auditorAwareConfig(){
        return new AuditorAwareConfig();
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}
