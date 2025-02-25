package com.codebean.UserService.dto;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Jan 2025 10:49
@Last Modified 13 Jan 2025 10:49
Version 1.0
*/

import lombok.*;

import java.time.LocalDate;
import java.util.List;

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

    private Boolean isActive;

    private Boolean isDelete;

    private String role;

    private String name;

    private LocalDate dateOfBirth;

    private String gender;

    private String profilePicture;

    private List<UserAddressDto> addresses;

}
