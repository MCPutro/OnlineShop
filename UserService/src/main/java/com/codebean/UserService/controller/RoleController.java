package com.codebean.UserService.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 29 Jan 2025 16:17
@Last Modified 29 Jan 2025 16:17
Version 1.0
*/

import com.codebean.UserService.service.PermissionIService;
import com.codebean.UserService.service.RoleService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionIService permissionService;

    @GetMapping("/roles")
    public ResponseEntity<?> getAllRoles(HttpServletRequest request) {
        return this.roleService.findAll(null, request);
    }

    @GetMapping("/permissions")
    public ResponseEntity<?> getAllPermissions(HttpServletRequest request) {
        return this.permissionService.findAll(null, request);
    }
}
