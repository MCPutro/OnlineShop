package com.codebean.transactionservice.dto;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Feb 2025 22:44
@Last Modified 13 Feb 2025 22:44
Version 1.0
*/

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private Long userId;
    private String name;
    private Long addressId;
    private Double totalPrice;
    private LocalDate orderDate;
    private String orderStatus;
    private List<OrderItemDto> orderItems;
}
