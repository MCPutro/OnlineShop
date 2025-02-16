package com.codebean.websiteui.client;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 27 Jan 2025 11:51
@Last Modified 27 Jan 2025 11:51
Version 1.0
*/

import com.codebean.websiteui.errorHandling.FeignClientConfig;
import com.codebean.websiteui.dto.client.user.UserCreateDto;
import com.codebean.websiteui.dto.client.user.UserDetailDto;
import com.codebean.websiteui.dto.client.user.UserDto;
import com.codebean.websiteui.dto.client.user.ModuleDto;
import com.codebean.websiteui.dto.request.UserLoginDto;
import com.codebean.websiteui.dto.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "UserService", url = "http://localhost:8081", configuration = FeignClientConfig.class)
public interface UserClient {

//    @PostMapping("/auth/v1/customer")
//    ResponseEntity<Map<String, Object>> registerCustomer(@RequestBody UserRegReqDto dto);
//
//    @GetMapping("/api/v1/users")
//    Response<List<UserDto>> getAllUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);
//
//    @GetMapping("/api/v1/roles")
//    Response<List<RolePermissionsDto>> getRoleList(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);
//
//    @GetMapping("/api/v1/permissions")
//    Response<List<Permission>> getPermissionsList(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);
//
//    @PostMapping("/api/v1/user-create")
//    Response<Map<String, Object>> createNewUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody UserCreateReqDto body);
//
//    @GetMapping("/api/v1/user/{userId}")
//    Response<UserDetailDto> getUserDetailById(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable(value = "userId") Long userId);

    // new
    @GetMapping(path = "/api/v1/module/roleName/{roleName}")
    Response<List<ModuleDto>> getModuleByRoleName(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable(value = "roleName") String roleName);

    @PostMapping("/auth/v1/login")
    Response<UserDto> login(@RequestBody UserLoginDto dto);

    @GetMapping("/api/v1/users")
    Map<String, Object> findAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                @RequestParam(value = "page") Integer page,
                                @RequestParam(value = "sizePerPage") Integer sizePerPage
    );

    @GetMapping("/api/v1/find/user")
    Map<String, Object> findAllBy(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                  @RequestParam(value = "by") String by,
                                  @RequestParam(value = "search") String search,
                                  @RequestParam(value = "page") Integer page,
                                  @RequestParam(value = "sizePerPage") Integer sizePerPage
    );

    @GetMapping("/api/v1/roles/active")
    Map<String, Object> findActiveRole(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                       @RequestParam(value = "page") Integer page,
                                       @RequestParam(value = "sizePerPage") Integer sizePerPage
    );

    @PostMapping("/api/v1/user-create")
    Response<String> createUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody UserCreateDto dto);

    @GetMapping("/api/v1/user/{userId}")
    Response<UserDetailDto> findById(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long userId);

    @DeleteMapping("/api/v1/user/{userId}")
    Response<Object> deleteById(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long userId);
}
