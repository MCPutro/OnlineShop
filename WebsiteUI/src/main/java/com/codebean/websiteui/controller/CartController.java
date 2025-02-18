package com.codebean.websiteui.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 14 Feb 2025 16:26
@Last Modified 14 Feb 2025 16:26
Version 1.0
*/

import com.codebean.websiteui.client.TransactionClient;
import com.codebean.websiteui.client.UserClient;
import com.codebean.websiteui.dto.UserAddressDto;
import com.codebean.websiteui.dto.client.product.CartDto;
import com.codebean.websiteui.dto.client.product.OrderCreateDto;
import com.codebean.websiteui.dto.client.user.RoleCreateDto;
import com.codebean.websiteui.dto.pageAttribute;
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
public class CartController {

    @Autowired
    private TransactionClient transactionClient;

    @Autowired
    private UserClient userClient;

    @GetMapping("/cart")
    public String cart(Model model, WebRequest request, RedirectAttributes redirectAttributes) {

        //validate session
        if (GlobalFunction.cekSession(request) == null) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList("Session expired, please relogin"));
            return "redirect:/";
        }

        GlobalFunction.setGlobalFragment(model, request);

        try {
            String auth = Constans.BEARER + request.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();
            Response<List<CartDto>> responseCart = this.transactionClient.getCartByToken(auth);
            model.addAttribute("carts", responseCart.getData());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "cart/cart";
    }

    @GetMapping("/cart/delete/{cartId}")
    public String cartDelete(@PathVariable("cartId") Long cartId, Model model, WebRequest request, RedirectAttributes redirectAttributes) {
        //validate session
        if (GlobalFunction.cekSession(request) == null) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList("Session expired, please relogin"));
            return "redirect:/";
        }

        GlobalFunction.setGlobalFragment(model, request);

        try {
            String auth = Constans.BEARER + request.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();
            Response<String> stringResponse = this.transactionClient.deleteCartById(auth, cartId);

            redirectAttributes.addFlashAttribute("successMessage", stringResponse.getMessage());

            return "redirect:/cart";
        } catch (Exception e) {
            model.addAttribute(Constans.ERRORS, e.getMessage());
            return "cart/cart";
        }
    }

    @GetMapping("/checkout")
    public String checkout(@RequestParam(required = false) List<Long> selectedItems,
                           Model model,
                           WebRequest request,
                           RedirectAttributes redirectAttributes
    ) {
        //validate session
        if (GlobalFunction.cekSession(request) == null) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList("Session expired, please relogin"));
            return "redirect:/";
        }

        GlobalFunction.setGlobalFragment(model, request);

        if(selectedItems == null || selectedItems.isEmpty()){
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList("Please select at least one item or your cart is empty"));
            return "redirect:/cart";
        }

        try{
            String auth = Constans.BEARER + request.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();

            //get user address
            Response<pageAttribute<UserAddressDto>> responseAddress = this.userClient.getAddressByToken(auth, 0, 100);
            model.addAttribute("listAddress", responseAddress.getData().getContent());

            model.addAttribute("selectedItems", selectedItems);

            //count price
            Map<String, Object> cartReview = this.transactionClient.getCartRevire(auth, selectedItems);
            Map<String, Object> o = (Map<String, Object>) cartReview.get("data");
            List<Map<String, Object>> list = (List<Map<String, Object>>) o.get("list");

            model.addAttribute("totalPrice", o.get("totalPrice"));
            model.addAttribute("cartReview", list);
            return "shop/checkout";
        } catch (Exception e) {
//            throw new RuntimeException(e);
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList("Session expired, please relogin"));
            return "redirect:/";
        }

    }

    @ResponseBody
    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@Valid @RequestBody OrderCreateDto dto,
                                           BindingResult bindingResult,
                                           Model model,
                                           WebRequest webRequest
    ){

        System.out.println(dto);
        if (bindingResult.hasErrors()) {
            List<String> list = bindingResult.getFieldErrors().stream().map(err -> {
                return err.getField() + " " + err.getDefaultMessage();
            }).toList();

            return ResponseEntity.badRequest().body(list.toString());
        }

        GlobalFunction.setGlobalFragment(model, webRequest);

        try{
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();
            Response<String> order = this.transactionClient.order(auth, dto);
            return ResponseEntity.ok(order.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
