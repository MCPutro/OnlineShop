package com.codebean.websiteui.client;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 14 Feb 2025 09:58
@Last Modified 14 Feb 2025 09:58
Version 1.0
*/

import com.codebean.websiteui.dto.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "ProductService", url = "http://localhost:8082")
public interface ProductClient {
    @GetMapping("/api/v1/shop/category")
    Map<String, Object> getActiveCategory(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                          @RequestParam(value = "sizePerPage", required = false, defaultValue = "5") Integer sizePerPage
    );
}
