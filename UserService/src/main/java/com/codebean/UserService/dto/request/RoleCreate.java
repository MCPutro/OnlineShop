package com.codebean.UserService.dto.request;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 07 Feb 2025 23:51
@Last Modified 07 Feb 2025 23:51
Version 1.0
*/

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class RoleCreate {

    //    ^[a-zA-Z0-9]+$
    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    @JsonProperty("roleName")
    private String name;

    @Valid
    @NotNull(message = "cannot be null")
    private Set<Long> permissionIds;
}

