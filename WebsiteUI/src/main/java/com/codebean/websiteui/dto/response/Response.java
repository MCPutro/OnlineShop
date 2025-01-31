package com.codebean.websiteui.dto.response;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 29 Jan 2025 13:30
@Last Modified 29 Jan 2025 13:30
Version 1.0
*/

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private Boolean success;
    private String message;
    private Integer status;
    private LocalDateTime timestamp;
    private String path;
    private String error_code;
    private String error_detail;
    private T data;


}
