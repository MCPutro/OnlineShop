package com.codebean.transactionservice.dto.request;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Feb 2025 14:33
@Last Modified 13 Feb 2025 14:33
Version 1.0
*/

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class OrderAdd {

    @NotNull(message = "cannot be null")
    @Size(min = 1, message = "must have at least one selected item in cart.")
    private List<Long> cartIds;

    @NotNull(message = "cannot be null")
    private Long addressId;
}
