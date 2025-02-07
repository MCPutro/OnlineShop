package com.codebean.UserService.service;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 07 Feb 2025 23:26
@Last Modified 07 Feb 2025 23:26
Version 1.0
*/

/**
 * Platform Code : MDL
 * Modul Code : 04
 * FV = Failed Validation
 * FE = Failed Error
 * ex = FVMDL04001 -> [FV] [MDL] [04] 001 -> [JENIS ERROR] [Platform Code] [MODUL CODE] [seq]
 */

import com.codebean.UserService.dto.ModuleDto;
import com.codebean.UserService.dto.PermissionDto;
import com.codebean.UserService.handler.Response;
import com.codebean.UserService.model.Module;
import com.codebean.UserService.model.Permission;
import com.codebean.UserService.model.Role;
import com.codebean.UserService.model.RolePermission;
import com.codebean.UserService.repository.ModuleRepository;
import com.codebean.UserService.repository.RolePermissionRepository;
import com.codebean.UserService.repository.RoleRepository;
import com.codebean.UserService.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public ResponseEntity<Object> findByRoleId(Long roleId, HttpServletRequest request)  {
        try {
            if (roleId == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVMDL04001", request);
            }

            Optional<Role> optionalRole = this.roleRepository.findById(roleId);

            if (!optionalRole.isPresent()) {
                return Response.badRequest(Constants.BAD_DATA, "FVMDL04002", request);
            }

            return this.findByRole(optionalRole.get(), request);

        } catch (Exception e) {
            // NEED SAVE TO LOG
            e.printStackTrace();
            return Response.internalServerError(Constants.ROLE_FAILED_TO_DELETE, "FEMDL04001", request);
        }
    }

    public ResponseEntity<Object> findByRole(Role role, HttpServletRequest request) {
        try {
            if (role == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVMDL04011", request);
            }

//            List<RolePermission> listActivePermissionByRole = this.rolePermissionRepository.findByRoleAndIsActive(role, true);
//
//            Map<Long, ModuleDto> mapModuleDto = new HashMap<>();
//
//            for (RolePermission rolePermission : listActivePermissionByRole) {
//                Permission permission = rolePermission.getPermission();
//                Module module = permission.getModule();
//
//                ModuleDto moduleDto = mapModuleDto.get(module.getID());
//
//                if (moduleDto == null) {
//                    moduleDto = new ModuleDto(module.getID(), module.getName(), module.getPath(), module.getDescription(), module.getIsActive());
//                    PermissionDto permissionDto = this.modelMapper.map(permission, PermissionDto.class);
//                    moduleDto.addPermission(permissionDto);
//                    mapModuleDto.put(module.getID(), moduleDto);
//                } else {
//                    PermissionDto permissionDto = this.modelMapper.map(permission, PermissionDto.class);
//                    mapModuleDto.get(module.getID()).addPermission(permissionDto);
//                }
//            }

            List<ModuleDto> activeModulesByRole = this.getActiveModulesByRole(role);

            return Response.success(Constants.SUCCESS, activeModulesByRole, request);
        } catch (Exception e) {
            // NEED SAVE TO LOG
            e.printStackTrace();
            return Response.internalServerError(Constants.ROLE_FAILED_TO_DELETE, "FEMDL04011", request);
        }
    }


    public List<ModuleDto> getActiveModulesByRole(Role role) {
        List<RolePermission> listActivePermissionByRole = this.rolePermissionRepository.findByRoleAndIsActive(role, true);

        Map<Long, ModuleDto> mapModuleDto = new HashMap<>();

        for (RolePermission rolePermission : listActivePermissionByRole) {
            Permission permission = rolePermission.getPermission();
            Module module = permission.getModule();

            ModuleDto moduleDto = mapModuleDto.get(module.getID());

            if (moduleDto == null) {
                moduleDto = new ModuleDto(module.getID(), module.getName(), module.getPath(), module.getDescription(), module.getIsActive());
                PermissionDto permissionDto = this.modelMapper.map(permission, PermissionDto.class);
                moduleDto.addPermission(permissionDto);
                mapModuleDto.put(module.getID(), moduleDto);
            } else {
                PermissionDto permissionDto = this.modelMapper.map(permission, PermissionDto.class);
                mapModuleDto.get(module.getID()).addPermission(permissionDto);
            }
        }

        return mapModuleDto.values().stream().toList();
    }
}
