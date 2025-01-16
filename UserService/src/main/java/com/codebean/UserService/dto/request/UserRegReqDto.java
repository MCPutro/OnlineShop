package com.codebean.UserService.dto.request;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 13:21
@Last Modified 10 Jan 2025 13:21
Version 1.0
*/

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegReqDto {

    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
//    @Size(min = 8, max = 20, message = "must contain 8-20 character")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9._]{7,19}$", message = "harus di awali dengan huruf, terdiri dari 8-20 karakter, simbol yang diperboleh kan hanya . dan _ ")
    private String username;

    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    @Size(min = 8, max = 50, message = "must contain 8-50 character")
    private String password;

    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "Format not valid.")
    private String email;

}
