package com.codebean.UserService.service;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 29 Jan 2025 15:52
@Last Modified 29 Jan 2025 15:52
Version 1.0
*/

/**
 * Platform Code : ROL
 * Modul Code : 03
 * FV = Failed Validation
 * FE = Failed Error
 * ex = FVROL03001 -> [FV] [ROL] [03] 001 -> [JENIS ERROR] [Platform Code] [MODUL CODE] [seq]
 */

import com.codebean.UserService.core.iService;
import com.codebean.UserService.dto.PermissionDto;
import com.codebean.UserService.dto.RoleDto;
import com.codebean.UserService.handler.Response;
import com.codebean.UserService.model.Permission;
import com.codebean.UserService.model.Role;
import com.codebean.UserService.model.RolePermission;
import com.codebean.UserService.repository.PermissionRepository;
import com.codebean.UserService.repository.RolePermissionRepository;
import com.codebean.UserService.repository.RoleRepository;
import com.codebean.UserService.utils.Constants;
import com.codebean.UserService.utils.TransformPagination;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService implements iService<Role> {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private TransformPagination transformPagination;

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public ResponseEntity<Object> save(Role role, HttpServletRequest request) {
        try {
            if (role == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVROL03001", request);
            }

            // validate role name
            Optional<Role> firstByName = this.roleRepository.findFirstByName(role.getName());
            if (firstByName.isPresent()) {
                return Response.badRequest(Constants.ROLE_ALREADY_EXISTS, "FVROL03002", request);
            }

            // role permission
            List<RolePermission> rolePermissions = new ArrayList<>();

            // find permission by id from param
            List<Permission> listPermissionById = this.permissionRepository.findAllById(role.getPermissionIds());
            for (Permission permission : listPermissionById) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRole(role);
                rolePermission.setPermission(permission);

                rolePermissions.add(rolePermission);
            }

            //save role
            this.roleRepository.save(role);

            //save role permission
            this.rolePermissionRepository.saveAll(rolePermissions);

            return Response.success(Constants.ROLE_ADDED_SUCCESSFULLY, null, request);
        } catch (Exception e) {
            // NEED SAVE TO LOG
            return Response.internalServerError(Constants.ROLE_FAILED_TO_ADD, "FEROL03001", request);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> update(Long id, Role role, HttpServletRequest request) {
        try {
            if (id == null || role == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVROL03011", request);
            }

            //check role name
            Optional<Role> optionalRoleByName = this.roleRepository.findFirstByName(role.getName());
            if (optionalRoleByName.isPresent()) {
                return Response.badRequest(Constants.ROLE_ALREADY_EXISTS, "FVROL03012", request);
            }

            //find by id
            Optional<Role> optionalRoleById = this.roleRepository.findById(id);
            if (!optionalRoleById.isPresent()) {
                return Response.badRequest(Constants.ROLE_NOT_FOUND, "FVROL03013", request);
            }

            Role roleDB = optionalRoleById.get();

            // set active permission
            List<RolePermission> rolePermissions = new ArrayList<>();
            List<Permission> listPermissionById = this.permissionRepository.findAllById(role.getPermissionIds());
            for (Permission permission : listPermissionById) {
                RolePermission existingRolePermission = this.rolePermissionRepository.findByRoleAndPermission(roleDB, permission).orElse(null);
                if (existingRolePermission == null) {
                    // create new permission if it doesn't exist
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setRole(roleDB);
                    rolePermission.setPermission(permission);
                    rolePermissions.add(rolePermission);
                } else {
                    // If the permission exists but is not active, reactivate it
                    existingRolePermission.setIsActive(true);
                    rolePermissions.add(existingRolePermission);
                }
            }

            // Save batch active permission to database
//            this.rolePermissionRepository.saveAll(rolePermissions);

            //disable unused permission
//            rolePermissions.clear();
            List<RolePermission> disablePermission = this.rolePermissionRepository.findByRoleAndPermission_IDNotIn(roleDB, role.getPermissionIds());
            for (RolePermission rolePermission : disablePermission) {
                if (rolePermission.getIsActive()) {
                    rolePermission.setIsActive(false);
                    rolePermissions.add(rolePermission);
                }
            }

            // Save batch permission to database
            this.rolePermissionRepository.saveAll(rolePermissions);

            // update role
            optionalRoleById.ifPresent(rl -> {
                rl.setName(role.getName());
            });

            return Response.success(Constants.ROLE_UPDATED_SUCCESSFULLY, null, request);
        } catch (Exception e) {
            // NEED SAVE TO LOG
            e.printStackTrace();
            return Response.internalServerError(Constants.ROLE_FAILED_TO_ADD, "FEROL03013", request);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try {
            if (id == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVROL03021", request);
            }

            Optional<Role> optionalRole = this.roleRepository.findById(id);
            if (!optionalRole.isPresent()) {
                return Response.badRequest(Constants.ROLE_NOT_FOUND, "FVROL03022", request);
            }

            optionalRole.ifPresent(role -> {
                role.setIsActive(false);
            });

            return Response.success(Constants.ROLE_DELETED_SUCCESSFULLY, null, request);
        } catch (Exception e) {
            // NEED SAVE TO LOG
            e.printStackTrace();
            return Response.internalServerError(Constants.ROLE_FAILED_TO_DELETE, "FEROL03021", request);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        try {
            Page<Role> pageRoles = this.roleRepository.findAll(pageable);

            List<Role> listRole = pageRoles.getContent();

            List<RoleDto> listRoleDto = this.listRoleModelToDto(listRole);

            return Response.success(Constants.SUCCESS, this.transformPagination.transformPagination(listRoleDto, pageRoles, "id", ""), request);
        } catch (Exception e) {
            // NEED SAVE TO LOG
            e.printStackTrace();
            return Response.internalServerError(Constants.ROLE_FAILED_TO_DELETE, "FEROL03031", request);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        try {
            if (id == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVROL03041", request);
            }

            Optional<Role> optionalRole = this.roleRepository.findById(id);
            if (!optionalRole.isPresent()) {
                return Response.badRequest(Constants.ROLE_NOT_FOUND, "FVROL03042", request);
            }

            Role role = optionalRole.get();

            //find active permission by role
            List<RolePermission> listActivePermissionByRole = this.rolePermissionRepository.findByRoleAndIsActive(role, true);

            // get permission from listActivePermissionByRole
            Set<Permission> setPermission = listActivePermissionByRole.stream().map(RolePermission::getPermission).collect(Collectors.toSet());

            RoleDto roleDto = this.modelMapper.map(role, RoleDto.class);
            roleDto.setPermissions(this.setPermissionModelToDto(setPermission));

            return Response.success(Constants.SUCCESS, roleDto, request);
        } catch (Exception e) {
            // NEED SAVE TO LOG
            e.printStackTrace();
            return Response.internalServerError(Constants.ROLE_FAILED_TO_DELETE, "FEROL03041", request);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        try {
            Page<Role> page;
            switch (columnName.toLowerCase()) {
                case "status":
                    if (value.equalsIgnoreCase("active")) {
                        page = this.roleRepository.findAllByIsActive(true, pageable);
                    } else if (value.equalsIgnoreCase("inactive")) {
                        page = this.roleRepository.findAllByIsActive(false, pageable);
                    } else {
                        return Response.badRequest(Constants.INPUT_ACTIVE_OR_INACTIVE, "FVROL03052", request);
                    }
                    break;
                case "name":
                    page = this.roleRepository.findAllByNameContainingIgnoreCase(value, pageable);
                    break;
                default:
                    page = this.roleRepository.findAll(pageable);
                    break;
            }

            List<Role> listRole = page.getContent();
            if (listRole.isEmpty()) {
                return Response.badRequest(Constants.ROLE_NOT_FOUND, "FVROL03053", request);
            }

            List<RoleDto> listRoleDto = this.listRoleModelToDto(listRole);

            return Response.success(Constants.SUCCESS, this.transformPagination.transformPagination(listRoleDto, page, columnName, value), request);
        } catch (Exception e) {
            // NEED SAVE TO LOG
            e.printStackTrace();
            return Response.internalServerError(Constants.ROLE_FAILED_TO_DELETE, "FEROL03051", request);
        }
    }

    private Set<PermissionDto> setPermissionModelToDto(Set<Permission> permissionSet) {
        return this.modelMapper.map(permissionSet, new TypeToken<Set<PermissionDto>>() {
        }.getType());
    }

    private List<RoleDto> listRoleModelToDto(List<Role> listRole) {
        List<RoleDto> listRoleDto = new ArrayList<>();
        for (Role role : listRole) {
            RoleDto roleDto = this.modelMapper.map(role, RoleDto.class);// role
            Set<Permission> collect = role.getPermissions().stream()
                    .filter(rolePermission -> rolePermission.getIsActive().equals(true))
                    .map(RolePermission::getPermission).collect(Collectors.toSet());
            roleDto.setPermissions(this.setPermissionModelToDto(collect));

            listRoleDto.add(roleDto);
        }

        return listRoleDto;
    }
}
