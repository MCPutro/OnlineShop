package com.codebean.UserService.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 13:23
@Last Modified 10 Jan 2025 13:23
Version 1.0
*/

/**
 * Platform Code : AUT
 * Modul Code : CTRL04
 * FV = Failed Validation
 * FE = Failed Error
 * ex = FVAUTCTRL04001 -> [FV] [AUT] [CTRL04] 001 -> [JENIS ERROR] [Platform Code] [MODUL CODE] [seq]
 */

import com.codebean.UserService.dto.request.CustomerRegister;
import com.codebean.UserService.dto.request.UserLoginReq;
import com.codebean.UserService.handler.Response;
import com.codebean.UserService.model.Role;
import com.codebean.UserService.model.User;
import com.codebean.UserService.service.AuthUserDetailService;
import com.codebean.UserService.service.ValidateService;
import com.codebean.UserService.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthUserDetailService authUserDetailService;

    @Autowired
    private ValidateService validateService;

    @PostMapping(path = "/v1/register/customer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> customerRegister(@RequestBody CustomerRegister customerRegister,
                                              HttpServletRequest request
    ) {
        this.validateService.validate(customerRegister, "FVAUTCTRL04001", request);

        try {
            if (!customerRegister.getPassword().equals(customerRegister.getConfirmPassword())) {
                return Response.badRequest(Constants.CONFIRM_PASSWORD_NOT_MATCH, "FVAUTCTRL04001", request);
            }

            User newUser = new User();
            newUser.setRole(new Role("Customer"));
            BeanUtils.copyProperties(customerRegister, newUser);

            return this.authUserDetailService.registerUser(newUser, request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.BAD_DATA, "FVAUTCTRL04001", request);
        }

    }

    @PostMapping(path = "/v1/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> login(@RequestBody UserLoginReq userLoginReq,
                                   HttpServletRequest request
    ) {
        this.validateService.validate(userLoginReq, "FVAUTCTRL04011", request);

        try {

            User newUser = new User();
            BeanUtils.copyProperties(userLoginReq, newUser);

            return this.authUserDetailService.login(newUser, request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.BAD_DATA, "FEAUTCTRL04011", request);
        }

    }
}
