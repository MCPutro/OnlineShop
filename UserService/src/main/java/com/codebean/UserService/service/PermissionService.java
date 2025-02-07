//package com.codebean.UserService.service;
//
///*
//IntelliJ IDEA 2024.2.4 (Community Edition)
//Build #IC-242.23726.103, built on October 23, 2024
//@Author mcputro a.k.a. Mu'ti Cahyono Putro
//Created on 29 Jan 2025 18:47
//@Last Modified 29 Jan 2025 18:47
//Version 1.0
//*/
//
//import com.codebean.UserService.core.iService;
//import com.codebean.UserService.dto.PermissionDto;
//import com.codebean.UserService.handler.Response;
//import com.codebean.UserService.model.Permission;
//import com.codebean.UserService.repository.PermissionRepository;
//import com.codebean.UserService.utils.Constants;
//import jakarta.servlet.http.HttpServletRequest;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.awt.print.Pageable;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class PermissionService implements iService<Permission> {
//
//    @Autowired
//    private PermissionRepository permissionsRepository;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    private final List<PermissionDto> listPermissions = new ArrayList<>();
//
//    @Override
//    public ResponseEntity<Object> save(Permission permissions, HttpServletRequest request) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<Object> update(Long id, Permission permissions, HttpServletRequest request) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
//        return null;
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
//        try {
//            this.listPermissions.clear();
//            Iterable<Permission> all = this.permissionsRepository.findAll();
//            all.forEach(permission -> {
//                this.listPermissions.add(this.modelMapper.map(permission, PermissionDto.class));
//
//            });
//
//            return Response.success(Constants.SUCCESS, this.listPermissions, request);
//        } catch (Exception e) {
//            return Response.internalServerError(e.getMessage(), "adasd", request);
//        }
//    }
//
//    @Override
//    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
//        return null;
//    }
//}
