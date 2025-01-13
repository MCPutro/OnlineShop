package com.codebean.ProductService.repository;

import com.codebean.ProductService.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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

    @Transactional(readOnly = true) //harus pake transactional
    @Test
    void name() {
        List<Product> productSayuran = this.productRepository.findProductsByCategoryName("buah-buahan");
//        productSayuran.forEach(product -> product.getCategories().size());
        for (Product product : productSayuran) {
            System.out.println("-----");
            System.out.println(product);
            System.out.println(" ");
//            System.out.println(product.getCategories() != null ? product.getCategories().size() : 0);
//            System.out.println(product.getCategories() != null ? product.getCategories() : null);
            product.getCategories().forEach(category -> System.out.println("Category : "+category.getName()));
            System.out.println("-----");
        }
    }
}