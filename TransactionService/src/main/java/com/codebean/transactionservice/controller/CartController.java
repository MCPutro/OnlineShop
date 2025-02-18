package com.codebean.transactionservice.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 12 Feb 2025 13:19
@Last Modified 12 Feb 2025 13:19
Version 1.0
*/

import com.codebean.transactionservice.dto.request.CartAddDto;
import com.codebean.transactionservice.model.Cart;
import com.codebean.transactionservice.service.CartService;
import com.codebean.transactionservice.service.ValidationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ValidationService validationService;

    @PostMapping(value = "/cart/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> addToCart(@RequestBody CartAddDto dto, HttpServletRequest request) {
        this.validationService.validate(dto, "", request);

        Cart cart = new Cart();
        BeanUtils.copyProperties(dto, cart);

        return this.cartService.save(cart, request);
    }

    @PatchMapping(path = "/cart/{cartId}/{qty}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> addQtyCart(@PathVariable(value = "cartId") Long cartId,
                                        @PathVariable(value = "qty") Integer qty,
                                        HttpServletRequest request
    ) {
        return this.cartService.addQuantity(cartId, qty, request);
    }


    @DeleteMapping(path = "/cart/{cartId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> removeCart(@PathVariable(value = "cartId") Long cartId, HttpServletRequest request) {
        return this.cartService.delete(cartId, request);
    }

    @GetMapping(path = "/cart", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCart(HttpServletRequest request) {
        return this.cartService.findAll(request);
    }

    @GetMapping("/cart/review")
    public ResponseEntity<?> reviewCart(@RequestParam List<Long> cartIds, HttpServletRequest request) {
        return this.cartService.cartReview(cartIds, request);

    }
}
