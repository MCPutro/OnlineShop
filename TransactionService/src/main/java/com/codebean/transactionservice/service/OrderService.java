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
import com.codebean.transactionservice.client.UserServiceClient;
import com.codebean.transactionservice.dto.OrderDto;
import com.codebean.transactionservice.dto.client.ResponseClient;
import com.codebean.transactionservice.dto.client.product.ProductStock;
import com.codebean.transactionservice.dto.client.user.UserDto;
import com.codebean.transactionservice.dto.request.OrderAdd;
import com.codebean.transactionservice.handler.Response;
import com.codebean.transactionservice.model.Cart;
import com.codebean.transactionservice.model.Order;
import com.codebean.transactionservice.model.OrderItem;
import com.codebean.transactionservice.repository.CartRepository;
import com.codebean.transactionservice.repository.OrderItemRepository;
import com.codebean.transactionservice.repository.OrderRepository;
import com.codebean.transactionservice.utils.Constants;
import com.codebean.transactionservice.utils.TransformPagination;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private TransformPagination transformPagination;

    private final List<ProductStock> productStockList = new ArrayList<>();

    private final ModelMapper modelMapper = new ModelMapper();

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

            return Response.success(Constants.TRANSACTION_SUCCESSFUL, null, request);
        } catch (FeignException e) {
            throw new RuntimeException(e.contentUTF8());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.TRANSACTION_FAILED_TO_CREATE, "FETRX09001", request);
        }
    }


    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        try {
            Page<Order> pageOrders = this.orderRepository.findAll(pageable);

            List<Order> content = pageOrders.getContent();

            List<OrderDto> ordersDto = this.listModelOrderToDto(content);

            return Response.success(Constants.SUCCESS, this.transformPagination.transformPagination(ordersDto, pageOrders, "id", ""), request);
        } catch (Exception e) {
            return Response.internalServerError(Constants.TRANSACTION_FAILED_TO_GET, "FETRX09031", request);
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        try {
            if (id == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVTRX09041", request);
            }

            Optional<Order> optionalOrder = this.orderRepository.findById(id);

            Order order = optionalOrder.get();
            OrderDto map = this.modelMapper.map(order, OrderDto.class);

            return Response.success(Constants.SUCCESS, map, request);

        } catch (Exception e) {
            return Response.internalServerError(Constants.TRANSACTION_FAILED_TO_GET, "FETRX09041", request);
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        try {
            Page<Order> page;
            switch (columnName.toLowerCase()) {
                case "status":
                    page = this.orderRepository.findAllByUserId(Long.parseLong(value), pageable);
                    break;
                case "userid":
                    page = this.orderRepository.findAllByUserId(Long.parseLong(value), pageable);
                    break;
                default:
                    page = this.orderRepository.findAll(pageable);
                    break;
            }

            List<Order> listOrder = page.getContent();
            if (listOrder.isEmpty()) {
                return Response.badRequest(Constants.TRANSACTION_NOT_FOUND, "FVTRX09051", request);
            }

            List<OrderDto> temp = new ArrayList<>();
            String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
            for (Order o : listOrder) {
                ResponseClient<UserDto> userById = this.userServiceClient.getUserByToken(auth);

                if(Objects.equals(o.getUserId(), userById.getData().getId())){
                    OrderDto dto = this.modelMapper.map(o, OrderDto.class);
                    dto.setName(userById.getData().getName());
                    temp.add(dto);
                }
            }

//            List<OrderDto> ordersDto = this.listModelOrderToDto(listOrder);

            return Response.success(Constants.SUCCESS, this.transformPagination.transformPagination(temp, page, columnName, value), request);
        } catch (Exception e) {
            return Response.internalServerError(Constants.TRANSACTION_FAILED_TO_GET, "FETRX09051", request);
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> findByIdAndUserId(Long id, HttpServletRequest request) {
        try {
            // get userid from token
            Long userId = Long.valueOf((String) request.getAttribute(Constants.USER_ID));

            Optional<Order> optionalOrder = this.orderRepository.findFirstByIdAndUserId(id, userId);
            OrderDto map = this.modelMapper.map(optionalOrder.get(), OrderDto.class);
            return Response.success(Constants.SUCCESS, map, request);
        } catch (Exception e) {
            return Response.internalServerError(Constants.TRANSACTION_FAILED_TO_GET, "FETRX09061", request);
        }
    }

    private List<OrderDto> listModelOrderToDto(List<Order> orders) {
        return this.modelMapper.map(orders, new TypeToken<List<OrderDto>>() {
        }.getType());
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
