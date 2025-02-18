package com.codebean.transactionservice.dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartReview {
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
    private Double totalPrice;
    
}
