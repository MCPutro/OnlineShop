package com.codebean.websiteui.dto;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 29 Jan 2025 13:03
@Last Modified 29 Jan 2025 13:03
Version 1.0
*/

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDto {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private String name;
    private String role;
    private String profilePicture;
    private Boolean isActive;
    private Boolean isDelete;
    private String gender;
    private LocalDate dateOfBirth;
    private List<UserAddressDto> addresses;
}
