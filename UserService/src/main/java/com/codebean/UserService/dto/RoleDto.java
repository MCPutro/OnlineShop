package com.codebean.UserService.dto;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 07 Feb 2025 23:22
@Last Modified 07 Feb 2025 23:22
Version 1.0
*/

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RoleDto {
    private Long ID;
    private String name;
    private Boolean isActive;
    private Set<PermissionDto> permissions;

    public RoleDto() {
    }

    public RoleDto(Long ID, String name, Boolean isActive) {
        this.ID = ID;
        this.name = name;
        this.isActive = isActive;
        this.permissions = new HashSet<>();
    }

    public void addPermission(PermissionDto permission) {
        this.permissions.add(permission);
    }
}
