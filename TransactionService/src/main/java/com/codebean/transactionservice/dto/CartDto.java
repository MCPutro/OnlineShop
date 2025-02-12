package com.codebean.transactionservice.dto;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 11 Feb 2025 01:33
@Last Modified 11 Feb 2025 01:33
Version 1.0
*/

import lombok.Data;

@Data
public class CartDto {
    private Long id;
    private Long userId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
}
