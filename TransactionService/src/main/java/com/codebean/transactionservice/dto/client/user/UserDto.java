package com.codebean.transactionservice.dto.client.user;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 12 Feb 2025 13:08
@Last Modified 12 Feb 2025 13:08
Version 1.0
*/

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String role;
    private Boolean isActive;
    private Boolean isDelete;
}
