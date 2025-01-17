package com.codebean.UserService.service;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 12:37
@Last Modified 10 Jan 2025 12:37
Version 1.0
*/

import com.codebean.UserService.dto.response.CustomerRegRespDTO;
import com.codebean.UserService.handler.ResponseHandler;
import com.codebean.UserService.model.User;
import com.codebean.UserService.model.UserAddress;
import com.codebean.UserService.model.UserProfile;
import com.codebean.UserService.repository.UserAddressRepository;
import com.codebean.UserService.repository.UserProfileRepository;
import com.codebean.UserService.repository.UserRepository;
import com.codebean.UserService.repository.RoleRepository;
import com.codebean.UserService.model.Role;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.support.TransactionOperations;

import java.awt.print.Pageable;
import java.util.*;


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

    private List<String> listError;

//    @Autowired
//    private TransactionOperations transactionOperations;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserAddressRepository userAddressRepository, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userAddressRepository = userAddressRepository;
        this.userProfileRepository = userProfileRepository;
        // ModelMapper instance
        this.modelMapper = new ModelMapper();

        this.listError = new ArrayList<>();

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
    public ResponseEntity<Object> save(User user, HttpServletRequest request) {
        try {
            this.listError.clear();

            // cek email
            if (this.userRepository.existsByEmail(user.getEmail())) {
                this.listError.add("Email Already Exists");
            }

            // cek username
            if (this.userRepository.existsByUsername(user.getUsername())) {
                this.listError.add("Username Already Exists");
            }

            // cek role
            Optional<Role> optionalRole = this.roleRepository.findByName(user.getRole().getName());
            if (optionalRole.isEmpty()) {
                this.listError.add("Role Not Found");
            }

            //return if you got error
            if (!this.listError.isEmpty()) {
                return new ResponseHandler().handleResponse(null,
                        HttpStatus.BAD_REQUEST, this.listError, "FVUSR01001", request);
            } else {
                optionalRole.ifPresent(user::setRole);
            }

            // user profile
            user.setProfile(UserProfile.builder()
                    .user(user)
                    .createdBy("system")
                    .build());

            this.userRepository.save(user);

            return new ResponseHandler().handleResponse(
                    "Berhasil di daftarkan",
                    HttpStatus.CREATED,
                    this.customerModelToDTO(user),
                    null, request
            );

        } catch (Throwable e) {
            return new ResponseHandler().handleResponse(
                    e.getMessage(), // String message
                    HttpStatus.INTERNAL_SERVER_ERROR, //HttpStatus status
                    null, // Object data
                    "FEUSR01001", //Object errorCode
                    request //HttpServletRequest request
            );
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> update(Long id, User userUpdate, HttpServletRequest request) {
        try {
            Optional<User> optionalUser = this.userRepository.findById(id);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                //update user profile
                if (user.getProfile() != null) {
                    user.getProfile().setFirstName(userUpdate.getProfile() != null ? userUpdate.getProfile().getFirstName() : user.getProfile().getFirstName());
                    user.getProfile().setLastName(userUpdate.getProfile() != null ? userUpdate.getProfile().getLastName() : user.getProfile().getLastName());
                    user.getProfile().setDateOfBirth(userUpdate.getProfile() != null ? userUpdate.getProfile().getDateOfBirth() : user.getProfile().getDateOfBirth());
                    user.getProfile().setGender(userUpdate.getProfile() != null ? userUpdate.getProfile().getGender() : user.getProfile().getGender());
                    user.getProfile().setProfilePicture(userUpdate.getProfile() != null ? userUpdate.getProfile().getProfilePicture() : user.getProfile().getProfilePicture());
                } else {
                    userUpdate.getProfile().setUser(user);
                    userUpdate.getProfile().setCreatedBy("system");
                    user.setProfile(userUpdate.getProfile());
                }

                // update user
                user.getProfile().setUpdatedBy("system");
                user.setUpdatedBy("system");
                user.setUsername(userUpdate.getUsername());
                user.setPassword(userUpdate.getPassword());
                user.setEmail(userUpdate.getEmail());
                user.setPhoneNumber(userUpdate.getPhoneNumber());

                this.userRepository.save(user);

            } else {
                // return id tidak ditemukan
            }

            return null;
        } catch (Throwable t) {
            //return error ise
            return null;
        }
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
        List<User> customer = this.userRepository.findAllByRole_Name("Customer");
        return new ResponseHandler().handleResponse(
                "Berhasil", HttpStatus.OK, customer, null, request
        );
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


    public User custDTOtoModel(Object dto, String role, String createBy) {
        try {

            User customer = this.modelMapper.map(dto, User.class);
            customer.setRole(new Role(role));
            customer.setCreatedBy(createBy);

            return customer;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }

    }

    public CustomerRegRespDTO customerModelToDTO(User user) {
        return CustomerRegRespDTO.builder()
                .id(user.getID())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole().getName())
                .build();
    }
}
