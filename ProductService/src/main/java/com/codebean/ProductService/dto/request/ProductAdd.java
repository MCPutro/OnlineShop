package com.codebean.ProductService.dto.request;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Feb 2025 13:49
@Last Modified 10 Feb 2025 13:49
Version 1.0
*/


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class ProductAdd {

    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String sku;

    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String name;

    @NotNull(message = "cannot be null")
    private Double price;

    @NotNull(message = "cannot be null")
    private Integer stock;

    @NotNull(message = "cannot be null")
    private Boolean isActive;

    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String description;

    @NotEmpty(message = "cannot be empty")
    @NotNull(message = "cannot be null")
    @Size(min = 1, message = "The list must have at least one category.")
    private Set<Long> categoryIds;
}
