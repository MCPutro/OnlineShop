package com.codebean.ProductService.repository;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Jan 2025 22:23
@Last Modified 13 Jan 2025 22:23
Version 1.0
*/

import com.codebean.ProductService.model.Category;
import com.codebean.ProductService.model.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @EntityGraph(attributePaths = {"categories"})
    Page<Product> findAllByOrderByIdAsc(Pageable pageable);

    Optional<Product> findFirstBySku(String sku);

    Optional<Product> findByIdAndIsActive(Long id, Boolean isActive);

    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.name LIKE %:categoryName%")
    Page<Product> findProductsByCategoryName(@Param("categoryName") String categoryName, Pageable pageable);

    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.id = :categoryId")
    Page<Product> findProductsByCategoryId(@Param("categoryId") String categoryId, Pageable pageable);

    Page<Product> findAllByNameContainingIgnoreCaseAndIsActive(String name, Boolean isActive, Pageable pageable);

    Page<Product> findProductsByCategories(Set<Category> categories, Pageable pageable);

    // search by multiple category id
    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.id IN :categoryIds")
    Page<Product> findAllByCategoryIds(@Param("categoryIds") Iterable<Long> categoryIds, Pageable pageable);

    // search by product's status
    @EntityGraph(attributePaths = {"categories"})
    Page<Product> findAllByIsActive(Boolean isActive, Pageable pageable);

    // search by range price
    Page<Product> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);

    @Query("SELECT p FROM Product p JOIN p.categories c where c.id IN :categoryIds AND p.isActive = :productStatus AND p.price BETWEEN :minPrice AND :maxPrice")
    Page<Product> findAllByCategoryIdsAndStatusAndPrice(@Param("categoryIds") Iterable<Long> categoryIds,
                                                        @Param("productStatus") Boolean productStatus,
                                                        @Param("minPrice") Double minPrice,
                                                        @Param("maxPrice") Double maxPrice,
                                                        Pageable pageable
    );

    @Query("SELECT p FROM Product p JOIN p.categories c where c.id IN :categoryIds AND p.isActive = true AND p.price BETWEEN :minPrice AND :maxPrice")
    Page<Product> findAllByCategoryIdsAndPrice(@Param("categoryIds") Iterable<Long> categoryIds,
                                               @Param("minPrice") Double minPrice,
                                               @Param("maxPrice") Double maxPrice,
                                               Pageable pageable
    );

    // select dengan lock
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Product p WHERE p.id = :productId")
    Optional<Product> findByIdWithLock(@Param("productId") Long productId);

    @Query("SELECT p FROM Product p WHERE p.id in :ids AND p.isActive = :status")
    List<Product> findProductsByIdsAndStatus(@Param("ids") Iterable<Long> ids, @Param("status") Boolean status);

//    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c IN :categories")
//    List<Product> findProductsByCategories(@Param("categories") List<Category> categories);

//    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.id IN :categoryIds")
//    Page<Product> findAllByCategoryIds(@Param("categoryIds") List<Long> categoryIds, Pageable pageable);


    @Override
    @EntityGraph(attributePaths = {"categories"})
    Page<Product> findAll(Specification<Product> spec, Pageable pageable);
}
