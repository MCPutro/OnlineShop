package com.codebean.UserService.service;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 29 Jan 2025 15:52
@Last Modified 29 Jan 2025 15:52
Version 1.0
*/

import com.codebean.UserService.core.iService;
import com.codebean.UserService.dto.PermissionDto;
import com.codebean.UserService.dto.response.RolePermissionRespDto;
import com.codebean.UserService.handler.Response;
import com.codebean.UserService.model.Role;
import com.codebean.UserService.model.RolePermissions;
import com.codebean.UserService.repository.RolePermissionRepository;
import com.codebean.UserService.repository.RoleRepository;
import com.codebean.UserService.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.*;

@Service
public class RoleService implements iService<Role> {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Map<Long, RolePermissionRespDto> mapRole = new HashMap<>();

    @Override
    public ResponseEntity<Object> save(Role role, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(Long id, Role role, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        try {
            List<RolePermissions> listRoleDetail = this.rolePermissionRepository.findAllByRole_IsActiveAndIsActive(true, true);

            mapRole.clear();

            for (RolePermissions rp : listRoleDetail) {
                // cek role udah ada belum
                RolePermissionRespDto rolePerById = mapRole.get(rp.getRole().getID());
                if (rolePerById == null) {
                    RolePermissionRespDto newRolePermission = new RolePermissionRespDto(rp.getRole().getID(), rp.getRole().getName(), rp.getIsActive());

                    newRolePermission.getPermissions().add(this.modelMapper.map(rp.getPermission(), PermissionDto.class));

                    mapRole.put(rp.getRole().getID(), newRolePermission);
                } else {
                    rolePerById.getPermissions().add(this.modelMapper.map(rp.getPermission(), PermissionDto.class));
                }
            }

            return Response.success(Constants.SUCCESS, mapRole.values().stream().toList(), request);
        } catch (Exception e) {
            return Response.internalServerError(e.getMessage(), "adsadad", request);
        }
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        return null;
    }


}
