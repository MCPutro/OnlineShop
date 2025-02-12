package com.codebean.transactionservice.dto.request;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 12 Feb 2025 13:31
@Last Modified 12 Feb 2025 13:31
Version 1.0
*/

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartAddDto {

    @NotNull(message = "gak boleh null")
    private Long productId;

    @NotNull(message = "gak boleh null")
    @Min(value = 1, message = "minimum stock is 1")
    private Integer quantity;
}
