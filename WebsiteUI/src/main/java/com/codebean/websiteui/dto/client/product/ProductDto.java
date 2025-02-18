package com.codebean.websiteui.dto.client.product;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String sku;
    private String name;
    private String description;
    private Integer stock;
    private Double price;
    private Boolean isActive;
    private List<CategoryDto> categories;
}
