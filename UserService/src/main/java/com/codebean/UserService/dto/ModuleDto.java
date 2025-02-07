package com.codebean.UserService.dto;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 07 Feb 2025 23:29
@Last Modified 07 Feb 2025 23:29
Version 1.0
*/

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ModuleDto {
    private Long ID;
    private String name;
    private String path;
    private String description;
    private Boolean isActive;
    private Set<PermissionDto> permissions;

    public ModuleDto() {
    }

    public ModuleDto(Long ID, String name, String path, String description, Boolean isActive) {
        this.ID = ID;
        this.name = name;
        this.path = path;
        this.description = description;
        this.isActive = isActive;
        this.permissions = new HashSet<>();
    }

    public void addPermission(PermissionDto permission) {
        this.permissions.add(permission);
    }
}