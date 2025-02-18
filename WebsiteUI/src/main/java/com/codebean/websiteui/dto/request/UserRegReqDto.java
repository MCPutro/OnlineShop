package com.codebean.websiteui.dto.request;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 27 Jan 2025 11:56
@Last Modified 27 Jan 2025 11:56
Version 1.0
*/

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRegReqDto {

    @NotEmpty(message = "cannot be empty ")
    @NotNull(message = "cannot be null ")
    @NotBlank(message = "cannot be blank ")
    private String username;

    @NotEmpty(message = "cannot be empty ")
    @NotNull(message = "cannot be null ")
    @NotBlank(message = "cannot be blank ")
    private String email;

    @NotEmpty(message = "cannot be empty ")
    @NotNull(message = "cannot be null ")
    @NotBlank(message = "cannot be blank ")
    private String name;

    @NotEmpty(message = "cannot be empty ")
    @NotNull(message = "cannot be null ")
    @NotBlank(message = "cannot be blank ")
    private String password;

    @NotEmpty(message = "cannot be empty ")
    @NotNull(message = "cannot be null ")
    @NotBlank(message = "cannot be blank ")
    private String confirmPassword;


}
