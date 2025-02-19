package com.codebean.websiteui.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 14 Feb 2025 18:41
@Last Modified 14 Feb 2025 18:41
Version 1.0
*/

import com.codebean.websiteui.client.ProductClient;
import com.codebean.websiteui.dto.client.product.CategoryDto;
import com.codebean.websiteui.dto.client.product.CategoryReqDto;
import com.codebean.websiteui.dto.pageAttribute;
import com.codebean.websiteui.dto.response.Response;
import com.codebean.websiteui.util.Constans;
import com.codebean.websiteui.util.GlobalFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("product-management")
public class ProductManagement {

    @Autowired
    private ProductClient productClient;

    private String token = "Bearer xxx";

//    @GetMapping("/product-management")
//    public String productManagement(Model model, WebRequest webRequest) {
//        GlobalFunction.setGlobalFragment(model, webRequest);
//        model.addAttribute(Constans.IS_MANAGEMENT, "Product & Category Management");
//        return "productManagement/main";
//    }

    // ini untuk tampilin ui add category
    @GetMapping
    public String productManagement(Model model, WebRequest webRequest) {
//        model.addAttribute("newCategory", new CategoryReqDto());
//        return "productManagement/Category/add";
        GlobalFunction.setGlobalFragment(model, webRequest);
        model.addAttribute(Constans.IS_MANAGEMENT, "Product Management");
        return "commingSoon";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("newCategory") CategoryReqDto newCategory,
                       BindingResult bindingResult,
                       Model model,
                       WebRequest webRequest) {
        try{
            System.out.println(newCategory);
            this.productClient.createCategory(token, newCategory);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return "redirect:/product-management";
    }

    @GetMapping("/all")
    public String all(Model model) {
        try {
            Map<String, Object> activeCategory = this.productClient.getActiveCategory(0, 100);
            Map<String, Object> data = (Map<String, Object>) activeCategory.get("data");
            List<Map<String, Object>> listContent = (List<Map<String, Object>>) data.get("content");

//            for (Map<String, Object> map : listContent) {
//                System.out.println(map);
//            }

            model.addAttribute("listContent", listContent);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
            return "commingSoon";
        }
//        return "productManagement/Category/view";
        return "commingSoon";
    }
}
