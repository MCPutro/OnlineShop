package com.codebean.ProductService.service;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 14 Jan 2025 12:52
@Last Modified 14 Jan 2025 12:52
Version 1.0
*/

/**
 * Platform Code : PDT
 * Modul Code : 07
 * FV = Failed Validation
 * FE = Failed Error
 * ex = FVPDT07001 -> [FV] [PDT] [07] [001] -> [JENIS ERROR] [Platform Code] [MODUL CODE] [seq]
 */

import com.codebean.ProductService.core.iService;
import com.codebean.ProductService.dto.request.ProductDto;
import com.codebean.ProductService.handler.Response;
import com.codebean.ProductService.model.Category;
import com.codebean.ProductService.model.Product;
import com.codebean.ProductService.repository.CategoryRepository;
import com.codebean.ProductService.repository.ProductRepository;
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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService implements iService<Product> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransformPagination transformPagination;

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public ResponseEntity<Object> save(Product product, HttpServletRequest request) {
        try {
            if (product == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVPDT07001", request);
            }

            //check by SKU
            Optional<Product> firstBySku = this.productRepository.findFirstBySku(product.getSku());
            if (firstBySku.isPresent()) {
                return Response.badRequest(Constants.PRODUCT_ALREADY_EXIST, "FVPDT07002", request);
            }

            // find category
            List<Category> listCategoryByIds = this.categoryRepository.findAllById(product.getCategoriesIds());

            // update product category
            product.setCategories(new HashSet<>(listCategoryByIds));

            // save product
            this.productRepository.save(product);

            return Response.success(Constants.SUCCESS, null, request);
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.internalServerError(Constants.PRODUCT_FAILED_TO_ADD, "FEPDT07001", request);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> update(Long id, Product product, HttpServletRequest request) {
        try {
            if (product == null || id == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVPDT07011", request);
            }

            Optional<Product> optionalProduct = this.productRepository.findByIdWithLock(id);
            if (!optionalProduct.isPresent()) {
                return Response.badRequest(Constants.PRODUCT_NOT_FOUND, "FVPDT07012", request);
            }

            Product productDB = optionalProduct.get();
            if (productDB.getIsActive()) {
                //productDB.setSku(product.getSku());
                productDB.setName(product.getName());
                productDB.setDescription(product.getDescription());
                productDB.setStock(product.getStock());
                productDB.setPrice(product.getPrice());
                productDB.setCategories(product.getCategories());

                return Response.success(Constants.PRODUCT_UPDATED_SUCCESSFULLY, null, request);
            } else {
                return Response.badRequest(Constants.PRODUCT_INACTIVE, "FVPDT07013", request);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.PRODUCT_FAILED_TO_ADD, "FEPDT07011", request);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try {
            if (id == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVPDT07021", request);
            }
            Optional<Product> optionalProduct = this.productRepository.findByIdWithLock(id);
            if (!optionalProduct.isPresent()) {
                return Response.badRequest(Constants.PRODUCT_NOT_FOUND, "FVPDT07022", request);
            }

            Product product = optionalProduct.get();
            if (product.getIsActive()) {
                product.setIsActive(false);
            } else {
                return Response.badRequest(Constants.PRODUCT_INACTIVE, "FVPDT07023", request);
            }

//            optionalProduct.ifPresent(product1 -> {
//                product1.setIsActive(false);
//            });

            return Response.success(Constants.PRODUCT_DELETED_SUCCESSFULLY, null, request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.PRODUCT_FAILED_TO_DELETE, "FEPDT07021", request);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        try {
            Page<Product> pageProduct = this.productRepository.findAll(pageable);

            List<ProductDto> listProductDto = this.listModelProductDto(pageProduct.getContent());

            return Response.success(Constants.SUCCESS,
                    this.transformPagination.transformPagination(listProductDto, pageProduct, "id", ""),
                    request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.PRODUCT_FAILED_TO_GET, "FEPDT07031", request);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        try {
            if (id == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVPDT07041", request);
            }

            Optional<Product> optionalProduct = this.productRepository.findById(id);
            if (!optionalProduct.isPresent()) {
                return Response.badRequest(Constants.PRODUCT_NOT_FOUND, "FVPDT07042", request);
            }

            ProductDto productDto = this.modelMapper.map(optionalProduct.get(), ProductDto.class);
            return Response.success(Constants.SUCCESS, productDto, request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.PRODUCT_FAILED_TO_GET, "FEPDT07041", request);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        try {
            Page<Product> pageProduct;
            switch (columnName.toLowerCase()) {
                case "productname":
                    pageProduct = this.productRepository.findAllByNameContainingIgnoreCase(value, pageable);
                    break;
                case "categoryname":
                    pageProduct = this.productRepository.findProductsByCategoryName(value, pageable);
                    break;
                default:
                    pageProduct = this.productRepository.findAll(pageable);
                    break;
            }

            List<Product> listProduct = pageProduct.getContent();
            if (listProduct == null || listProduct.isEmpty()) {
                return Response.success(Constants.PRODUCT_NOT_FOUND, null, request);
            }

            List<ProductDto> listModelProductDto = this.listModelProductDto(listProduct);

            return Response.success(Constants.SUCCESS, this.transformPagination.transformPagination(listModelProductDto, pageProduct, columnName, value), request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(Constants.PRODUCT_FAILED_TO_GET, "FEPDT07051", request);
        }
    }

    private List<ProductDto> listModelProductDto(List<Product> products) {
        return this.modelMapper.map(products, new TypeToken<List<ProductDto>>() {
        }.getType());
    }
}
