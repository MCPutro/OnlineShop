package com.codebean.ProductService.repository;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 08 Feb 2025 16:55
@Last Modified 08 Feb 2025 16:55
Version 1.0
*/

import com.codebean.ProductService.model.Category;
import com.codebean.ProductService.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
public class dummyTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    private static Random random = new Random();

    private static final List<String> categoryNames = Arrays.asList(
            "Electronics", "Furniture", "Clothing", "Sports", "Toys", "Books",
            "Food", "Automotive", "Beauty", "Home Appliances"
    );

    private static final List<String> productNames = Arrays.asList(
            "Laptop", "Chair", "Shirt", "Soccer Ball", "Doll", "Cookbook",
            "Fridge", "Car Engine", "Lipstick", "Microwave", "Smartphone",
            "Table", "Jacket", "Tennis Racket", "Action Figure", "Novel",
            "Blender", "Washing Machine", "Camera", "Sneakers", "Wristwatch"
    );

    private static final List<String> productDescriptions = Arrays.asList(
            "High quality product", "Latest model", "Comfortable and durable", "Best seller", "Affordable",
            "Limited edition", "Top-rated", "Best in class", "Perfect for home use", "Trendy and stylish"
    );

    public static List<Product> generateProducts(int numberOfProducts, List<Category> categories) {
        List<Product> products = new ArrayList<>();

        for (int i = 1; i <= numberOfProducts; i++) {
            String name = productNames.get(random.nextInt(productNames.size()));
            String description = productDescriptions.get(random.nextInt(productDescriptions.size()));
            double price = 20 + (500 * random.nextDouble());  // Random price between $20 and $500

            Product product = new Product();//Product(i, name, description, price);
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setStock(random.nextInt(100));

            // Assign a random number of categories to the product (1-3 categories)
            int categoriesCount = 1 + random.nextInt(3);
            for (int j = 0; j < categoriesCount; j++) {
                if (product.getCategories() == null) {
                    product.setCategories(new HashSet<>());
                }
                Category category = categories.get(random.nextInt(categories.size()));

                product.setSku(generateSKU(category.getName()));

                product.getCategories().add(category);
//                product.addCategory(categories.get(random.nextInt(categories.size())));
            }

            products.add(product);
        }

        return products;
    }

    public static List<Category> generateCategories() {
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < categoryNames.size(); i++) {
            categories.add(new Category(categoryNames.get(i)));
        }
        return categories;
    }

    public static String generateSKU(String category) {
        // Format tanggal: YYYYMMDDHHMMSS
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String datePart = sdf.format(new Date());

        // Generate angka acak untuk memastikan SKU unik
        Random random = new Random();
        int randomNum = 1000 + random.nextInt(9000);  // menghasilkan angka 4 digit acak antara 1000 dan 9999

        // Gabungkan kategori produk, tanggal, dan angka acak
        String sku = category.toUpperCase() + "-" + datePart + "-" + randomNum;

        return sku;
    }

    @Test
    void name() {
        List<Category> categories = generateCategories();
        this.categoryRepository.saveAll(categories);

        List<Product> products = generateProducts(100, categories);

        this.productRepository.saveAll(products);
    }
}
