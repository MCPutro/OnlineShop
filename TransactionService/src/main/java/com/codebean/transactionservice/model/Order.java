package com.codebean.transactionservice.model;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 12 Feb 2025 00:23
@Last Modified 12 Feb 2025 00:23
Version 1.0
*/

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "Orders")
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "UserId", nullable = false)
    private Long userId;

    @Column(name = "AddressId", nullable = false)
    private Long addressId;

    @Column(name = "TotalPrice", nullable = false)
    private Double totalPrice;

    @Column(name = "OrderDate", nullable = false)
    private LocalDate orderDate;

    @Column(name = "OrderStatus", nullable = false)
    private String orderStatus;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @CreatedBy
    @Column(name = "CreatedBy", updatable = false, nullable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = "CreatedDate", updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "UpdatedBy", insertable = false)
    private String updatedBy;

    @LastModifiedDate
    @Column(name = "UpdatedDate", insertable = false)
    private LocalDateTime updatedDate;

    public Order() {}

    public Order(Long userId, Long addressId, Double totalPrice) {
        this.userId = userId;
        this.addressId = addressId;
        this.totalPrice = totalPrice;
        this.orderStatus = "PENDING";
        this.orderDate = LocalDate.now();
    }

    public Order(Long userId, Long addressId) {
        this.userId = userId;
        this.addressId = addressId;
        this.orderStatus = "PENDING";
        this.orderDate = LocalDate.now();
        this.orderItems = new ArrayList<>();
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}
