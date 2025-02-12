package com.codebean.transactionservice.client;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 12 Feb 2025 10:35
@Last Modified 12 Feb 2025 10:35
Version 1.0
*/

import com.codebean.transactionservice.dto.client.product.ProductDto;
import com.codebean.transactionservice.dto.client.ResponseClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ProductService", url = "http://localhost:8082")
public interface ProductServiceClient {

    @GetMapping("/api/v1/shop/product/{productId}")
    ResponseClient<ProductDto> getProductById(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long productId);

    @GetMapping("/api/v1/shop/product/ids")
    ResponseClient<List<ProductDto>> getAllProductByIds(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestParam(value = "productId") List<Long> productIds);
}
