package com.codebean.UserService.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 13:23
@Last Modified 10 Jan 2025 13:23
Version 1.0
*/

import com.codebean.UserService.dto.request.UserRegisterDTO;
import com.codebean.UserService.exception.ValidateException;
import com.codebean.UserService.hadler.ResponseHandler;
import com.codebean.UserService.model.Role;
import com.codebean.UserService.model.User;
import com.codebean.UserService.service.UserService;
import com.codebean.UserService.service.ValidationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@RestController
@RequestMapping("/public/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidationService validator;

    @PostMapping(path = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody UserRegisterDTO dto, HttpServletRequest request) {

        // validasi data input
        this.validator.validate(dto, "FVUSR01001", request);

        // convert dto to model cara 1
        User newUser = User.builder()
                .role(new Role("customer"))
                .createdBy("sistem")
                .createdDate(new Date())
                .build();
        BeanUtils.copyProperties(dto, newUser);
        System.out.println("1>>" + newUser);

        // convert dto to model cara 2
        User user = this.userService.userRegisDTOtoModel(dto, "Customer", "sistem");
        System.out.println("1>>" + user);


        // panggil function save user
        ResponseEntity<Object> save = this.userService.save(user, request);
        System.out.println("2>>>>>>>>>" + save);
        return save;
    }
}
