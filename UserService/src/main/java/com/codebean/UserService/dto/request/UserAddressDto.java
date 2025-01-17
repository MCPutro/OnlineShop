package com.codebean.UserService.dto.request;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 16 Jan 2025 19:22
@Last Modified 16 Jan 2025 19:22
Version 1.0
*/

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAddressDto {
    @JsonProperty("userId")
    private Long id;
    private List<Address> addresses;
}



