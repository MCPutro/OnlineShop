package com.codebean.UserService.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 06 Feb 2025 17:12
@Last Modified 06 Feb 2025 17:12
Version 1.0
*/

/**
 * Platform Code : USR
 * Modul Code : CTRL01
 * FV = Failed Validation
 * FE = Failed Error
 * ex = FVUSRCTRL01001 -> [FV] [USR] [CTRL01] 001 -> [JENIS ERROR] [Platform Code] [MODUL CODE] [seq]
 */

import com.codebean.UserService.dto.UserDetailDto;
import com.codebean.UserService.dto.request.ChangePasswordDto;
import com.codebean.UserService.dto.request.UserRegister;
import com.codebean.UserService.dto.request.UserSearchDto;
import com.codebean.UserService.dto.request.UserUpdate;
import com.codebean.UserService.handler.Response;
import com.codebean.UserService.model.Role;
import com.codebean.UserService.model.User;
import com.codebean.UserService.service.UserService;
import com.codebean.UserService.service.ValidateService;
import com.codebean.UserService.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidateService validateService;

    @PreAuthorize("hasAuthority('MANAGE_USER')")
    @PostMapping(path = "/user-create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createUser(@RequestBody UserRegister userRegister,
                                        HttpServletRequest request) {
        this.validateService.validate(userRegister, "FVUSRCTRL01001", request);
        if (!userRegister.getPassword().equals(userRegister.getConfirmPassword())) {
            return Response.badRequest(Constants.CONFIRM_PASSWORD_NOT_MATCH, "FVUSRCTRL01002", request);
        }
        User user = new User();
        BeanUtils.copyProperties(userRegister, user);
        user.setRole(new Role(userRegister.getRole()));
        return this.userService.save(user, request);
    }


    @PreAuthorize("hasAuthority('VIEW_USER')")
    @GetMapping(path = "/user/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getUserById(@PathVariable(value = "userId") Long userId,
                                         HttpServletRequest request) {
        return this.userService.findById(userId, request);
    }


    @GetMapping(path = "/user",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getUserByToken(HttpServletRequest request) {
        try {
            // get userid from token
            Long userId = Long.valueOf((String) request.getAttribute(Constants.USER_ID));

            return this.userService.findById(userId, request);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR : " + e.getMessage());
            return Response.internalServerError(Constants.ACCOUNT_FAILED_TO_GET, "FEUSRCTRL01021", request);
        }
    }


    @PreAuthorize("hasAuthority('VIEW_USER')")
    @GetMapping(path = "/users",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAllUsers(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "sizePerPage", required = false, defaultValue = "50") Integer sizePerPage,
            HttpServletRequest request
    ) {
        Pageable Pageable = PageRequest.of(page, sizePerPage);
        return this.userService.findAll(Pageable, request);
    }


    @PutMapping(path = "/user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updateUserProfileByToken(@RequestBody UserUpdate dto,
                                                      HttpServletRequest request
    ) {
        // validate dto
        this.validateService.validate(dto, "FVUSRCTRL01051", request);

        try {
            // get userid from token
            Long userId = Long.valueOf((String) request.getAttribute(Constants.USER_ID));

            User user = new User();
            BeanUtils.copyProperties(dto, user);

            return this.userService.update(userId, user, request);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR : " + e.getMessage());
            return Response.internalServerError(Constants.ACCOUNT_FAILED_TO_UPDATE, "FEUSRCTRL01051", request);
        }
    }


    @PreAuthorize("hasAnyAuthority('MANAGE_USER')")
    @PutMapping(path = "/user/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updateUserProfileByUserId(@PathVariable(value = "userId") Long userId,
                                                       @RequestBody UserUpdate dto,
                                                       HttpServletRequest request
    ) {
        // validate dto
        this.validateService.validate(dto, "FVUSRCTRL01061", request);
        try {
            User user = new User();
            BeanUtils.copyProperties(dto, user);

            return this.userService.update(userId, user, request);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR : " + e.getMessage());
            return Response.internalServerError(Constants.ACCOUNT_FAILED_TO_UPDATE, "FEUSRCTRL01061", request);
        }
    }


    @PreAuthorize("hasAuthority('VIEW_USER')")
    @GetMapping(path = "/find/user",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> searchUsers(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "sizePerPage", required = false, defaultValue = "10") Integer sizePerPage,
            @RequestParam(required = false) String sortType, // asc or desc
            @RequestParam(required = false) String sortBy, // kolom yang di sorting
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) Boolean isActive,
            HttpServletRequest request
    ) {
        PageRequest pageRequest;
        Sort sort;
        if (sortType == null || "".equals(sortType) || sortBy == null || "".equals(sortBy)) {
            pageRequest = PageRequest.of(page, sizePerPage);
        } else {
            if (sortType.equalsIgnoreCase("asc")) {
                sort = Sort.by(Sort.Order.asc(sortBy));
            } else {
                sort = Sort.by(Sort.Order.desc(sortBy));
            }
            pageRequest = PageRequest.of(page, sizePerPage, sort);
        }

        UserSearchDto build = UserSearchDto.builder()
                .username(username)
                .email(email)
                .name(name)
                .role(role == null || role.equalsIgnoreCase("") ? null : role)
                .status(isActive)
                .build();

        return this.userService.search(pageRequest, build, request);

//        if (by.equalsIgnoreCase("username") || by.toLowerCase().equals("email") || by.toLowerCase().equals("rolename")) {
//            return this.userService.findByParam(Pageable, by, search, request);
//        }
//
//        return this.userService.findAll(Pageable, request);
    }


    @PreAuthorize("hasAnyAuthority('MANAGE_USER')")
    @DeleteMapping(path = "/user/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> deleteUserByUserId(@PathVariable(value = "userId") Long userId,
                                                HttpServletRequest request
    ) {
        try {
            return this.userService.delete(userId, request);
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("ERROR : " + e.getMessage());
            return Response.internalServerError(Constants.ACCOUNT_FAILED_TO_UPDATE, "FEUSRCTRL01071", request);
        }
    }


    @PostMapping(path = "/user/change-password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changePasswordByToken(@RequestBody ChangePasswordDto dto, HttpServletRequest request) {
        this.validateService.validate(dto, "FEUSRCTRL01081", request);

        try {

            // get userid from token
            Long userId = Long.valueOf((String) request.getAttribute(Constants.USER_ID));

            if (!userId.equals(dto.getUserId())) {
                return Response.badRequest(Constants.BAD_DATA, "FVUSRCTRL01082", request);
            }

            if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
                return Response.badRequest(Constants.CONFIRM_PASSWORD_NOT_MATCH, "FVUSRCTRL01082", request);
            }

            return this.userService.changePassword(dto.getUserId(), dto.getCurrentPassword(), dto.getNewPassword(), request);
        } catch (Exception e) {
            return Response.internalServerError(Constants.ACCOUNT_FAILED_TO_UPDATE, "FEUSRCTRL01082", request);
        }
    }


}
