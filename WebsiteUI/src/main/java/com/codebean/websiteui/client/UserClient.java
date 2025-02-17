package com.codebean.websiteui.client;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 27 Jan 2025 11:51
@Last Modified 27 Jan 2025 11:51
Version 1.0
*/

import com.codebean.websiteui.dto.client.user.*;
import com.codebean.websiteui.dto.pageAttribute;
import com.codebean.websiteui.errorHandling.FeignClientConfig;
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

    @GetMapping("/api/v1/user")
    Response<UserDetailDto> findByToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);

    @DeleteMapping("/api/v1/user/{userId}")
    Response<Object> deleteById(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long userId);

    @PutMapping("/api/v1/user/{userId}")
    Response<String> updateUserProfileById(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long userId, @RequestBody UserUpdateProfileDto dto);

    @GetMapping("/api/v1/roles")
    Response<pageAttribute<RoleDto>> findAllRole(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                 @RequestParam(value = "page") Integer page,
                                                 @RequestParam(value = "sizePerPage") Integer sizePerPage
    );

    @GetMapping("/api/v1/role/search/{roleName}")
    Response<pageAttribute<RoleDto>> findRoleByName(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                    @RequestParam(value = "page") Integer page,
                                                    @RequestParam(value = "sizePerPage") Integer sizePerPage,
                                                    @PathVariable String roleName);

    @GetMapping("/api/v1/module/active")
    Response<List<ModuleDto>> findAllActiveModuleAndPermission(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);


    @GetMapping("/api/v1/modules")
    Response<List<ModuleDto>> findAllModuleAndPermission(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);

    @PostMapping("/api/v1/role-create")
    Response<String> createRole(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody RoleCreateDto dto);

    @GetMapping("/api/v1/role/{roleId}")
    Response<RoleDto> findRoleById(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long roleId);

    @PutMapping("/api/v1/role/{roleId}")
    Response<String> updateRoleById(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long roleId, @RequestBody RoleCreateDto dto);

    @DeleteMapping("/api/v1/role/{roleId}")
    Response<String> deleteRoleById(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String roleId);

    @PutMapping("/api/v1/role/reactivation/{roleId}")
    Response<String> reactivationRoleById(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String roleId);
}
