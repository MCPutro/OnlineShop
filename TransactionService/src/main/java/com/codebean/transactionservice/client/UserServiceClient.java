package com.codebean.transactionservice.client;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 12 Feb 2025 13:06
@Last Modified 12 Feb 2025 13:06
Version 1.0
*/

import com.codebean.transactionservice.dto.client.ResponseClient;
import com.codebean.transactionservice.dto.client.user.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "UserService", url = "http://localhost:8081")
public interface UserServiceClient {

    @GetMapping("/api/v1/user")
    ResponseClient<UserDto> getUserByToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);
}
