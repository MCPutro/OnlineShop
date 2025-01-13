package com.codebean.UserService.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 13:23
@Last Modified 10 Jan 2025 13:23
Version 1.0
*/

import com.codebean.UserService.dto.request.CustomerRegReqDTO;
import com.codebean.UserService.model.Role;
import com.codebean.UserService.model.User;
import com.codebean.UserService.service.UserService;
import com.codebean.UserService.service.ValidationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/public/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidationService validator;

    @PostMapping(path = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody CustomerRegReqDTO dto, HttpServletRequest request) {

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
        User customer = this.userService.customerRegisDTOtoModel(dto, "Customer", "sistem");
        System.out.println("1>>" + customer);


        // panggil function save user
        return this.userService.save(customer, request);
    }

    @GetMapping(path = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllUsers(HttpServletRequest request) {
        return this.userService.findAll(null, request);
    }
}
