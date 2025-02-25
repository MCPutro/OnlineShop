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
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAllModules(HttpServletRequest request) {
        try {
            List<Module> modules = moduleRepository.getAllModules();

            List<ModuleDto> moduleDto = this.listModelModulesToDto(modules);

            return Response.success(Constants.SUCCESS, moduleDto, request);
        } catch (Exception e) {
            return Response.internalServerError(Constants.MODULE_FAILED_TO_GET, "FEMDL04001", request);
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAllModulesByStatus(Boolean status, HttpServletRequest request) {
        try {
            if (status == null) {
                status = Boolean.TRUE;
            }

            List<Module> listActiveModules = this.moduleRepository.findAllByIsActive(status);

            List<ModuleDto> moduleDto = this.listModelModulesToDto(listActiveModules);

            return Response.success(Constants.SUCCESS, moduleDto, request);
        } catch (Exception e) {
            return Response.internalServerError(Constants.MODULE_FAILED_TO_GET, "FEMDL04011", request);
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAllByModuleStatusAndPermissionStatus(Boolean moduleStatus, Boolean permissionStatus, HttpServletRequest request) {
        try {
            if (moduleStatus == null || permissionStatus == null) {
                return Response.badRequest(Constants.MODULE_FAILED_TO_GET, "FVMDL04021", request);
            }

            List<Module> allByModuleStatusAndPermissionsStatus = this.moduleRepository.findAllByModuleStatusAndPermissionsStatus(moduleStatus, permissionStatus);

            // filter status permission sesuai dengan permissionStatus
            allByModuleStatusAndPermissionsStatus.stream().forEach(module -> {
                List<Permission> list = module.getPermissions().stream().filter(permission -> permission.getIsActive().equals(permissionStatus)).toList();
                module.setPermissions(list);
            });

            List<ModuleDto> listModuleDto = this.listModelModulesToDto(allByModuleStatusAndPermissionsStatus);

            return Response.success(Constants.SUCCESS, listModuleDto, request);
        } catch (Exception e) {
            return Response.internalServerError(Constants.MODULE_FAILED_TO_GET, "FEMDL04021", request);
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findByRoleId(Long roleId, HttpServletRequest request) {
        try {
            if (roleId == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVMDL04031", request);
            }

            Optional<Role> optionalRole = this.roleRepository.findById(roleId);

            if (!optionalRole.isPresent()) {
                return Response.badRequest(Constants.BAD_DATA, "FVMDL04032", request);
            }

            return this.findByRole(optionalRole.get(), request);

        } catch (Exception e) {
            // NEED SAVE TO LOG
            e.printStackTrace();
            return Response.internalServerError(Constants.MODULE_FAILED_TO_GET, "FEMDL04031", request);
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findByRoleName(String roleName, HttpServletRequest request) {
        try {
            if (roleName == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVMDL04031", request);
            }

            Optional<Role> optionalRole = this.roleRepository.findFirstByName(roleName);

            if (!optionalRole.isPresent()) {
                return Response.badRequest(Constants.BAD_DATA, "FVMDL04032", request);
            }

            return this.findByRole(optionalRole.get(), request);

        } catch (Exception e) {
            // NEED SAVE TO LOG
            e.printStackTrace();
            return Response.internalServerError(Constants.MODULE_FAILED_TO_GET, "FEMDL04031", request);
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findByRole(Role role, HttpServletRequest request) {
        try {
            if (role == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVMDL04041", request);
            }

            List<ModuleDto> activeModulesByRole = this.getActiveModulesByRole(role);

            if (activeModulesByRole == null) {
                return Response.badRequest(Constants.MODULE_FAILED_TO_GET, "FVMDL04042", request);
            }

            return Response.success(Constants.SUCCESS, activeModulesByRole, request);
        } catch (Exception e) {
            // NEED SAVE TO LOG
            e.printStackTrace();
            return Response.internalServerError(Constants.MODULE_FAILED_TO_GET, "FEMDL04041", request);
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    public List<ModuleDto> getActiveModulesByRole(Role role) {
        try {
            List<RolePermission> listActivePermissionByRole = this.rolePermissionRepository.findByRoleAndIsActive(role, true);

            Map<Long, ModuleDto> mapModuleDto = new HashMap<>();

            for (RolePermission rolePermission : listActivePermissionByRole) {
                Permission permission = rolePermission.getPermission();
                Module module = permission.getModule();

                ModuleDto moduleDto = mapModuleDto.get(module.getId());

                if (moduleDto == null) {
                    moduleDto = new ModuleDto(module.getId(), module.getName(), module.getPath(), module.getDescription(), module.getIsActive());
                    PermissionDto permissionDto = this.modelMapper.map(permission, PermissionDto.class);
                    moduleDto.addPermission(permissionDto);
                    mapModuleDto.put(module.getId(), moduleDto);
                } else {
                    PermissionDto permissionDto = this.modelMapper.map(permission, PermissionDto.class);
                    mapModuleDto.get(module.getId()).addPermission(permissionDto);
                }
            }

            return mapModuleDto.values().stream().toList();
        } catch (Exception e) {
            // NEED SAVE TO LOG
            e.printStackTrace();
            return null;
        }
    }

    private List<ModuleDto> listModelModulesToDto(List<Module> modules) {
        return this.modelMapper.map(modules, new TypeToken<List<ModuleDto>>() {
        }.getType());
    }
}
