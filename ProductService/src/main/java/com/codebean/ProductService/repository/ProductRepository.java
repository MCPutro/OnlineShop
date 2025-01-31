package com.codebean.ProductService.repository;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Jan 2025 22:23
@Last Modified 13 Jan 2025 22:23
Version 1.0
*/

import com.codebean.ProductService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.name = :categoryName")
    List<Product> findProductsByCategoryName(@Param("categoryName") String categoryName);

    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.id = :categoryId")
    List<Product> findProductsByCategoryId(@Param("categoryId") String categoryId);

    List<Product> findAllByNameContainingIgnoreCase(String name);
}
