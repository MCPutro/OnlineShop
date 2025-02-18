package com.codebean.websiteui.dto.client.transaction;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Double subTotalPrice;
    private Long quantity;
    private Double productPrice;
    private Long productId;
}
