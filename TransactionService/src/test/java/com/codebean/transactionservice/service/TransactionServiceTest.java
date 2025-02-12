package com.codebean.transactionservice.service;

import com.codebean.transactionservice.model.Cart;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 12 Feb 2025 12:58
@Last Modified 12 Feb 2025 12:58
Version 1.0
*/

@SpringBootTest
class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    void AddItemToCartTest() {
        Cart cart = new Cart();
        cart.setUserId(1L);
        cart.setProductId(1L);
        cart.setQuantity(20);

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjAwMSIsInBlcm1pc3Npb25zIjoiVklFV19VU0VSIE1BTkFHRV9VU0VSIFZJRVdfUFJPRFVDVCBNQU5BR0VfUFJPRFVDVCBWSUVXX1JPTEUgTUFOQUdFX1JPTEUgU0hPUCIsInVzZXJJZCI6MSwiaWF0IjoxNzM5MzM4OTg0LCJleHAiOjE3MzkzNzQ5ODR9.yguo9SCNTQF9Tw9Iw_lXnMc7Sbt-VvM_2tJg1h593cE";

        HttpServletRequest request = new MockHttpServletRequest();

        request.setAttribute(HttpHeaders.AUTHORIZATION,  token);

        ResponseEntity<Object> objectResponseEntity = this.transactionService.addItemToCart(cart, request);

        System.out.println(objectResponseEntity.getBody());
    }
}