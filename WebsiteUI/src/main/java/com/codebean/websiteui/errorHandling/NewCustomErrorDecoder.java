package com.codebean.websiteui.errorHandling;

import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;


public class NewCustomErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        String responseBody = "";

        try {
            // Cek apakah body response tersedia
            if (response.body() != null) {
                InputStream bodyIs = response.body().asInputStream();
                responseBody = new BufferedReader(new InputStreamReader(bodyIs))
                        .lines().collect(Collectors.joining("\n"));

//                System.out.println("Error Response Body: " + responseBody); // DEBUG LOG

                // Parsing JSON ke ApiErrorResponse
                apiErrorResponse = objectMapper.readValue(responseBody, ApiErrorResponse.class);
            }
        } catch (IOException e) {
            return new RuntimeException("Failed to parse error response: " + responseBody);
        }

        // Buat pesan error yang lebih informatif
//        String errorMessage = String.format("Error [%s]: %s (Code: %s) at %s %s",
//                apiErrorResponse.getStatus(),
//                apiErrorResponse.getErrorDetail(),
//                apiErrorResponse.getErrorCode(),
//                apiErrorResponse.getMethod(),
//                apiErrorResponse.getPath()
//        );

        String errorMessage = String.format("%s (Code: %s)",
//                apiErrorResponse.getStatus(),
                apiErrorResponse.getErrorDetail(),
                apiErrorResponse.getErrorCode()
//                apiErrorResponse.getMethod(),
//                apiErrorResponse.getPath()
        );

        // Tentukan exception berdasarkan status kode
        if (response.status() == HttpStatus.FORBIDDEN.value() || response.status() == HttpStatus.UNAUTHORIZED.value()) {
            return new ForbiddenException(errorMessage);
        } else if (response.status() == HttpStatus.NOT_FOUND.value()) {
            return new NotFoundException(errorMessage);
        } else if (response.status() == HttpStatus.BAD_REQUEST.value()) {
            return new BadRequestException(errorMessage);
        } else {
            return new RuntimeException(errorMessage);
        }
//        return new RuntimeException(errorMessage);
    }
}
