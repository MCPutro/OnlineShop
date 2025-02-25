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
public class RoleDetailDto {
    private Long id;
    private String name;
    private Boolean isActive;
    private Set<PermissionDto> permissions;

    public RoleDetailDto() {
    }

    public RoleDetailDto(Long id, String name, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.permissions = new HashSet<>();
    }

    public void addPermission(PermissionDto permission) {
        this.permissions.add(permission);
    }
}
