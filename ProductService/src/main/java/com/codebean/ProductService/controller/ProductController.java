package com.codebean.ProductService.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Feb 2025 18:56
@Last Modified 10 Feb 2025 18:56
Version 1.0
*/

/**
 * Platform Code : PDT
 * Modul Code : CTRL07
 * FV = Failed Validation
 * FE = Failed Error
 * ex = FVPDTCTRL07001 -> [FV] [PDT] [CTRL07] [001] -> [JENIS ERROR] [Platform Code] [MODUL CODE] [seq]
 */

import com.codebean.ProductService.dto.request.ProductAdd;
import com.codebean.ProductService.dto.request.ProductStock;
import com.codebean.ProductService.dto.request.ProductUpdate;
import com.codebean.ProductService.dto.request.SearchProductDto;
import com.codebean.ProductService.handler.Response;
import com.codebean.ProductService.model.Product;
import com.codebean.ProductService.service.ProductService;
import com.codebean.ProductService.service.ValidationService;
import com.codebean.ProductService.util.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ValidationService validationService;

    @PreAuthorize("hasAuthority('MANAGE_PRODUCT')")
    @PostMapping(path = "/product",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> addProduct(@RequestBody ProductAdd dto, HttpServletRequest request) {
        this.validationService.validate(dto, "FVPDTCTRL07001", request);

        try {
            Product newProduct = new Product();
            BeanUtils.copyProperties(dto, newProduct);

            return this.productService.save(newProduct, request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.PRODUCT_FAILED_TO_ADD, "FEPDTCTRL07001", request);
        }
    }


    @PreAuthorize("hasAuthority('VIEW_PRODUCT')")
    @GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllProducts(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                            @RequestParam(value = "sizePerPage", required = false, defaultValue = "50") Integer sizePerPage,
                                            HttpServletRequest request
    ) {
        PageRequest pageRequest = PageRequest.of(page, sizePerPage);
        return this.productService.findAll(pageRequest, request);
    }


    @PreAuthorize("hasAuthority('VIEW_PRODUCT')")
    @GetMapping(path = "/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductById(@PathVariable(value = "productId") Long productId, HttpServletRequest request) {
        return this.productService.findById(productId, request);
    }


//    //    @PreAuthorize("hasAuthority('SHOP')")
//    @GetMapping(path = "/shop/products")
//    public ResponseEntity<?> getAllActiveProducts(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
//                                                  @RequestParam(value = "sizePerPage", required = false, defaultValue = "50") Integer sizePerPage,
//                                                  @RequestParam(required = false) String sortType, // asc or desc
//                                                  @RequestParam(required = false) String sortBy, // kolom yang di sorting
//                                                  @RequestParam(required = false) String search, // kolom yang di sorting
//                                                  HttpServletRequest request
//    ) {
//        PageRequest pageRequest;//= PageRequest.of(page, sizePerPage);
//        Sort sort;
//        if (sortType == null || "".equals(sortType) || sortBy == null || "".equals(sortBy)) {
//            pageRequest = PageRequest.of(page, sizePerPage);
//        } else {
//            if (sortType.equalsIgnoreCase("asc")) {
//                sort = Sort.by(Sort.Order.asc(sortBy));
//            } else {
//                sort = Sort.by(Sort.Order.desc(sortBy));
//            }
//            pageRequest = PageRequest.of(page, sizePerPage, sort);
//        }
//        if (search == null) {
//            return this.productService.findByParam(pageRequest, "status", "active", request);
//        } else {
//            return this.productService.findByParam(pageRequest, "productname", search, request);
//        }
//
//    }


    //    @PreAuthorize("hasAuthority('SHOP')")
    @GetMapping(path = "/shop/product/{productId}")
    public ResponseEntity<?> getAllActiveProductById(@PathVariable(value = "productId") Long productId,
                                                     HttpServletRequest request
    ) {
        return this.productService.findByIdAndStatus(productId, true, request);
    }


    @PreAuthorize("hasAuthority('MANAGE_PRODUCT')")
    @PatchMapping(path = "/product/{productId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProductById(@PathVariable(value = "productId") Long productId,
                                               @RequestBody ProductUpdate dto,
                                               HttpServletRequest request
    ) {
        this.validationService.validate(dto, "FVPDTCTRL07061", request);
        try {
            Product updateProduct = new Product();
            BeanUtils.copyProperties(dto, updateProduct);

            return this.productService.update(productId, updateProduct, request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.PRODUCT_FAILED_TO_ADD, "FEPDTCTRL07061", request);
        }
    }


    @PreAuthorize("hasAuthority('MANAGE_PRODUCT')")
    @DeleteMapping(path = "/product/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteProductById(@PathVariable(value = "productId") Long productId,
                                               HttpServletRequest request
    ) {
        return this.productService.delete(productId, request);
    }

    @PreAuthorize("hasAnyAuthority('SHOP')")
    @GetMapping(path = "/shop/product/ids")
    public ResponseEntity<?> getAllActiveProductByIds(@RequestParam(value = "productId") List<Long> productIds,
                                                      HttpServletRequest request
    ) {
        return this.productService.findByIdsAndStatus(productIds, true, request);
    }

    @PreAuthorize("hasAnyAuthority('SHOP')")
    @PostMapping("/shop/deduct")
    public ResponseEntity<?> deductStock(@RequestBody List<ProductStock> stockRequests, HttpServletRequest request) {
        try {
            productService.deductStock(stockRequests, request);
            return ResponseEntity.ok("Stock deducted successfully");
        } catch (RuntimeException e) {
            return Response.badRequest(e.getMessage(), "FVSTOCK001", request);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping(path = "/shop/products")
    public ResponseEntity<?> search(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                    @RequestParam(value = "sizePerPage", required = false, defaultValue = "10") Integer sizePerPage,
                                    @RequestParam(required = false) String sortType, // asc or desc
                                    @RequestParam(required = false) String sortBy, // kolom yang di sorting
                                    @RequestParam(required = false) String productName, // search by product name
                                    @RequestParam(required = false) Double minPrice, // search by min price
                                    @RequestParam(required = false) Double maxPrice, // search by max price
                                    @RequestParam(required = false) Set<Long> categoryIds, // search by max price
                                    HttpServletRequest request
    ) {
        PageRequest pageRequest;
        Sort sort;
        if (sortType == null || "".equals(sortType) || sortBy == null || "".equals(sortBy)) {
            pageRequest = PageRequest.of(page, sizePerPage);
        } else {
            if (sortType.equalsIgnoreCase("asc")) {
                sort = Sort.by(Sort.Order.asc(sortBy));
            } else {
                sort = Sort.by(Sort.Order.desc(sortBy));
            }
            pageRequest = PageRequest.of(page, sizePerPage, sort);
        }

        SearchProductDto searchProductDto = SearchProductDto.builder()
                .productName(productName)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .categoryId(categoryIds)
                .build();

        return this.productService.searchProduct(pageRequest, searchProductDto, request);
    }

}
