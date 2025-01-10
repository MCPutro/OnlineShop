package com.codebean.UserService.dto.request;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 13:21
@Last Modified 10 Jan 2025 13:21
Version 1.0
*/

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisterDTO {

    private String username;

    private String password;

    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;
}
