package com.codebean.websiteui.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 14 Feb 2025 11:42
@Last Modified 14 Feb 2025 11:42
Version 1.0
*/

import com.codebean.websiteui.client.ProductClient;
import com.codebean.websiteui.dto.response.Response;
import com.codebean.websiteui.util.GlobalFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

@Controller
public class ShopController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/shop")
    public String shop(Model model, WebRequest webRequest) {
        GlobalFunction.setGlobalFragment(model, webRequest);

        try {
            // get category list
            Map<String, Object> activeCategory = this.productClient.getActiveCategory(0, 100);
            Map<String, Object> mapData = (Map<String, Object>) activeCategory.get("data");
            List<Map<String, Object>> ltCategory = (List<Map<String, Object>>) mapData.get("content");

            model.addAttribute("ltCategory", ltCategory);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "shop";
    }
}
