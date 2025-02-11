package com.codebean.ProductService.dto.request;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 11 Feb 2025 23:00
@Last Modified 11 Feb 2025 23:00
Version 1.0
*/

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
public class ProductUpdate {

    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String name;

    @NotNull(message = "cannot be null")
    private Double price;

    @NotNull(message = "cannot be null")
    @Min(value = 1, message = "minimum stock is 1")
    private Integer stock;

    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String description;

    @NotNull(message = "cannot be null")
    private Boolean isActive;

    @NotEmpty(message = "cannot be empty")
    @NotNull(message = "cannot be null")
    @Size(min = 1, message = "The list must have at least one category.")
    private Set<Long> categoryIds;
}
