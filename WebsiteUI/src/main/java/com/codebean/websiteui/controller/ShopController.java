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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("shop")
public class ShopController {

    @Autowired
    private ProductClient productClient;

    @GetMapping
    public String shop(Model model, WebRequest webRequest) {
        GlobalFunction.setGlobalFragment(model, webRequest);

//        try {
//            // get category list
//            Map<String, Object> activeCategory = this.productClient.getActiveCategory(0, 100);
//            Map<String, Object> mapData = (Map<String, Object>) activeCategory.get("data");
//            List<Map<String, Object>> ltCategory = (List<Map<String, Object>>) mapData.get("content");
//
//            model.addAttribute("ltCategory", ltCategory);
//
//            Map<String, Object> activeProducts = this.productClient.getActiveProducts(0, 15);
//            Map<String, Object> dataProducts = (Map<String, Object>) activeProducts.get("data");
//            List<Map<String, Object>> products = (List<Map<String, Object>>) dataProducts.get("content");
//
//            model.addAttribute("ltProducts", products);
//        } catch (Exception e) {
//
//            throw new RuntimeException(e);
//        }

        return "shop";
    }

    @GetMapping("/search")
    public String shop(@RequestParam(value = "productName", required = false) String productName,
                       @RequestParam(value = "category", required = false) List<Integer> categoryIds,
                       @RequestParam(value = "minPrice", required = false) Integer minPrice,
                       @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
                       Model model) {
        System.out.println(productName);
        System.out.println(categoryIds);
        System.out.println(minPrice);
        System.out.println(maxPrice);

        model.addAttribute("productName", productName);
        return "shop";
    }
}
