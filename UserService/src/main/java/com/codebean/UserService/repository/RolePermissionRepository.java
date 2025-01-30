package com.codebean.UserService.repository;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 24 Jan 2025 19:41
@Last Modified 24 Jan 2025 19:41
Version 1.0
*/

import com.codebean.UserService.model.RolePermissions;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RolePermissionRepository extends CrudRepository<RolePermissions, Long> {
    List<RolePermissions> findAllByRole_NameAndIsActiveIsTrue(String roleName);

    List<RolePermissions> findAllByRole_IsActiveAndIsActive(Boolean roleIsActive, Boolean permissionIsActive);
}
