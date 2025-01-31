package com.codebean.websiteui.dto.request;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 29 Jan 2025 20:58
@Last Modified 29 Jan 2025 20:58
Version 1.0
*/

import com.codebean.websiteui.dto.Permission;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ToString
@Setter
@Getter
@NoArgsConstructor
public class UserCreateReqDto {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String role;
    private Set<Long> permissionId;
    private Set<Permission> permissions;

    public UserCreateReqDto(String username, String email, String password, String confirmPassword, String role, Set<Permission> permissions) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role = role;
        this.permissions = permissions;
    }
}
