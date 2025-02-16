package com.codebean.websiteui.dto.client.product;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    public String name;
    public boolean isActive;
    public int id;
}
