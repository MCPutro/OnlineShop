package com.codebean.websiteui.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 27 Jan 2025 11:50
@Last Modified 27 Jan 2025 11:50
Version 1.0
*/

import com.codebean.websiteui.client.UserClient;
import com.codebean.websiteui.dto.Permission;
import com.codebean.websiteui.dto.UserDetailDto;
import com.codebean.websiteui.dto.request.UserCreateReqDto;
import com.codebean.websiteui.dto.response.Response;
import com.codebean.websiteui.dto.RolePermissionsDto;
import com.codebean.websiteui.dto.UserDto;
import com.codebean.websiteui.util.Constans;
import com.google.gson.Gson;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private Gson gson;

    private String key = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjEyMyIsInBlcm1pc3Npb25zIjoiUFJPRklMRSBTSE9XVVNFUkRFVEFJTCBFRElUVVNFUkRFVEFJTCBQUk9EVUNUQ0FURUdPUllFRElUIFBST0RVQ1RDQVRFR09SWVZJRVcgU0hPV1VTRVJTIEFERFVTRVIiLCJ1c2VySWQiOjQxLCJpYXQiOjE3MzgyOTg4MjcsImV4cCI6MTczODMzNDgyN30.9yL7RQS5kHbS5A4wxbzo3fT6qrK8FJtDxbtjvnuUK1w";


    @GetMapping("/user-management")
    public String user(Model model) {
        try {
            String token = "Bearer " + key;
            Response<List<UserDto>> allUser = this.userClient.getAllUser(token);

            model.addAttribute("listUser", allUser.getData());

            return Constans.ADMIN_LIST_USER;

        } catch (FeignException fe) {
            System.out.println("disisni");
            System.out.println(fe);
            System.out.println(fe.contentUTF8());
            fe.printStackTrace();
            throw new RuntimeException(fe);
        } catch (Exception e) {
            System.out.println("disisni 1");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/user-view/{userId}")
    public String userView(@PathVariable(value = "userId") Long userId, Model model){
        try{
            String token = "Bearer " + key;
            Response<UserDetailDto> userDetailById = this.userClient.getUserDetailById(token, userId);

            model.addAttribute("userDetail", userDetailById.getData());

            return Constans.ADMIN_VIEW_USER;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/user-new")
    public String userNew(Model model) {
        try {
            model.addAttribute("newUser", new UserCreateReqDto());

            String token = "Bearer " + key;

            Response<List<RolePermissionsDto>> roleList = this.userClient.getRoleList(token);

            model.addAttribute("listRole", roleList.getData());

            Response<List<Permission>> permissionsList = this.userClient.getPermissionsList(token);
            model.addAttribute("permissions", permissionsList.getData());

            Map<String, List<Long>> rolePermissionMap = new HashMap<>();
            for (RolePermissionsDto rp : roleList.getData()) {
                rolePermissionMap.put(String.valueOf(rp.getName()), rp.getPermissions().stream().map(Permission::getID).collect(Collectors.toList()));
            }

            model.addAttribute("rolePermissionMap", rolePermissionMap);

            return Constans.ADMIN_ADD_USER;
        } catch (FeignException fe) {
            fe.printStackTrace();
            throw new RuntimeException(fe);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/user-new")
    public String userNew(@ModelAttribute UserCreateReqDto userCreateReqDto, Model model) {
        try {
            System.out.println("POST  1 -> " + userCreateReqDto);
            userCreateReqDto.setPermissions(userCreateReqDto.getPermissionId().stream().map(aLong -> new Permission(aLong, null, null)).collect(Collectors.toSet()));
            System.out.println("POST 2 -> " + userCreateReqDto);

            String token = "Bearer " + key;

            Response<Map<String, Object>> newUser = this.userClient.createNewUser(token, userCreateReqDto);
            System.out.println("POST 3 -> " + newUser);

            return "redirect:/admin/user-management";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/user-edit/{userId}")
    public String userEdit(@PathVariable(value = "userId") Long userId, Model model) {
        try {

            String token = "Bearer " + key;
            UserDetailDto data = this.userClient.getUserDetailById(token, userId).getData();
            Set<Long> collect = data.getPermissions().stream().map(Permission::getID).collect(Collectors.toSet());
            data.setPermissionId(collect); // menampilkan

            System.out.println(">>>" + data);
            model.addAttribute("existingUser", data);

            List<RolePermissionsDto> roleList = this.userClient.getRoleList(token).getData();

            model.addAttribute("listRole", roleList);

            Response<List<Permission>> permissionsList = this.userClient.getPermissionsList(token);
            model.addAttribute("permissions", permissionsList.getData());
            System.out.println(">>>" + permissionsList.getData());

            Map<String, List<Long>> rolePermissionMap = new HashMap<>();
            for (RolePermissionsDto rp : roleList) {
                rolePermissionMap.put(String.valueOf(rp.getName()), rp.getPermissions().stream().map(Permission::getID).collect(Collectors.toList()));
            }

            model.addAttribute("rolePermissionMap", rolePermissionMap);

            return Constans.ADMIN_EDIT_USER;
        } catch (FeignException fe) {
            fe.printStackTrace();
            throw new RuntimeException(fe);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/user-edit/{userId}")
    public String saveUserEdit(@PathVariable(value = "userId") Long userId, @ModelAttribute UserDetailDto dto, Model model) {
        System.out.println("POST  1 -> " + userId);
        System.out.println("POST  2 -> " + dto);
        return "redirect:/admin/user-management";
    }
}
