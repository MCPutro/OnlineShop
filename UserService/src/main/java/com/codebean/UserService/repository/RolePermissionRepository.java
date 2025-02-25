package com.codebean.UserService.repository;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 24 Jan 2025 19:41
@Last Modified 24 Jan 2025 19:41
Version 1.0
*/

import com.codebean.UserService.model.Permission;
import com.codebean.UserService.model.Role;
import com.codebean.UserService.model.RolePermission;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {

    Optional<RolePermission> findByRoleAndPermission(Role role, Permission permission);

    List<RolePermission> findByRoleAndPermission_IdNotIn(Role role, Collection<Long> permissionIDS);

    @EntityGraph(attributePaths = {"permission", "permission.module"})
    List<RolePermission> findByRoleAndIsActive(Role role, Boolean isActive);

    List<RolePermission> findByRoleAndIsActiveAndPermission_Module_IsActive(Role role, Boolean isActive, Boolean permissionModuleIsActive);
}