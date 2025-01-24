package com.codebean.UserService.dto.response;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 24 Jan 2025 00:54
@Last Modified 24 Jan 2025 00:54
Version 1.0
*/

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegRespDto {
    private Long id;
    private String username;
    private String email;
    private Boolean isActive;
    private String role;
}
