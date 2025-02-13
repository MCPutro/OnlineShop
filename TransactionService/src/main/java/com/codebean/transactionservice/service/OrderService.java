package com.codebean.transactionservice.service;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 12 Feb 2025 19:32
@Last Modified 12 Feb 2025 19:32
Version 1.0
*/

/**
 * Platform Code : TRX
 * Modul Code : 09
 * FV = Failed Validation
 * FE = Failed Error
 * ex = FVTRX09001 -> [FV] [TRX] [09] [001] -> [JENIS ERROR] [Platform Code] [MODUL CODE] [seq]
 */


import com.codebean.transactionservice.client.ProductServiceClient;
import com.codebean.transactionservice.dto.client.product.ProductStock;
import com.codebean.transactionservice.dto.request.OrderAdd;
import com.codebean.transactionservice.handler.Response;
import com.codebean.transactionservice.model.Cart;
import com.codebean.transactionservice.model.Order;
import com.codebean.transactionservice.model.OrderItem;
import com.codebean.transactionservice.repository.CartRepository;
import com.codebean.transactionservice.repository.OrderItemRepository;
import com.codebean.transactionservice.repository.OrderRepository;
import com.codebean.transactionservice.utils.Constants;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductServiceClient productServiceClient;

    private final List<ProductStock> productStockList = new ArrayList<>();

    @Transactional
    public ResponseEntity<Object> createOrder(Long userId, OrderAdd orderAdd, HttpServletRequest request) {
        try {
            productStockList.clear();

            if (userId == null || orderAdd == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVTRX09001", request);
            }

            // get cart by user id and cart id
            List<Cart> listCart = this.cartRepository.findAllByUserIdAndIds(userId, orderAdd.getCartIds());
            if (listCart.isEmpty()) {
                return Response.badRequest(Constants.CART_NOT_FOUND, "FVTRX09002", request);
            }

            double TotalPrice = 0;

            for (Cart cart : listCart) {
                TotalPrice += cart.getQuantity() * cart.getPrice();
                productStockList.add(new ProductStock(cart.getProductId(), cart.getQuantity()));
            }

            // Kirim semua produk dalam satu request ke product-service
//            try {
//                String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
//                this.productServiceClient.deductProducts(auth, this.productStockList);
//            } catch (FeignException f) {
//                throw new RuntimeException(f.contentUTF8());
//            } catch (RuntimeException e) {
//                // Jika terjadi error, rollback semua produk yang sudah dikurangi stoknya
//                // productServiceClient.rollbackStock2(stockRequests);
//                throw new RuntimeException(e.getMessage());
//            }

            String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
            this.productServiceClient.deductProducts(auth, this.productStockList);

            Order order = new Order();
            order.setUserId(userId);
            order.setOrderDate(LocalDate.now());
            order.setTotalPrice(TotalPrice);
            order.setAddressId(orderAdd.getAddressId());
            order.setOrderStatus("PENDING");
            List<OrderItem> orderItems = new ArrayList<>();
            for (Cart cart : listCart) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setProductId(cart.getProductId());
                orderItem.setProductPrice(cart.getPrice());
                orderItem.setQuantity(cart.getQuantity());
                orderItem.setSubTotalPrice(cart.getPrice() * orderItem.getQuantity());
                orderItems.add(orderItem);
            }
            order.setOrderItems(orderItems);

            this.orderRepository.save(order);
            this.orderItemRepository.saveAll(orderItems);

            this.cartRepository.deleteAllById(orderAdd.getCartIds());

            return null;
        } catch (FeignException e) {
            throw new RuntimeException(e.contentUTF8());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.TRANSACTION_FAILED_TO_CREATE, "FETRX09001", request);
        }
    }

//    @Transactional
//    public ResponseEntity<Object> cobaRollback() {
//        try {
//            for (int i = 0; i < 5; i++) {
//                Order o = new Order();
//                o.setUserId(1L);
//                o.setAddressId(1L);
//                o.setTotalPrice(1.0);
//                o.setOrderStatus("PENDING");
//                o.setOrderDate(LocalDate.now());
//                Thread.sleep(1500);
//                if (i == 3) {
//                    throw new Exception("FVTRX09003");
//                }
//                this.orderRepository.save(o);
//            }
//            return null;
//        } catch (Exception e) {
//            System.out.println("ini harus nya roolback");
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    public ResponseEntity<Object> cobaRollback2() {
//        try {
//            for (int i = 0; i < 5; i++) {
//                Order o = new Order();
//                o.setUserId(1L);
//                o.setAddressId(1L);
//                o.setTotalPrice(1.0);
//                o.setOrderStatus("PENDING" + i);
//                o.setOrderDate(LocalDate.now());
//                if (i == 3) {
//                    throw new Exception("FVTRX09003");
//                }
//                this.orderRepository.save(o);
//            }
//            return null;
//        } catch (Exception e) {
//            System.out.println("ini harus nya roolback");
////            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Gagal menyimpan data: " + e.getMessage());
//        }
//    }
}
