package com.codebean.UserService.service;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 20 Jan 2025 11:39
@Last Modified 20 Jan 2025 11:39
Version 1.0
*/

import com.codebean.UserService.core.Service;
import com.codebean.UserService.model.User;
import com.codebean.UserService.model.UserAddress;
import com.codebean.UserService.repository.UserAddressRepository;
import com.codebean.UserService.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.Optional;

/**
 * Platform Code : ADR
 * Modul Code : 01
 * FV = Failed Validation
 * FE = Failed Error
 */

@org.springframework.stereotype.Service
public class UserAddressService implements Service<UserAddress> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<Object> save(UserAddress userAddress, HttpServletRequest request) {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> update(Long userId, UserAddress userAddress, HttpServletRequest request) {
        try {
            Optional<User> optionalUser = this.userRepository.findById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.addAddress(userAddress);
                this.userRepository.save(user);
            } else {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Berhasil di simpan", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try {
            Optional<UserAddress> optionalUserAddress = this.userAddressRepository.findById(id);
            optionalUserAddress.ifPresent(userAddress -> userAddress.setIsActive(false));
        } catch (Exception e) {
            e.printStackTrace();

            throw new RuntimeException(e);

        }
        return null;
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        return null;
    }

    public UserAddress addressDTOtoUserAddressModel(Object dto) {
        try {
            return this.modelMapper.map(dto, UserAddress.class);
        } catch (Exception e) {
            return null;
        }
    }
}
