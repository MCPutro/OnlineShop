package com.codebean.ProductService.repository;

import com.codebean.ProductService.model.Category;
import com.codebean.ProductService.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 08 Feb 2025 17:46
@Last Modified 08 Feb 2025 17:46
Version 1.0
*/
@SpringBootTest
public class dummy2Test {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void name() {
        Category category1 = new Category("Electronics");
        Category category2 = new Category("Home Appliances");
        Category category3 = new Category("Books");
        Category category4 = new Category("Fashion");
        Category category5 = new Category("Sports & Outdoors");
        Category category6 = new Category("Beauty & Health");
        Category category7 = new Category("Toys & Games");
        Category category8 = new Category("Groceries");
        Category category9 = new Category("Automotive");
        Category category10 = new Category("Furniture");

        Random rand = new Random();
        List<Category> categories = Arrays.asList(category1, category2, category3, category4, category5,
                category6, category7, category8, category9, category10);

        categoryRepository.saveAll(categories);

        for (int i = 0; i < 100; i++) {
            String sku = "SKU" + (10000 + i);  // Example SKU format
            String name = "Product " + (i + 1); // Product name
            double price = rand.nextDouble() * 500 + 10;  // Random price between 10 and 510
            int stock = rand.nextInt(200) + 10;  // Random stock between 10 and 210
            boolean isActive = rand.nextBoolean();
            String description = "Description for " + name + "."; // Simple description

            // Assign random categories
            Set<Category> productCategories = new HashSet<>();
            int numCategories = rand.nextInt(3) + 1; // Each product can belong to 1-3 categories
            for (int j = 0; j < numCategories; j++) {
                productCategories.add(categories.get(rand.nextInt(categories.size())));
            }

            // Create the Product instance
            Product product = new Product();//((long) (i + 1), sku, name, price, stock, isActive, description, productCategories);
            // Add product to a list or save to database
            product.setSku(sku);
            product.setName(name);
            product.setPrice(price);
            product.setStock(stock);
            product.setDescription(description);
            if(product.getCategories() == null){
                product.setCategories(new HashSet<>());
            }
            product.setCategories(productCategories);

            this.productRepository.save(product);
        }





    }
}
