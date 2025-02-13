package com.codebean.transactionservice.service;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 12 Feb 2025 11:41
@Last Modified 12 Feb 2025 11:41
Version 1.0
*/

/**
 * Platform Code : CRT
 * Modul Code : 08
 * FV = Failed Validation
 * FE = Failed Error
 * ex = FVCRT08001 -> [FV] [CRT] [08] [001] -> [JENIS ERROR] [Platform Code] [MODUL CODE] [seq]
 */


import com.codebean.transactionservice.client.ProductServiceClient;
import com.codebean.transactionservice.client.UserServiceClient;
import com.codebean.transactionservice.dto.CartDto;
import com.codebean.transactionservice.dto.client.ResponseClient;
import com.codebean.transactionservice.dto.client.product.ProductDto;
import com.codebean.transactionservice.dto.client.user.UserDto;
import com.codebean.transactionservice.handler.Response;
import com.codebean.transactionservice.model.Cart;
import com.codebean.transactionservice.repository.CartRepository;
import com.codebean.transactionservice.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private ProductServiceClient productServiceClient;

    private final List<CartDto> listCartDto = new ArrayList<>();

    @Transactional
    public ResponseEntity<Object> save(Cart cart, HttpServletRequest request) {
        try {
            if (cart == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVCRT08001", request);
            }

            String auth = request.getHeader(HttpHeaders.AUTHORIZATION);

            // cek user id and product
            UserDto userDto;
            ProductDto productDto;
            try {
                ResponseClient<UserDto> authorization = this.userServiceClient.getUserByToken(auth);
                userDto = authorization.getData();

                ResponseClient<ProductDto> productById = this.productServiceClient.getProductById(auth, cart.getProductId());
                productDto = productById.getData();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.badRequest(Constants.FAILED_TO_GET_USER_OR_PRODUCT, "FVCRT08002", request);
            }

            // find by user id and product id
            Optional<Cart> optionalCart = this.cartRepository.findByUserIdAndProductId(userDto.getId(), cart.getProductId());

            if (optionalCart.isPresent()) {
                Cart cartDB = optionalCart.get();
                cartDB.setQuantity(cart.getQuantity() + cartDB.getQuantity());
//                cartDB.setProductId(productDto.getId());
                cartDB.setPrice(productDto.getPrice());
            } else {
                cart.setUserId(userDto.getId());
                cart.setPrice(productDto.getPrice());
                this.cartRepository.save(cart);
            }

            return Response.success(Constants.SUCCESS, null, request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.CART_ADD_ITEM_FAILED, "FECRT08001", request);
        }
    }

//    @Transactional
//    public ResponseEntity<Object> updateQuantity(Long userId, Long cartId, Integer quantity, HttpServletRequest request) {
//        try {
//            if (userId == null || cartId == null || quantity == null) {
//                return Response.badRequest(Constants.BAD_DATA, "FVCRT08021", request);
//            }
//
//            Optional<Cart> optionalCart = this.cartRepository.findFirstByIdAndUserId(cartId, userId);
//            if (!optionalCart.isPresent()) {
//                return Response.badRequest(Constants.BAD_DATA, "FVCRT08022", request);
//            }
//
//            Cart cart = optionalCart.get();
//            cart.setQuantity(quantity);
//
//            return Response.success(Constants.SUCCESS, null, request);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.internalServerError(Constants.CART_ADD_ITEM_FAILED, "FECRT08011", request);
//        }
//    }

    @Transactional
    public ResponseEntity<Object> addQuantity(Long cartId, Integer quantity, HttpServletRequest request) {
        try {
            if (cartId == null || quantity == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVCRT08021", request);
            }

            // get user id from token
            Long userId = Long.valueOf((String) request.getAttribute(Constants.USER_ID));

            Optional<Cart> optionalCart = this.cartRepository.findFirstByIdAndUserId(cartId, userId);
            if (!optionalCart.isPresent()) {
                return Response.badRequest(Constants.BAD_DATA, "FVCRT08022", request);
            }

            Cart cart = optionalCart.get();

            // cek product service
            ProductDto productDto;
            try {
                ResponseClient<ProductDto> productById = this.productServiceClient.getProductById(request.getHeader(HttpHeaders.AUTHORIZATION), cart.getProductId());
                productDto = productById.getData();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.badRequest(Constants.FAILED_TO_GET_USER_OR_PRODUCT, "FECRT08023", request);
            }

            if (quantity + cart.getQuantity() > productDto.getPrice()) {
                return Response.badRequest(Constants.INSUFFICIENT_STOCK, "FECRT08024", request);
            }

            cart.setQuantity(cart.getQuantity() + quantity);

            return Response.success(Constants.SUCCESS, null, request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.CART_ADD_ITEM_FAILED, "FECRT08011", request);
        }
    }

    @Transactional
    public ResponseEntity<Object> delete(Long cartId, HttpServletRequest request) {
        try {
            if (cartId == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVCRT08021", request);
            }

            // get user id from token
            Long userId = Long.valueOf((String) request.getAttribute(Constants.USER_ID));

            Optional<Cart> optionalCart = this.cartRepository.findFirstByIdAndUserId(cartId, userId);
            if (!optionalCart.isPresent()) {
                return Response.badRequest(Constants.CART_NOT_FOUND, "FVCRT08022", request);
            }

            this.cartRepository.delete(optionalCart.get());

            return Response.success(Constants.SUCCESS, null, request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.CART_REMOVE_ITEM_FAILED, "FECRT08021", request);
        }
    }


    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        try {
            this.listCartDto.clear();

            // get user id from token
            Long userId = Long.valueOf((String) request.getAttribute(Constants.USER_ID));

            List<Cart> carts = this.cartRepository.findByUserId(userId);

            String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
            //cara 1
            for (Cart cart : carts) {
                ProductDto productDto;
                try {
                    ResponseClient<ProductDto> productById = this.productServiceClient.getProductById(auth, cart.getProductId());
                    productDto = productById.getData();
                } catch (Exception e) {
                    e.printStackTrace();
                    return Response.internalServerError(Constants.FAILED_TO_GET_PRODUCT, "FVCRT08031", request);
                }

                CartDto cartDto = new CartDto();
                BeanUtils.copyProperties(cart, cartDto);
//                cartDto.setId(cart.getId());
//                cartDto.setUserId(cart.getUserId());
//                cartDto.setProductId(cart.getProductId());
//                cartDto.setQuantity(cart.getQuantity());
                cartDto.setProductName(productDto.getName());
                cartDto.setPrice(productDto.getPrice());

                this.listCartDto.add(cartDto);
            }

            return Response.success(Constants.SUCCESS, this.listCartDto, request);

//            List<Long> list = carts.stream().map(Cart::getProductId).toList();

//            ResponseClient<List<ProductDto>> allProductByIds = this.productServiceClient.getAllProductByIds(request.getHeader(HttpHeaders.AUTHORIZATION), list);

//            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.CART_FAILED_TO_GET, "FECRT08031", request);
        }
    }

}
