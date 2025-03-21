package com.codebean.transactionservice.repository;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Feb 2025 12:32
@Last Modified 13 Feb 2025 12:32
Version 1.0
*/

import com.codebean.transactionservice.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAllByUserId(Long userId, Pageable pageable);
    
    Optional<Order> findFirstByIdAndUserId(Long id, Long userId);

    Page<Order> findAllByOrderStatusContainingIgnoreCase(String orderStatus, Pageable pageable);
}
