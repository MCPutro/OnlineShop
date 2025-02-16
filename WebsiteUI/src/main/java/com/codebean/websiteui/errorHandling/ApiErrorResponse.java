package com.codebean.websiteui.errorHandling;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class ApiErrorResponse {
    // Getter dan Setter
    private String path;
    private String method;
    private String path2;
    private boolean success;
    @JsonProperty("error_detail")
    private String errorDetail;
    @JsonProperty("error_code")
    private String errorCode;
    private int status;
    private String timestamp;

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(String path, String method, String path2, boolean success, String errorDetail, String errorCode, int status, String timestamp) {
        this.path = path;
        this.method = method;
        this.path2 = path2;
        this.success = success;
        this.errorDetail = errorDetail;
        this.errorCode = errorCode;
        this.status = status;
        this.timestamp = timestamp;
    }

}

