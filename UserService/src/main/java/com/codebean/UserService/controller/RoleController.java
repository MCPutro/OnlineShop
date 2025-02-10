package com.codebean.UserService.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 29 Jan 2025 16:17
@Last Modified 29 Jan 2025 16:17
Version 1.0
*/

/**
 * Platform Code : ROL
 * Modul Code : CTRL03
 * FV = Failed Validation
 * FE = Failed Error
 * ex = FVROLCTRL03001 -> [FV] [ROL] [CTRL03] 001 -> [JENIS ERROR] [Platform Code] [MODUL CODE] [seq]
 */


import com.codebean.UserService.dto.request.RoleCreate;
import com.codebean.UserService.handler.Response;
import com.codebean.UserService.model.Role;
import com.codebean.UserService.service.RoleService;
import com.codebean.UserService.service.ValidateService;
import com.codebean.UserService.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RoleController {

    @Autowired
    private ValidateService validateService;

    @Autowired
    private RoleService roleService;


    @PreAuthorize("hasAuthority('MANAGE_ROLE')")
    @PostMapping(path = "/role-create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createRole(@RequestBody RoleCreate role, HttpServletRequest request) {
        this.validateService.validate(role, "FVROLCTRL03001", request);
        try {
            Role newRole = new Role();
            BeanUtils.copyProperties(role, newRole);

            return this.roleService.save(newRole, request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.ROLE_FAILED_TO_ADD, "FEROLCTRL03001", request);
        }
    }


    @PreAuthorize("hasAuthority('MANAGE_ROLE')")
    @PatchMapping(path = "/role/{roleId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updateRole(@PathVariable(value = "roleId") Long roleId,
                                        @RequestBody RoleCreate role,
                                        HttpServletRequest request
    ) {
        this.validateService.validate(role, "FVROLCTRL03011", request);
        try {
            Role newRole = new Role();
            BeanUtils.copyProperties(role, newRole);
            return this.roleService.update(roleId, newRole, request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.ROLE_FAILED_TO_UPDATE, "FEROLCTRL03011", request);
        }
    }


    @PreAuthorize("hasAuthority('VIEW_ROLE')")
    @GetMapping(path = "/roles",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getRoles(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                      @RequestParam(value = "sizePerPage", required = false, defaultValue = "50") Integer sizePerPage,
                                      HttpServletRequest request) {
        try {
            Pageable Pageable = PageRequest.of(page, sizePerPage);
            return this.roleService.findAll(Pageable, request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.ROLE_FAILED_TO_GET, "FEROLCTRL03021", request);
        }
    }


    @PreAuthorize("hasAuthority('VIEW_ROLE')")
    @GetMapping(path = "/role/{roleId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getRoleById(@PathVariable(value = "roleId") Long roleId,
                                         HttpServletRequest request
    ) {
        try {
            return this.roleService.findById(roleId, request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.ROLE_FAILED_TO_UPDATE, "FEROLCTRL03031", request);
        }
    }


    @PreAuthorize("hasAuthority('MANAGE_ROLE')")
    @DeleteMapping(path = "/role/{roleId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> deleteRoleById(@PathVariable(value = "roleId") Long roleId, HttpServletRequest request) {
        try {
            return this.roleService.delete(roleId, request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.ROLE_FAILED_TO_DELETE, "FEROLCTRL03041", request);
        }
    }


    //digunakan saat membuat user baru makannya pake MANAGE_USER
    @PreAuthorize("hasAnyAuthority('VIEW_ROLE','MANAGE_USER')")
    @GetMapping(path = "/roles/active",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getActiveRoles(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                            @RequestParam(value = "sizePerPage", required = false, defaultValue = "50") Integer sizePerPage,
                                            HttpServletRequest request) {
        try {
            Pageable Pageable = PageRequest.of(page, sizePerPage);
            return this.roleService.findByParam(Pageable, "status", "active", request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.ROLE_FAILED_TO_GET, "FEROLCTRL03021", request);
        }
    }
}
