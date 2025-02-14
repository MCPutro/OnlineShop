package com.codebean.UserService.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 09 Feb 2025 22:23
@Last Modified 09 Feb 2025 22:23
Version 1.0
*/

import com.codebean.UserService.service.ModuleService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

        @GetMapping(path = "/modules", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getModules(HttpServletRequest request) {
        return this.moduleService.findAllModules(request);
    }

//    @GetMapping(path = "/modules2", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> getModulesByStatusTest(HttpServletRequest request) {
//        return this.moduleService.findAllModulesByStatus(true, request);
//    }

    @GetMapping(path = "/module/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getModuleByModuleStatusAndPermissionStatus(HttpServletRequest request) {
        return this.moduleService.findAllByModuleStatusAndPermissionStatus(true, true, request);
    }

    @GetMapping(path = "/module/roleId/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getModuleByRoleId(@PathVariable(value = "roleId") Long roleId, HttpServletRequest request) {
        return this.moduleService.findByRoleId(roleId, request);
    }

    @GetMapping(path = "/module/roleName/{roleName}")
    public ResponseEntity<?> getModuleByRoleName(@PathVariable(value = "roleName") String roleName, HttpServletRequest request) {
        return this.moduleService.findByRoleName(roleName, request);
    }

}
