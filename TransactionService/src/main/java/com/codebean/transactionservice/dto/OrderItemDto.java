package com.codebean.transactionservice.dto;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Feb 2025 22:50
@Last Modified 13 Feb 2025 22:50
Version 1.0
*/

import jakarta.persistence.Column;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private Long ProductId;
    private Double ProductPrice;
    private Integer Quantity;
    private Double subTotalPrice;
}
