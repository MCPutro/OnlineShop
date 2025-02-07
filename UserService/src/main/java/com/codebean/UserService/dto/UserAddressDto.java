package com.codebean.UserService.dto;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 28 Jan 2025 16:43
@Last Modified 28 Jan 2025 16:43
Version 1.0
*/

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAddressDto {

    private Long ID;

    private String name;

    private String address;

    private String country;

    private String postalCode;

    private Boolean isActive;
}
