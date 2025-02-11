package com.codebean.transactionservice.service;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 11 Feb 2025 01:36
@Last Modified 11 Feb 2025 01:36
Version 1.0
*/

import com.codebean.transactionservice.dto.CartDto;
import com.codebean.transactionservice.model.Cart;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    public List<CartDto> getCart() {

        CartDto cartDto = new CartDto();
        cartDto.setId(1L);
        cartDto.setQuantity(1);
        cartDto.setUserId(1L);

        System.out.println(">>>" + cartDto);

        ArrayList<CartDto> carts = new ArrayList<>();
        carts.add(cartDto);
        return carts;
    }

}
