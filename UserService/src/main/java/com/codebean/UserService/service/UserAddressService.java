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
import java.util.List;
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
        try {
            this.userAddressRepository.save(userAddress);
            return new ResponseEntity<>("Address berhasil di simpan", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Address gagal di simpan", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> update(Long userId, UserAddress userAddress, HttpServletRequest request) {
        try {
            // find user
            Optional<User> optionalUser = this.userRepository.findFirstByIDAndIsActive(userId, true);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                // find address
                Optional<UserAddress> optionalUserAddress = this.userAddressRepository.findFirstByIDAndIsActiveIsTrue(userAddress.getID());
                // if address is exist and user id is match then update
                if (optionalUserAddress.isPresent() && optionalUserAddress.get().getUser().getID().equals(user.getID())) {
                    optionalUserAddress.ifPresent(data -> {
                        data.setAddress(userAddress.getAddress());
                        data.setCountry(userAddress.getCountry());
                        data.setName(userAddress.getName());
                        data.setPostalCode(userAddress.getPostalCode());
                    });
                } else {
                    return new ResponseEntity<>("bad request", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Berhasil di update", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("bad request", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> delete(Long addressId, HttpServletRequest request) {
        try {
            Optional<UserAddress> optionalUserAddress = this.userAddressRepository.findById(addressId);
            optionalUserAddress.ifPresent(userAddress -> userAddress.setIsActive(false));
            return new ResponseEntity<>("Adddress berhasil di hapus", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Adddress gagal di hapus", HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findById(Long userId, HttpServletRequest request) {
        List<UserAddress> listActiveUserAddress = this.userAddressRepository.findAllByIsActiveIsTrueAndUser_ID(userId);
        return new ResponseEntity<>(listActiveUserAddress, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        return null;
    }

    @Transactional
    public ResponseEntity<Object> addAddress(Long userId, UserAddress userAddress, HttpServletRequest request) {
        try {
            //find user by user id
            Optional<User> optionalUser = this.userRepository.findFirstByIDAndIsActive(userId, true);
            // if data exist
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                userAddress.setUser(user);
                this.userAddressRepository.save(userAddress);

                return new ResponseEntity<>("Adddress berhasil ditambah", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User gak ada", HttpStatus.BAD_REQUEST);
            }


        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Adddress gagal ditambah", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public UserAddress addressDTOtoUserAddressModel(Object dto) {
        try {
            return this.modelMapper.map(dto, UserAddress.class);
        } catch (Exception e) {
            return null;
        }
    }
}
