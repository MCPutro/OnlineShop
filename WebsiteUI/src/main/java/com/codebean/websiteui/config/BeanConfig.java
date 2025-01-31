package com.codebean.websiteui.config;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 27 Jan 2025 13:22
@Last Modified 27 Jan 2025 13:22
Version 1.0
*/

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
