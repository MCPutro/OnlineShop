package com.codebean.ProductService.dto.request;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SearchProductDto {
    private String productName;
    private Double minPrice;
    private Double maxPrice;
    private Set<Long> categoryId;
}
