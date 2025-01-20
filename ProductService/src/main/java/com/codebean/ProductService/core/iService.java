package com.codebean.ProductService.core;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 20 Jan 2025 13:09
@Last Modified 20 Jan 2025 13:09
Version 1.0
*/

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;

public interface iService<T> {
    public ResponseEntity<Object> save(T t, HttpServletRequest request);//001-010

    public ResponseEntity<Object> update(Long id, T t, HttpServletRequest request);//011-020

    public ResponseEntity<Object> delete(Long id, HttpServletRequest request);//021-030

    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request);//031-040

    public ResponseEntity<Object> findById(Long id, HttpServletRequest request);//041-050

    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request);//051-060
}
