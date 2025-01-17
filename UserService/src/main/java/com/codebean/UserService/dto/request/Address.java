package com.codebean.UserService.dto.request;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 16 Jan 2025 19:28
@Last Modified 16 Jan 2025 19:28
Version 1.0
*/

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {
    private String address;
    private String country;
    private String postalCode;
}
