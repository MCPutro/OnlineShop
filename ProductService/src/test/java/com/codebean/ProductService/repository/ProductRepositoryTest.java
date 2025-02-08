package com.codebean.ProductService.repository;

import com.codebean.ProductService.model.Product;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.support.TransactionOperations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Jan 2025 22:53
@Last Modified 13 Jan 2025 22:53
Version 1.0
*/

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionOperations transactionOperations;

    @Test
    void TestAddProduct() {
        Product product = new Product();
        product.setName("Kol");
        product.setPrice(1.00);
        product.setIsActive(true);
        product.setStock(100);
        product.setCreatedBy("sistem");

        this.productRepository.save(product);
        System.out.println(product.getId());
    }

    @Test
    void FindByCategoryIds() {
        transactionOperations.executeWithoutResult(status -> {
            Set<Long> asd = new HashSet<Long>();
            asd.add(1L);
            asd.add(2L);
            asd.add(3L);

            Pageable pageable = PageRequest.of(0, 100);

            Page<Product> allByCategoryIds = this.productRepository.findAllByCategoryIds(asd, pageable);
            List<Product> content = allByCategoryIds.getContent();
            for (Product product : content) {
                System.out.println(" - product id : " + product.getId());
                System.out.println("   product name : " + product.getName());
                System.out.println("   product price : " + product.getPrice());
                System.out.println("   product status : " + product.getIsActive());
                System.out.println("   product stock : " + product.getStock());
                System.out.println("   product category : " + product.getCategories().stream().map(category -> {
                            return Strings.concat(category.getId(), ".", category.getName());
                        }
                ).toList());
            }
        });
    }

    @Test
    void findAllByStatus() {
        transactionOperations.executeWithoutResult(status -> {
            Pageable pageable = PageRequest.of(0, 100);

            Page<Product> allByCategoryIds = this.productRepository.findAllByIsActive(true, pageable);
            List<Product> content = allByCategoryIds.getContent();
            System.out.println(content.size());
            for (Product product : content) {
                System.out.println(" - product id : " + product.getId());
                System.out.println("   product name : " + product.getName());
                System.out.println("   product price : " + product.getPrice());
                System.out.println("   product status : " + product.getIsActive());
                System.out.println("   product stock : " + product.getStock());
                System.out.println("   product category : " + product.getCategories().stream().map(category -> Strings.concat(category.getId(), ".", category.getName())
                ).toList());
            }

//            assertEquals(0, allByCategoryIds.getTotalElements());
        });
    }

    @Test
    void findByPrice() {
        transactionOperations.executeWithoutResult(status -> {


            Pageable pageable = PageRequest.of(0, 100);

            Page<Product> allByCategoryIds = this.productRepository.findByPriceBetween(16.0, 30.0, pageable);
            List<Product> content = allByCategoryIds.getContent();
            System.out.println(content.size());
            for (Product product : content) {
                System.out.println(" - product id : " + product.getId());
                System.out.println("   product name : " + product.getName());
                System.out.println("   product price : " + product.getPrice());
                System.out.println("   product status : " + product.getIsActive());
                System.out.println("   product stock : " + product.getStock());
                System.out.println("   product category : " + product.getCategories().stream().map(category -> Strings.concat(category.getId(), ".", category.getName())
                ).toList());
            }

//            assertEquals(0, allByCategoryIds.getTotalElements());
        });
    }

    @Test
    void findAllByCategoryIdsAndStatusAndPrice() {
        transactionOperations.executeWithoutResult(status -> {
            Set<Long> asd = new HashSet<Long>();
            asd.add(1L);
            asd.add(2L);
            asd.add(3L);

            Pageable pageable = PageRequest.of(0, 100);

            Page<Product> allByCategoryIds = this.productRepository.findAllByCategoryIdsAndStatusAndPrice(asd, false, 60.0, 100.0, pageable);
            List<Product> content = allByCategoryIds.getContent();
            System.out.println(content.size());
            for (Product product : content) {
                System.out.println(" - product id : " + product.getId());
                System.out.println("   product name : " + product.getName());
                System.out.println("   product price : " + product.getPrice());
                System.out.println("   product status : " + product.getIsActive());
                System.out.println("   product stock : " + product.getStock());
                System.out.println("   product category : " + product.getCategories().stream().map(category -> Strings.concat(category.getId(), ".", category.getName())
                ).toList());
            }

//            assertEquals(0, allByCategoryIds.getTotalElements());
        });
    }

    @Test
    void findAllByCategoryIdsAndPrice() {
        transactionOperations.executeWithoutResult(status -> {
            Set<Long> asd = new HashSet<Long>();
            asd.add(1L);
            asd.add(2L);
            asd.add(3L);

            Pageable pageable = PageRequest.of(0, 100);

            Page<Product> allByCategoryIds = this.productRepository.findAllByCategoryIdsAndPrice(asd,  16.0, 100.0, pageable);
            List<Product> content = allByCategoryIds.getContent();
            System.out.println(content.size());
            for (Product product : content) {
                System.out.println(" - product id : " + product.getId());
                System.out.println("   product name : " + product.getName());
                System.out.println("   product price : " + product.getPrice());
                System.out.println("   product status : " + product.getIsActive());
                System.out.println("   product stock : " + product.getStock());
                System.out.println("   product category : " + product.getCategories().stream().map(category -> Strings.concat(category.getId(), ".", category.getName())
                ).toList());
            }

//            assertEquals(0, allByCategoryIds.getTotalElements());
        });
    }
}