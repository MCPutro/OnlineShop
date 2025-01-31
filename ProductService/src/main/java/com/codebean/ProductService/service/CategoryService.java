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
import com.codebean.ProductService.dto.CategoryDto;
import com.codebean.ProductService.handler.Response;
import com.codebean.ProductService.model.Category;
import com.codebean.ProductService.repository.CategoryRepository;
import com.codebean.ProductService.util.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements iService<Category> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    private List<CategoryDto> categoryDtos = new ArrayList<>();

    @Override
    public ResponseEntity<Object> save(Category category, HttpServletRequest request) {
        try {
            Optional<Category> optionalCategory = this.categoryRepository.findFirstByName(category.getName());
            if (optionalCategory.isPresent()) {
                return Response.badRequest(Constants.CATEGORY_ALREADY_EXIST, "adasdas", request);
            }

            this.categoryRepository.save(category);

            return Response.created(Constants.CATEGORY_CREATED_SUCCESSFULLY, this.modelMapper.map(category, CategoryDto.class), request);
        } catch (Exception e) {
            return Response.internalServerError(e.getMessage(), "asdasd", request);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> update(Long id, Category category, HttpServletRequest request) {
        try {
            Optional<Category> optionalCategoryByName = this.categoryRepository.findFirstByName(category.getName());
            if (optionalCategoryByName.isPresent()) {
                return Response.badRequest(Constants.CATEGORY_ALREADY_EXIST, "adasdas", request);
            }

            Optional<Category> optionalCategoryById = this.categoryRepository.findById(id);
            if (optionalCategoryById.isEmpty()) {
                return Response.badRequest(Constants.CATEGORY_NOT_FOUND, "adasdas", request);
            }

            optionalCategoryById.ifPresent(data -> {
                data.setName(category.getName());
            });

            return Response.success(Constants.CATEGORY_UPDATED_SUCCESSFULLY, this.modelMapper.map(category, CategoryDto.class), request);

        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(e.getMessage(), "asdasd", request);
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

            if (!optionalCategory.isPresent()) {
                return Response.badRequest(Constants.CATEGORY_NOT_FOUND, "adasdas", request);
            }
            return Response.success(Constants.CATEGORY_DELETED_SUCCESSFULLY, null, request);
        } catch (Exception e) {
            return Response.internalServerError(e.getMessage(), "asdasd", request);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        Iterable<Category> all = this.categoryRepository.findAll();

        this.categoryDtos.clear();

        all.forEach(category -> categoryDtos.add(this.modelMapper.map(category, CategoryDto.class)));

        return Response.success(Constants.SUCCESS, categoryDtos, request);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        Optional<Category> optionalCategory = this.categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return Response.badRequest(Constants.CATEGORY_NOT_FOUND, "adasdas", request);
        }

        return Response.success(Constants.SUCCESS, this.modelMapper.map(optionalCategory.get(), CategoryDto.class), request);
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        return null;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAllByStatus(Boolean status, HttpServletRequest request) {
        status = status == null ? Boolean.TRUE : status;
        List<Category> listActiveCategory = this.categoryRepository.findAllByIsActive(status);
        return Response.success(Constants.SUCCESS, this.listCategoryModelToDto(listActiveCategory), request);
    }

    public List<CategoryDto> listCategoryModelToDto(List<Category> category) {
        return this.modelMapper.map(category, new TypeToken<List<CategoryDto>>() {
        }.getType());
    }
}
