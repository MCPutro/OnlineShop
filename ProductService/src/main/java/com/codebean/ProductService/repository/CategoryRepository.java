package com.codebean.ProductService.repository;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Jan 2025 22:24
@Last Modified 13 Jan 2025 22:24
Version 1.0
*/

import com.codebean.ProductService.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findFirstByName(String name);

    Page<Category> findFirstByName(String name, Pageable pageable);

    List<Category> findAllByIsActive(Boolean isActive);

    Page<Category> findAllByIsActive(Boolean isActive, Pageable pageable);

//    Optional<Category> findFirstByIdAndIsActive(Long id, Boolean isActive);
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from Product.ProductCategory where CategoryId = :categoryId") //ONLY_SQL_SERVER
    Integer removeRelationCategoryById(@Param("categoryId") Long categoryId);
}
