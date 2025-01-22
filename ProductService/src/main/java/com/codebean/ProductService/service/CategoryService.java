package com.codebean.ProductService.service;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 20 Jan 2025 13:08
@Last Modified 20 Jan 2025 13:08
Version 1.0
*/

import com.codebean.ProductService.core.iService;
import com.codebean.ProductService.model.Category;
import com.codebean.ProductService.repository.CategoryRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements iService<Category> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<Object> save(Category category, HttpServletRequest request) {
        try {
            Optional<Category> optionalCategory = this.categoryRepository.findFirstByName(category.getName());
            if (optionalCategory.isPresent()) {
                return new ResponseEntity<>("sudah terdaftar", HttpStatus.BAD_REQUEST);
            }

            this.categoryRepository.save(category);

            return new ResponseEntity<>(category, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> update(Long id, Category category, HttpServletRequest request) {
        try {
            Optional<Category> optionalCategoryByName = this.categoryRepository.findFirstByName(category.getName());
            if (optionalCategoryByName.isPresent()) {
                return new ResponseEntity<>("Category sudah ada", HttpStatus.NOT_FOUND);
            }

            Optional<Category> optionalCategoryById = this.categoryRepository.findById(id);
            if (optionalCategoryById.isEmpty()) {
                return new ResponseEntity<>("gak ketemu", HttpStatus.NOT_FOUND);
            }

            optionalCategoryById.ifPresent(data -> {
                data.setName(category.getName());
            });

            return new ResponseEntity<>("berhasil", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try {
            Optional<Category> optionalCategory = this.categoryRepository.findById(id);
            optionalCategory.ifPresent(category -> {
                category.setIsActive(false);
            });
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        Iterable<Category> all = this.categoryRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        Optional<Category> optionalCategory = this.categoryRepository.findById(id);
        return new ResponseEntity<>(optionalCategory.orElse(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        return null;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAllByStatus(Boolean status, HttpServletRequest request) {
        status = status == null ? Boolean.TRUE : status;
        List<Category> listActiveCategory = this.categoryRepository.findAllByIsActive(status);
        return new ResponseEntity<>(listActiveCategory, HttpStatus.OK);
    }
}
