package com.codebean.UserService.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 13:23
@Last Modified 10 Jan 2025 13:23
Version 1.0
*/

import com.codebean.UserService.dto.request.UserLoginDto;
import com.codebean.UserService.dto.request.UserRegReqDto;
import com.codebean.UserService.exception.ApiException;
import com.codebean.UserService.model.Role;
import com.codebean.UserService.model.User;
import com.codebean.UserService.service.AuthUserDetailService;
import com.codebean.UserService.service.UserService;
import com.codebean.UserService.service.ValidationService;
import com.codebean.UserService.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthUserDetailService authUserDetailService;

    @Autowired
    private ValidationService validator;

    @PostMapping(path = "/v1/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> CustomerRegister(@RequestBody UserRegReqDto dto, HttpServletRequest request) {

        // validasi data input
        this.validator.validate(dto, "FVUSR01001x", request);

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, Constants.CONFIRM_PASSWORD_NOT_MATCH, "FVUSR01001x", request);
        }

        // convert dto to model cara 1
        User newUser = new User();
        newUser.setRole(new Role("Customer"));
        BeanUtils.copyProperties(dto, newUser);

//        // convert dto to model cara 2
//        User customer = this.userService.custDTOtoUserModel(dto, "Customer", "sistem");
//        System.out.println("1>>" + customer);

        return this.authUserDetailService.registerUser(newUser, request);
    }

    @PostMapping("/v1/login")
    public ResponseEntity<Object> Login(@RequestBody UserLoginDto dto, HttpServletRequest request) {
        // validasi data input
        this.validator.validate(dto, "FVUSR02001", request);

        User userLoginReq = new User();
        BeanUtils.copyProperties(dto, userLoginReq);

        return this.authUserDetailService.loginUser(userLoginReq, request);

    }

    @PostMapping("/v1/refreshToken")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        try {
//            belum di buat
            return null;
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), "FVUSR01001x", request);
        }
    }

}

