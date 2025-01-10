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
import com.codebean.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public String createUser(@RequestBody UserRegisterDTO user) {
        try {




            this.userService.save()
            return "OK";
//            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
//            return ResponseEntity.internalServerError().build();
            return e.getMessage();
        }



    }
}
