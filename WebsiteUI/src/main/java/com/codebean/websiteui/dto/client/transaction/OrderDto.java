package com.codebean.websiteui.dto.client.transaction;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long userID;
    private String name;
    private Long addressID;
    private double totalPrice;
    private LocalDate orderDate;
    private String orderStatus;
    private List<OrderItemDto> orderItems;
}
