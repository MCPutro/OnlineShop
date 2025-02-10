package com.codebean.ProductService.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Feb 2025 13:45
@Last Modified 10 Feb 2025 13:45
Version 1.0
*/

import com.codebean.ProductService.model.Product;
import com.codebean.ProductService.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/all-product",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAllProduct(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                           @RequestParam(value = "sizePerPage", required = false, defaultValue = "50") Integer sizePerPage,
                                           HttpServletRequest request
    ) {
        Pageable Pageable = PageRequest.of(page, sizePerPage);
        return  this.productService.findAll(Pageable, request);
    }

}
