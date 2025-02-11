package com.codebean.ProductService.service;

import com.codebean.ProductService.model.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Feb 2025 14:22
@Last Modified 10 Feb 2025 14:22
Version 1.0
*/

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void InsertTest() {
        Product product = new Product();
        product.setSku("SKU001");
        product.setName("Product 001");
        product.setDescription("Product 001");
        product.setStock(100);
        product.setPrice(500.0);
        product.setCategoryIds(new HashSet<>(Arrays.asList(1L, 3L, 4L)));

        System.out.println(product);

        HttpServletRequest request = new MockHttpServletRequest();

        ResponseEntity<Object> save = this.productService.save(product, request);

        System.out.println(save.getBody());
    }

    @Test
    void UpdateTest() {
        Product product = new Product();
        product.setSku("SKU001");
        product.setName("Product 001xx");
        product.setDescription("Product 001xx");
        product.setStock(999);
        product.setPrice(999.0);
        product.setCategoryIds(new HashSet<>(Arrays.asList(5L, 9L)));

        HttpServletRequest request = new MockHttpServletRequest();

        ResponseEntity<Object> update = this.productService.update(101L, product, request);

        System.out.println(update);
    }

    @Test
    void FindAllTest() {
        HttpServletRequest request = new MockHttpServletRequest();

        Pageable Pageable = PageRequest.of(0, 20);

        ResponseEntity<Object> all = this.productService.findAll(Pageable, request);

        System.out.println(all);
    }
}