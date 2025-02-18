package com.codebean.websiteui.dto.client.product;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long id;
    private Long userId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
    private Boolean isAvailable;
}