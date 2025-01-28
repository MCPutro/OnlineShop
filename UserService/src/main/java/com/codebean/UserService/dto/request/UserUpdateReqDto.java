package com.codebean.UserService.dto.request;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 16 Jan 2025 11:04
@Last Modified 16 Jan 2025 11:04
Version 1.0
*/

import com.codebean.UserService.dto.UserProfileDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateReqDto {

//    @NotNull(message = "cannot be null")
//    @JsonProperty("id")
//    private Long ID;

    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
//    @Size(min = 8, max = 20, message = "must contain 8-20 character")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9._]{7,19}$", message = "harus di awali dengan huruf, terdiri dari 8-20 karakter, simbol yang diperboleh kan hanya . dan _ ")
    private String username;


    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "Format not valid.")
    private String email;

    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String phoneNumber;

    @Valid
    private UserProfileDto profile;

}
