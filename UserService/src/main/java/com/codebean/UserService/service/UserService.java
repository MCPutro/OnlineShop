package com.codebean.UserService.service;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 12:37
@Last Modified 10 Jan 2025 12:37
Version 1.0
*/

import com.codebean.UserService.dto.request.UserRegisterDTO;
import com.codebean.UserService.hadler.ResponseHandler;
import com.codebean.UserService.model.User;
import com.codebean.UserService.model.UserAddress;
import com.codebean.UserService.repository.UserAddressRepository;
import com.codebean.UserService.repository.UserProfileRepository;
import com.codebean.UserService.repository.UserRepository;
import com.codebean.UserService.repository.RoleRepository;
import com.codebean.UserService.model.Role;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.Optional;


/**
 * Platform Code : USR
 * Modul Code : 01
 * FV = Failed Validation
 * FE = Failed Error
 */


@Service
public class UserService implements com.codebean.UserService.core.Service<User> {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private UserAddressRepository userAddressRepository;

    private UserProfileRepository userProfileRepository;

    private ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserAddressRepository userAddressRepository, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userAddressRepository = userAddressRepository;
        this.userProfileRepository = userProfileRepository;
        // ModelMapper instance
        this.modelMapper = new ModelMapper();

        // Custom mapping
//        TypeMap<UserRegisterDTO, User> typeMap = modelMapper.createTypeMap(UserRegisterDTO.class, User.class);
//        typeMap.addMappings(mapper -> {
//            mapper.map(UserRegisterDTO::getUsername, User::setUsername);
//            mapper.map(UserRegisterDTO::getPassword, User::setPassword);
//            mapper.map(UserRegisterDTO::getEmail, User::setEmail);
//            mapper.map(UserRegisterDTO::getPhoneNumber, User::setPhoneNumber);
//        });
    }

    @Override
    @Transactional
    public ResponseEntity<Object> save(User user, HttpServletRequest request) {
        try {
            // cek email
            if (this.userRepository.existsByEmail(user.getEmail())) {
                return new ResponseHandler().handleResponse("Email sudah terdaftar",
                        HttpStatus.BAD_REQUEST, null, "FVUSR01001", request);
            }

            // cek username
            if (this.userRepository.existsByUsername(user.getUsername())) {
                return new ResponseHandler().handleResponse("Username sudah terdaftar",
                        HttpStatus.BAD_REQUEST, null, "FVUSR01001", request);
            }

            // cek role
            Optional<Role> optionalRole = this.roleRepository.findByName(user.getRole().getName());
            if (optionalRole.isEmpty()) {
                return new ResponseHandler().handleResponse("Role tidak ditemukan",
                        HttpStatus.BAD_REQUEST, null, "FVUSR01001", request);
            }

            optionalRole.ifPresent(user::setRole);

            this.userRepository.save(user);

            return new ResponseHandler().handleResponse(
                    "Berhasil di daftarkan",
                    HttpStatus.CREATED,
                    user,
                    null, request
            );

        } catch (Throwable e) {
            ResponseEntity<Object> objectResponseEntity = new ResponseHandler().handleResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    "FEUSR01001",
                    request);
            return objectResponseEntity;
//            return null;
        }
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

    // Fungsi untuk Address
    @Transactional
    public ResponseEntity<Object> saveAddress(UserAddress address, HttpServletRequest request) {
        // Implementasi logika untuk menyimpan address ke dalam database
        return null;
    }

//    // Fungsi untuk Role
//    @Transactional
//    public ResponseEntity<Object> saveRole(Role role, HttpServletRequest request) {
//        // Implementasi logika untuk menyimpan role ke dalam database
//        return null;
//    }

//    // Fungsi untuk Permission
//    @Transactional
//    public ResponseEntity<Object> savePermission(Permission permission, HttpServletRequest request) {
//        // Implementasi logika untuk menyimpan permission ke dalam database
//        return null;
//    }


    public User userRegisDTOtoModel(UserRegisterDTO dto, String role, String createBy) {

        try {

            User user = this.modelMapper.map(dto, User.class);
            user.setRole(new Role(role));
            user.setCreatedDate(new Date());
            user.setCreatedBy(createBy);

            return user;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }

    }
}
