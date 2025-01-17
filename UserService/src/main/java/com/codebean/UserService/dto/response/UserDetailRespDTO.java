package com.codebean.UserService.dto.response;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Jan 2025 10:49
@Last Modified 13 Jan 2025 10:49
Version 1.0
*/

import com.codebean.UserService.dto.request.UserProfileDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailRespDTO {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private String role;
    private UserProfileDto profile;
}
