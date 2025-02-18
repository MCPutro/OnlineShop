package com.codebean.websiteui.client;

import com.codebean.websiteui.dto.client.product.CartAdd;
import com.codebean.websiteui.dto.client.product.CartDto;
import com.codebean.websiteui.dto.client.product.OrderCreateDto;
import com.codebean.websiteui.dto.response.Response;
import com.codebean.websiteui.errorHandling.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "TransactionService", url = "http://localhost:8083", configuration = FeignClientConfig.class)
public interface TransactionClient {
    @PostMapping("/api/v1/cart/add")
    Map<String, Object> addToCart(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody CartAdd dto);

    @GetMapping("/api/v1/cart")
    Response<List<CartDto>> getCartByToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);

    @DeleteMapping("/api/v1/cart/{cartId}")
    Response<String> deleteCartById(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long cartId);

    @GetMapping("/api/v1/cart/review")
    Map<String, Object> getCartRevire(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestParam List<Long> cartIds);

    @PostMapping("/api/v1/order")
    Response<String> order(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody OrderCreateDto dto);
}
