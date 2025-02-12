package com.codebean.transactionservice.dto.client;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 12 Feb 2025 10:48
@Last Modified 12 Feb 2025 10:48
Version 1.0
*/

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseClient<T> {
    private Boolean success;
    private String message;
    private Integer status;
    private LocalDateTime timestamp;
    private String path;
    private String error_code;
    private String error_detail;
    private T data;
}
