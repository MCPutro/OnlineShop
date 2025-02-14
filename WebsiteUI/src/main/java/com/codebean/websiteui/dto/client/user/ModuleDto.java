package com.codebean.websiteui.dto.client.user;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 14 Feb 2025 10:01
@Last Modified 14 Feb 2025 10:01
Version 1.0
*/

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDto {
    private Long id;
    private String name;
    private String description;
    private String path;
//    private Boolean isActive;
    private List<PermissionDto> permissions;
}
