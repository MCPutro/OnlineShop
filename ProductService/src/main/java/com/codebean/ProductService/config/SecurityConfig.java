package com.codebean.ProductService.config;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 31 Jan 2025 11:31
@Last Modified 31 Jan 2025 11:31
Version 1.0
*/

import com.codebean.ProductService.entrypoint.CustomAuthenticationEntryPoint;
import com.codebean.ProductService.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(tmp -> tmp.disable())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(
                                "/auth/**"
                                ,"/api/v1/shop/category"
                                ,"/api/v1/shop/products"
                                ,"/api/v1/shop/product/**"
                        ).permitAll() // Endpoint yang tidak memerlukan autentikasi

                        .anyRequest().authenticated() // Semua request lainnya memerlukan autentikasi
                )
                .addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class) // filter jwt
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exeption -> exeption.authenticationEntryPoint(this.customAuthenticationEntryPoint))
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
        ;

        return http.build();
    }
}
