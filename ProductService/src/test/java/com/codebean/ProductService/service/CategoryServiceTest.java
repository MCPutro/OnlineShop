package com.codebean.ProductService.service;

import com.codebean.ProductService.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 20 Jan 2025 13:31
@Last Modified 20 Jan 2025 13:31
Version 1.0
*/

@SpringBootTest
class CategoryServiceTest {
    
    @Autowired
    private CategoryService categoryService;

    @Test
    void testInsertCategory() {
        List<Category> listCategory = new ArrayList<>();
        listCategory.add(new Category("sayuran1"));
        listCategory.add(new Category("sayuran2"));
        listCategory.add(new Category("sayuran3"));
        listCategory.add(new Category("sayuran"));

        for (Category category : listCategory) {
            this.categoryService.save(category, null);
        }


    }
}