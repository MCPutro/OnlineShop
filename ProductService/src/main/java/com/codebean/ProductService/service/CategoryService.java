package com.codebean.ProductService.service;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 20 Jan 2025 13:08
@Last Modified 20 Jan 2025 13:08
Version 1.0
*/

/**
 * Platform Code : CTG
 * Modul Code : 06
 * FV = Failed Validation
 * FE = Failed Error
 * ex = FVCTG06001 -> [FV] [CTG] [06] 001 -> [JENIS ERROR] [Platform Code] [MODUL CODE] [seq]
 */

import com.codebean.ProductService.core.iService;
import com.codebean.ProductService.dto.CategoryDto;
import com.codebean.ProductService.handler.Response;
import com.codebean.ProductService.model.Category;
import com.codebean.ProductService.repository.CategoryRepository;
import com.codebean.ProductService.util.Constants;
import com.codebean.ProductService.util.TransformPagination;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryService implements iService<Category> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    private final List<CategoryDto> categoryDto = new ArrayList<>();

    @Autowired
    private TransformPagination transformPagination;

    @Override
    @Transactional
    public ResponseEntity<Object> save(Category category, HttpServletRequest request) {
        try {
            Optional<Category> optionalCategory = this.categoryRepository.findFirstByName(category.getName());
            if (optionalCategory.isPresent()) {
                return Response.badRequest(Constants.CATEGORY_ALREADY_EXIST, "FVCTG06001", request);
            }

            this.categoryRepository.save(category);

            return Response.created(Constants.CATEGORY_CREATED_SUCCESSFULLY, this.modelMapper.map(category, CategoryDto.class), request);
        } catch (Exception e) {
            return Response.internalServerError(e.getMessage(), "FECTG06001", request);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> update(Long id, Category category, HttpServletRequest request) {
        try {
            Optional<Category> optionalCategoryByName = this.categoryRepository.findFirstByName(category.getName());
            if (optionalCategoryByName.isPresent()) {
                return Response.badRequest(Constants.CATEGORY_ALREADY_EXIST, "FVCTG06011", request);
            }

            Optional<Category> optionalCategoryById = this.categoryRepository.findById(id);
            if (optionalCategoryById.isEmpty()) {
                return Response.badRequest(Constants.CATEGORY_NOT_FOUND, "FVCTG06012", request);
            }

            optionalCategoryById.ifPresent(data -> {
                data.setName(category.getName());
            });

            return Response.success(Constants.CATEGORY_UPDATED_SUCCESSFULLY, this.modelMapper.map(category, CategoryDto.class), request);

        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(e.getMessage(), "FECTG06011", request);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try {
            Optional<Category> optionalCategory = this.categoryRepository.findById(id);
            if (!optionalCategory.isPresent()) {
                return Response.badRequest(Constants.CATEGORY_NOT_FOUND, "FVCTG06021", request);
            }

            optionalCategory.ifPresent(category -> {
                category.setIsActive(false);
            });

            return Response.success(Constants.CATEGORY_DELETED_SUCCESSFULLY, null, request);
        } catch (Exception e) {
            return Response.internalServerError(e.getMessage(), "FECTG06021", request);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        try {
            Page<Category> pageCategory = this.categoryRepository.findAll(pageable);

            this.categoryDto.clear();

            pageCategory.forEach(category -> categoryDto.add(this.modelMapper.map(category, CategoryDto.class)));

            Map<String, Object> stringObjectMap = transformPagination.transformPagination(this.categoryDto, pageCategory, "id", "");

            return Response.success(Constants.SUCCESS, stringObjectMap, request);
        } catch (Exception e) {
            return Response.internalServerError(e.getMessage(), "FECTG06031", request);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        try {
            Optional<Category> optionalCategory = this.categoryRepository.findById(id);
            if (!optionalCategory.isPresent()) {
                return Response.badRequest(Constants.CATEGORY_NOT_FOUND, "FVCTG06041", request);
            }

            return Response.success(Constants.SUCCESS, this.modelMapper.map(optionalCategory.get(), CategoryDto.class), request);
        } catch (Exception e) {
            return Response.internalServerError(e.getMessage(), "FECTG06041", request);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        try {
            Page<Category> pageCategories;
            switch (columnName.toLowerCase()) {
                case "status":
                    if (value.equalsIgnoreCase("active")) {
                        pageCategories = this.categoryRepository.findAllByIsActive(true, pageable);
                    } else if (value.equalsIgnoreCase("inactive")) {
                        pageCategories = this.categoryRepository.findAllByIsActive(false, pageable);
                    } else {
                        return Response.badRequest(Constants.INPUT_ACTIVE_OR_INACTIVE, "FVCTG06051", request);
                    }
                    break;
                case "name":
                    pageCategories = this.categoryRepository.findFirstByName(value, pageable);
                    break;
                default:
                    pageCategories = this.categoryRepository.findAll(pageable);
                    break;
            }
            List<Category> categoryList = pageCategories.getContent();
            if (categoryList.isEmpty()) {
                return Response.badRequest(Constants.CATEGORY_NOT_FOUND, "FVCTG06055", request);
            }

            List<CategoryDto> listCategoryDto = this.listCategoryModelToDto(categoryList);
            Map<String, Object> stringObjectMap = transformPagination.transformPagination(listCategoryDto, pageCategories, columnName, value);

            return Response.success(Constants.SUCCESS, stringObjectMap, request);
        } catch (Exception e) {
            return Response.internalServerError(Constants.CATEGORY_FAILED_TO_GET, "FECTG06051", request);
        }

    }

//    @Transactional(readOnly = true)
//    public ResponseEntity<Object> findAllByStatus(Boolean status, HttpServletRequest request) {
//        status = status == null ? Boolean.TRUE : status;
//        List<Category> listActiveCategory = this.categoryRepository.findAllByIsActive(status);
//        return Response.success(Constants.SUCCESS, this.listCategoryModelToDto(listActiveCategory), request);
//    }

    public List<CategoryDto> listCategoryModelToDto(List<Category> category) {
        return this.modelMapper.map(category, new TypeToken<List<CategoryDto>>() {
        }.getType());
    }
}
