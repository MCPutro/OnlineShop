//package com.codebean.websiteui.controller;
//
///*
//IntelliJ IDEA 2024.2.4 (Community Edition)
//Build #IC-242.23726.103, built on October 23, 2024
//@Author mcputro a.k.a. Mu'ti Cahyono Putro
//Created on 27 Jan 2025 12:17
//@Last Modified 27 Jan 2025 12:17
//Version 1.0
//*/
//
//import com.codebean.websiteui.client.UserClient;
//import com.codebean.websiteui.dto.request.UserRegReqDto;
//import com.codebean.websiteui.util.Constans;
//import feign.FeignException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class AuthController {
//
//    @Autowired
//    private UserClient userClient;
//
//    @GetMapping("/customer-register")
//    public String customerRegister(Model model) {
//        model.addAttribute("newCustomer", new UserRegReqDto());
//        System.out.println("ini get");
//        return Constans.CUSTOMER_REGISTER;
//    }
//
//    @PostMapping("/customer-register")
//    public String customerRegister(@ModelAttribute UserRegReqDto dto, BindingResult result, Model model) {
//        System.out.println("ini post");
//        try {
//            System.out.println(">>>>>" + dto);
//            this.userClient.registerCustomer(dto);
//            return "redirect:/customer-register";
//        } catch (FeignException e) {
//            System.out.println("2 >>>>>" + e.contentUTF8()); // butuh mapping
//            model.addAttribute("errorMessage", e.getMessage());
//            model.addAttribute("newCustomer", new UserRegReqDto());
//            return Constans.CUSTOMER_REGISTER;
//        } catch (Exception e) {
//            System.out.println("1 >>>>>" + e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }
//}
