package com.codebean.websiteui.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 14 Feb 2025 13:06
@Last Modified 14 Feb 2025 13:06
Version 1.0
*/

import com.codebean.websiteui.client.UserClient;
import com.codebean.websiteui.dto.client.user.PermissionDto;
import com.codebean.websiteui.dto.client.user.UserDto;
import com.codebean.websiteui.dto.client.user.ModuleDto;
import com.codebean.websiteui.dto.request.UserLoginDto;
import com.codebean.websiteui.dto.request.UserRegReqDto;
import com.codebean.websiteui.dto.response.Response;
import com.codebean.websiteui.errorHandling.ForbiddenException;
import com.codebean.websiteui.util.Constans;
import com.codebean.websiteui.util.GlobalFunction;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class AuthController {

    private final List<String> perm = new ArrayList<>();
    @Autowired
    private UserClient userClient;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserLoginDto());
        model.addAttribute(Constans.IS_LOGIN_OR_REGIS_PAGE, "user");

//        GlobalFunction.setGlobalFragment(model, request);
//
//        Map<String, Object> map = model.asMap();
//        for (Map.Entry<String, Object> entry : map.entrySet()) {
//            System.out.println(entry.getKey()+ " : " + entry.getValue());
//        }

        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute("user") @Valid UserLoginDto user,
                              BindingResult bindingResult,
                              Model model,
                              RedirectAttributes redirectAttributes,
                              WebRequest webRequest
    ) {
        if (bindingResult.hasErrors()) {
            List<String> list = bindingResult.getFieldErrors().stream().map(err -> {
                return err.getField() + " " + err.getDefaultMessage();
            }).toList();
            model.addAttribute(Constans.ERRORS, list);
            return "login";
        }

        //login to userService
        UserDto userDto;
        StringBuilder modules = new StringBuilder();
        this.perm.clear();
        try {
            // login user
            Response<UserDto> login = this.userClient.login(user);
            userDto = login.getData();

            // get module
            Response<List<ModuleDto>> responseModuleByRoleName = this.userClient.getModuleByRoleName(Constans.BEARER + userDto.getToken(), userDto.getRole());


            //generate nav module
            modules.append("<li><a href=\"/\">Home</a></li>");
            for (ModuleDto moduleDto : responseModuleByRoleName.getData()) {
                modules.append("<li><a href=\"").append(moduleDto.getPath()).append("\">").append(moduleDto.getName()).append("</a></li>");
                perm.addAll(moduleDto.getPermissions().stream().map(PermissionDto::getName).toList());
            }

        } catch (Exception e) {
            model.addAttribute(Constans.ERRORS, e.getMessage());
            return "login";
        }

        // save to session
        webRequest.setAttribute(Constans.USERNAME, userDto.getUsername(), WebRequest.SCOPE_SESSION);
        webRequest.setAttribute(Constans.USER_ID, userDto.getId(), WebRequest.SCOPE_SESSION);
        webRequest.setAttribute(Constans.ROLE, userDto.getRole(), WebRequest.SCOPE_SESSION);
        webRequest.setAttribute(Constans.TOKEN, userDto.getToken(), WebRequest.SCOPE_SESSION);
        webRequest.setAttribute(Constans.MODULE, modules.toString(), WebRequest.SCOPE_SESSION);
        webRequest.setAttribute(Constans.PERMISSIONS, perm, WebRequest.SCOPE_SESSION);

        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserRegReqDto());
        model.addAttribute(Constans.IS_LOGIN_OR_REGIS_PAGE, "user");
        return "registerCustomer";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid UserRegReqDto user,
                           BindingResult bindingResult,
                           Model model,
                           RedirectAttributes redirectAttributes,
                           WebRequest webRequest
    ) {
        System.out.println(user);
        if (bindingResult.hasErrors()) {
            List<String> list = bindingResult.getFieldErrors().stream().map(err -> {
                return err.getField() + " " + err.getDefaultMessage();
            }).toList();
            model.addAttribute(Constans.ERRORS, list);
            return "registerCustomer";
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute(Constans.ERRORS, "Password and Confirm Password do not match");
            return "registerCustomer";
        }

        // call user service
        try {
            this.userClient.registerCustomer(user);
            model.addAttribute("successMessage", "Customer registered successfully, PLease login with your account.");
            return "registerCustomer";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
            return "registerCustomer";
        }


    }
}
