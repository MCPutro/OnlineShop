package com.codebean.websiteui.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 14 Feb 2025 16:26
@Last Modified 14 Feb 2025 16:26
Version 1.0
*/

import com.codebean.websiteui.util.GlobalFunction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class CartController {

    @GetMapping("/cart")
    public String cart(Model model, WebRequest request) {
        GlobalFunction.setGlobalFragment(model, request);
        return "cart";
    }
}
