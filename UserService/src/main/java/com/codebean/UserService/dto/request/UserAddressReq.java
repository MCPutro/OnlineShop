package com.codebean.UserService.dto.request;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 16 Jan 2025 19:28
@Last Modified 16 Jan 2025 19:28
Version 1.0
*/

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAddressReq {

    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    @JsonProperty("address_name")
    private String name;

    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String address;

    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String province;

    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String regency;

    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String postalCode;
}
