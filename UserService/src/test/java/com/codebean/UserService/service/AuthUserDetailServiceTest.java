//package com.codebean.UserService.service;
//
//import com.codebean.UserService.model.Role;
//import com.codebean.UserService.model.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import static org.junit.jupiter.api.Assertions.*;
///*
//IntelliJ IDEA 2024.2.4 (Community Edition)
//Build #IC-242.23726.103, built on October 23, 2024
//@Author mcputro a.k.a. Mu'ti Cahyono Putro
//Created on 23 Jan 2025 18:39
//@Last Modified 23 Jan 2025 18:39
//Version 1.0
//*/
//
//@SpringBootTest
//class AuthUserDetailServiceTest {
//
//    @Autowired
//    private AuthUserDetailService authUserDetailService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Test
//    void createCustomer() {
//        User user = new User();
//        user.setUsername("user001");
//        user.setPassword("password_001");
//        user.setEmail("admin@gmail.com");
//        user.setRole(new Role("Customer"));
//
//        ResponseEntity<?> responseEntity = this.authUserDetailService.registerUser(user, null);
//        assertNotNull(responseEntity);
//        System.out.println(responseEntity.getBody());
//    }
//
//
//    @Test
//    void name() {
////        String ss = "$2a$10$6X6RR3583ACzjacmqV1lTe.Tgkqhw.G7TTcD4x38PWOyzdpPL1XIe";
//
//        User user = new User();
//        user.setUsername("user001");
//        user.setPassword("password_001");
//
//        String encode = this.passwordEncoder.encode((user.getUsername() + user.getPassword()));
//        System.out.println(encode);
//
//        boolean matches = this.passwordEncoder.matches((user.getUsername() + user.getPassword()), encode);
//        System.out.println(matches);
//    }
//
//    @Test
//    void Login() {
//
//        User user = new User();
//        user.setUsername("user001");
//        user.setPassword("password_001");
//
//        ResponseEntity<?> responseEntity = this.authUserDetailService.loginUser(user, null);
//        assertNotNull(responseEntity);
//        System.out.println(responseEntity.getBody());
//    }
//}