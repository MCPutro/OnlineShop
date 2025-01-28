package com.codebean.UserService.dto;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Jan 2025 10:49
@Last Modified 13 Jan 2025 10:49
Version 1.0
*/

import com.codebean.UserService.dto.request.UserAddressReqDto;
import com.codebean.UserService.dto.response.UserAddressRespDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDto {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private String role;
    private Boolean isActive;
    private UserProfileDto profile;
    private List<UserAddressRespDto> addresses;
    private Set<PermissionDto> permissions;
}
