package com.codebean.ProductService.dto.request;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Feb 2025 15:04
@Last Modified 10 Feb 2025 15:04
Version 1.0
*/

import com.codebean.ProductService.dto.CategoryDto;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String sku;
    private String name;
    private Double price;
    private Integer stock;
    private String description;
    private Boolean isActive;
    private List<CategoryDto> categories;
}
