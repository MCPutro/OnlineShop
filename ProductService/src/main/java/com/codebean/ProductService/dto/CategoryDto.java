package com.codebean.ProductService.dto;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 31 Jan 2025 10:40
@Last Modified 31 Jan 2025 10:40
Version 1.0
*/

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private Boolean isActive;
}
