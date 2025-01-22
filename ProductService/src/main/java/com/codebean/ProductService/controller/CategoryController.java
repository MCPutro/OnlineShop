package com.codebean.ProductService.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 20 Jan 2025 14:42
@Last Modified 20 Jan 2025 14:42
Version 1.0
*/

import com.codebean.ProductService.dto.request.CategoryAddDto;
import com.codebean.ProductService.model.Category;
import com.codebean.ProductService.service.CategoryService;
import com.codebean.ProductService.service.ValidationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ValidationService validationService;

    @PostMapping(path = "/category")
    public ResponseEntity<?> addCategory(@RequestBody CategoryAddDto dto, HttpServletRequest request) {
        this.validationService.validate(dto, "CTG010010", request);

        Category category = new Category();
        BeanUtils.copyProperties(dto, category);

        return this.categoryService.save(category, request);
    }

    @GetMapping(path = "/category")
    public ResponseEntity<?> getAllCategory(HttpServletRequest request) {
//        return this.categoryService.findAll(null, request);
        return this.categoryService.findAllByStatus(true, request);
    }

    @GetMapping(path = "/category/{categoryId}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long categoryId, HttpServletRequest request) {
        return this.categoryService.findById(categoryId, request);
    }

    @DeleteMapping(path = "/category/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId, HttpServletRequest request) {
        return this.categoryService.delete(categoryId, request);
    }

    @PatchMapping(path = "/category/{categoryId}")
    public ResponseEntity<Object> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryAddDto dto, HttpServletRequest request) {
        this.validationService.validate(dto, "CTG010010", request);

        Category category = new Category();
        BeanUtils.copyProperties(dto, category);

        return this.categoryService.update(categoryId, category, request);
    }
}
