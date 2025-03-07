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
import com.codebean.websiteui.client.TransactionClient;
import com.codebean.websiteui.dto.client.product.CartAdd;
import com.codebean.websiteui.dto.client.product.ProductDto;
import com.codebean.websiteui.dto.response.Response;
import com.codebean.websiteui.util.Constans;
import com.codebean.websiteui.util.GlobalFunction;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("shop")
public class ShopController {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private TransactionClient transactionClient;

    @GetMapping
    public String shop(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "15") Integer size,
                       @RequestParam(required = false, defaultValue = "") String sortType, // asc or desc
                       @RequestParam(required = false, defaultValue = "") String sortBy, // kolom yang di sorting
                       @RequestParam(required = false) String search, // search by product name
                       @RequestParam(required = false) Double minPrice, // search by min price
                       @RequestParam(required = false) Double maxPrice, // search by max price
                       @RequestParam(value = "category", required = false) Set<Long> categoryIds, // search by max price
                       Model model,
                       RedirectAttributes redirectAttributes,
                       WebRequest webRequest
    ) {

        GlobalFunction.setGlobalFragment(model, webRequest);

        try {
            // get category list
            Map<String, Object> activeCategory = this.productClient.getActiveCategory(0, 100);
            Map<String, Object> mapCategoryData = (Map<String, Object>) activeCategory.get("data");
            List<Map<String, Object>> ltCategory = (List<Map<String, Object>>) mapCategoryData.get("content");

            model.addAttribute("ltCategory", ltCategory);

            //get product
            page = page > 0 ? page - 1 : page;
            Map<String, Object> activeProducts = this.productClient.getActiveProducts(page, size, sortType, sortBy, search, minPrice, maxPrice, categoryIds);
            Map<String, Object> mapProductsData = (Map<String, Object>) activeProducts.get("data");
            List<Map<String, Object>> ltProducts = (List<Map<String, Object>>) mapProductsData.get("content");

            model.addAttribute("ltProducts", ltProducts);

            Integer currentPage = (Integer) mapProductsData.get("current-page");
            Integer totalPage = (Integer) mapProductsData.get("total-page");
            model.addAttribute(Constans.CURRENT_PAGE, currentPage + 1);
            model.addAttribute(Constans.TOTAL_PAGES, totalPage);
            model.addAttribute(Constans.NAV_PAGINATION, "shop");


        } catch (Exception e) {
//            throw new RuntimeException(e);

        }

        //sent data search
        model.addAttribute("size", size);
        model.addAttribute("sortType", sortType);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("search", search);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("categoryIds", categoryIds != null ? categoryIds.stream().map(Object::toString).toList() : null);

        return "shop/shop";
    }

    @GetMapping("/product/{productId}")
    public String shopProductDetail(@PathVariable Long productId, Model model,
                                    WebRequest webRequest, RedirectAttributes redirectAttributes
    ) {

        GlobalFunction.setGlobalFragment(model, webRequest);

        try {
            Response<ProductDto> activeProductById = this.productClient.getActiveProductById(productId);
            model.addAttribute("product", activeProductById.getData());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "shop/shop_Item";
    }

    @ResponseBody
    @PostMapping("/add-cart")
    public ResponseEntity<String> addCart(@RequestBody @Valid CartAdd cartAdd, BindingResult bindingResult,
                                          Model model, WebRequest webRequest
    ) {

        if (bindingResult.hasErrors()) {
            List<String> list = bindingResult.getFieldErrors().stream().map(err -> {
                return err.getField() + " " + err.getDefaultMessage();
            }).toList();

            return ResponseEntity.badRequest().body(list.toString());
        }

        if (GlobalFunction.cekSession(webRequest) == null) {
            return ResponseEntity.badRequest().body("Please login to your account!");
        }

        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();
            Map<String, Object> response = this.transactionClient.addToCart(auth, cartAdd);

            return ResponseEntity.ok(response.get("message").toString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @GetMapping("/search")
//    public String shop(@RequestParam(defaultValue = "0") Integer page,
//                       @RequestParam(defaultValue = "15") Integer size,
//                       @RequestParam(value = "search", required = false) String search,
//                       @RequestParam(value = "category", required = false) List<Integer> categoryIds,
//                       @RequestParam(value = "minPrice", required = false) Integer minPrice,
//                       @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
//                       Model model,
//                       WebRequest webRequest
//    ) {
//        GlobalFunction.setGlobalFragment(model, webRequest);
//
//        try {
//            // get category list
//            Map<String, Object> activeCategory = this.productClient.getActiveCategory(0, 100);
//            Map<String, Object> mapCategoryData = (Map<String, Object>) activeCategory.get("data");
//            List<Map<String, Object>> ltCategory = (List<Map<String, Object>>) mapCategoryData.get("content");
//
//            model.addAttribute("ltCategory", ltCategory);
//
//            //get product
//            page = page > 0 ? page - 1 : page;
//            Map<String, Object> activeProducts = this.productClient.getActiveProducts(page, size);
//            Map<String, Object> mapProductsData = (Map<String, Object>) activeProducts.get("data");
//            List<Map<String, Object>> ltProducts = (List<Map<String, Object>>) mapProductsData.get("content");
//
//            Integer currentPage = (Integer) mapProductsData.get("current-page");
//            Integer totalPage = (Integer) mapProductsData.get("total-page");
//            model.addAttribute(Constans.CURRENT_PAGE, currentPage + 1);
//            model.addAttribute(Constans.TOTAL_PAGES, totalPage);
//            model.addAttribute(Constans.NAV_PAGINATION, "shop");
//            model.addAttribute("size", size);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        model.addAttribute("search", search);
//
//        return "shop";
//    }


}
