package com.codebean.ProductService.dto.request;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Feb 2025 14:20
@Last Modified 13 Feb 2025 14:20
Version 1.0
*/

import lombok.Data;

@Data
public class ProductStock {
    private Long productId;
    private int quantity;
}
