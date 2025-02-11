package com.codebean.transactionservice.service;

import com.codebean.transactionservice.dto.CartDto;
import com.codebean.transactionservice.model.Cart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 11 Feb 2025 01:37
@Last Modified 11 Feb 2025 01:37
Version 1.0
*/

@SpringBootTest
class CartServiceTest {

    @Autowired
    private CartService cartService;

    @Test
    void name() {
        List<CartDto> cart = this.cartService.getCart();
        System.out.println(cart);
    }
}