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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisterDTO {

    @NotEmpty(message = "username cannot be empty")
    @NotBlank(message = "username cannot be blank")
    @NotNull(message = "username cannot be null")
    @Size(min = 8, max = 50, message = "min 8 dan max 50")
    private String username;

    @NotEmpty(message = "password cannot be empty")
    @NotBlank(message = "password cannot be blank")
    @NotNull(message = "password cannot be null")
    @Size(min = 8, max = 50, message = "min 8 dan max 50")
    private String password;

    @NotEmpty(message = "email cannot be empty")
    @NotBlank(message = "email cannot be blank")
    @NotNull(message = "email cannot be null")
    @Size(min = 8, max = 50, message = "min 8 dan max 50")
    private String email;

//    @NotEmpty(message = "phone number cannot be empty.")
//    @NotBlank(message = "phone number cannot be blank.")
//    @NotNull(message = "phone number cannot be null")
//    @JsonProperty("phone_number")
//    private String phoneNumber;
}
