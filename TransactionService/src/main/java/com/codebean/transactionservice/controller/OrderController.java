package com.codebean.transactionservice.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Feb 2025 13:12
@Last Modified 13 Feb 2025 13:12
Version 1.0
*/

import com.codebean.transactionservice.dto.request.OrderAdd;
import com.codebean.transactionservice.handler.Response;
import com.codebean.transactionservice.service.OrderService;
import com.codebean.transactionservice.service.ValidationService;
import com.codebean.transactionservice.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ValidationService validationService;

    @PostMapping(value = "/order",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createOrder(@RequestBody OrderAdd dto, HttpServletRequest request) {
        this.validationService.validate(dto, "FVCTRLODR01001", request);

        try {
            //get user id from request
            Long userId = Long.valueOf(request.getAttribute(Constants.USER_ID).toString());
            return this.orderService.createOrder(userId, dto, request);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PreAuthorize("hasAuthority('VIEW_TRX')")
    @GetMapping(path = "/orders",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAllOrders(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                          @RequestParam(value = "sizePerPage", required = false, defaultValue = "50") Integer sizePerPage,
                                          HttpServletRequest request
    ) {
        PageRequest pageRequest = PageRequest.of(page, sizePerPage);
        return this.orderService.findAll(pageRequest, request);
    }


    @PreAuthorize("hasAuthority('VIEW_TRX')")
    @GetMapping(path = "/order/{orderId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getOrderById(HttpServletRequest request,
                                          @PathVariable(value = "orderId") Long orderId) {
        return this.orderService.findById(orderId, request);
    }


    @GetMapping(path = "/my/order",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAllOrdersByToken(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                 @RequestParam(value = "sizePerPage", required = false, defaultValue = "50") Integer sizePerPage,
                                                 HttpServletRequest request
    ) {
        try {
            PageRequest pageable = PageRequest.of(page, sizePerPage);

            // get userid from token
            Long userId = Long.valueOf((String) request.getAttribute(Constants.USER_ID));

            return this.orderService.findByParam(pageable, "userid", userId + "", request);
        } catch (Exception e) {
            return Response.internalServerError(Constants.TRANSACTION_FAILED_TO_GET, "FECTRLTRX09051", request);
        }
    }

    @GetMapping(path = "/my/order/{orderId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getOrderByTokenAndID(@PathVariable(value = "orderId") Long orderId, HttpServletRequest request) {
        try {
            return this.orderService.findByIdAndUserId(orderId, request);
        } catch (Exception e) {
            return Response.internalServerError(Constants.TRANSACTION_FAILED_TO_GET, "FECTRLTRX09051", request);
        }
    }


}
