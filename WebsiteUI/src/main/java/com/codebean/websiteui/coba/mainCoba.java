package com.codebean.websiteui.coba;

import com.codebean.websiteui.errorHandling.ApiErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class mainCoba {
    public static void main(String[] args) {
        String responseBody = "{\"path\":\"/api/v1/user/13\",\"method\":\"DELETE\",\"path2\":\"\",\"success\":false,\"error_detail\":\"Account not found\",\"error_code\":\"FVUSR01021\",\"status\":400,\"timestamp\":\"2025-02-16T11:18:07.6988821\"}";
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ApiErrorResponse apiErrorResponse = objectMapper.readValue(responseBody, ApiErrorResponse.class);
            System.out.println(apiErrorResponse);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }
}
