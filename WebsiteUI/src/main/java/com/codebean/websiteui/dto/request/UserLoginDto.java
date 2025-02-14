package com.codebean.websiteui.dto.request;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 14 Feb 2025 13:14
@Last Modified 14 Feb 2025 13:14
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
public class UserLoginDto {

    @NotEmpty(message = "cannot be empty ")
    @NotNull(message = "cannot be null ")
    @NotBlank(message = "cannot be blank ")
    private String username;

    @NotEmpty(message = "cannot be empty ")
    @NotNull(message = "cannot be null ")
    @NotBlank(message = "cannot be blank ")
    private String password;
}
