package com.codebean.UserService.service;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 12:37
@Last Modified 10 Jan 2025 12:37
Version 1.0
*/

import com.codebean.UserService.model.User;
import com.codebean.UserService.model.UserAddress;
import com.codebean.UserService.repository.UserAddressRepository;
import com.codebean.UserService.repository.UserProfileRepository;
import com.codebean.UserService.repository.UserRepository;
import com.codebean.UserService.repository.RoleRepository;
import com.codebean.UserService.model.Role;
//import com.codebean.UserService.repository.PermissionRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;


/**
 * Platform Code : USR
 * Modul Code : 01
 * FV = Failed Validation
 * FE = Failed Error
 */


@Service
public class UserService implements com.codebean.UserService.core.Service<User> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserAddressRepository userAddressRepository;

    private UserProfileRepository userProfileRepository;

    @Override
    @Transactional
    public ResponseEntity<Object> save(User user, HttpServletRequest request) {
        // Implementasi logika untuk menyimpan user ke dalam database
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> update(Long id, User user, HttpServletRequest request) {
        // Implementasi logika untuk mengupdate user berdasarkan ID
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        // Implementasi logika untuk menghapus user berdasarkan ID
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        // Implementasi logika untuk mendapatkan semua user dengan paging\
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        // Implementasi logika untuk mendapatkan user berdasarkan ID
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        // Implementasi logika untuk mencari user berdasarkan parameter tertentu
        return null;
    }

    // Fungsi untuk Role
    @Transactional
    public ResponseEntity<Object> saveRole(Role role, HttpServletRequest request) {
        // Implementasi logika untuk menyimpan role ke dalam database
        return null;
    }

    // Fungsi untuk Address
    @Transactional
    public ResponseEntity<Object> saveAddress(UserAddress address, HttpServletRequest request) {
        // Implementasi logika untuk menyimpan address ke dalam database
        return null;
    }

//    // Fungsi untuk Permission
//    @Transactional
//    public ResponseEntity<Object> savePermission(Permission permission, HttpServletRequest request) {
//        // Implementasi logika untuk menyimpan permission ke dalam database
//        return null;
//    }
}
