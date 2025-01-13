package com.codebean.ProductService.repository;

import com.codebean.ProductService.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Jan 2025 22:48
@Last Modified 13 Jan 2025 22:48
Version 1.0
*/

@SpringBootTest
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void TestInsert() {
        Category category = new Category();
        category.setName("Sayuran");
        category.setCreatedBy("sistem");
        System.out.println(category);

        this.categoryRepository.save(category);
        System.out.println(category.getId());

        Category category1 = new Category();
        category1.setName("buah-buahan");
        category1.setCreatedBy("sistem");
        System.out.println(category1);

        this.categoryRepository.save(category1);
        System.out.println(category1.getId());
    }
}