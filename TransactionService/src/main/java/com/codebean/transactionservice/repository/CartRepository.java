package com.codebean.transactionservice.repository;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 12 Feb 2025 09:13
@Last Modified 12 Feb 2025 09:13
Version 1.0
*/

import com.codebean.transactionservice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

//    List<Cart> findByUserIdAndIsCheckedOut(Long userId, Boolean isCheckedOut);

//    List<Cart> findByUserIdAndProductIdAndIsCheckedOut(Long userId, Long productId, Boolean isCheckedOut);

    List<Cart> findByUserId(Long userId);

    Optional<Cart> findByUserIdAndProductId(Long userId, Long productId);

    Optional<Cart> findFirstByIdAndUserId(Long id, Long userId);

    @Query("SELECT c FROM Cart c WHERE c.userId = :userId AND c.id in :cartIds")
    List<Cart> findAllByUserIdAndIds(@Param("userId") Long userId,
                                     @Param("cartIds") Iterable<Long> cartIds);
}
