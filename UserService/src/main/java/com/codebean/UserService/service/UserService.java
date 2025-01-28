package com.codebean.UserService.service;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 12:37
@Last Modified 10 Jan 2025 12:37
Version 1.0
*/

import com.codebean.UserService.dto.AddressDto;
import com.codebean.UserService.dto.PermissionDto;
import com.codebean.UserService.dto.UserProfileDto;
import com.codebean.UserService.dto.UserDetailDto;
import com.codebean.UserService.dto.response.UserRegRespDto;
import com.codebean.UserService.handler.Response;
import com.codebean.UserService.handler.ResponseHandler;
import com.codebean.UserService.model.*;
import com.codebean.UserService.repository.*;
import com.codebean.UserService.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.support.TransactionOperations;

import java.awt.print.Pageable;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Platform Code : USR
 * Modul Code : 01
 * FV = Failed Validation
 * FE = Failed Error
 * ex = FVUSR01001 -> [FV] [USR] [01] 001
 */

@Service
public class UserService implements com.codebean.UserService.core.Service<User> {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private UserAddressRepository userAddressRepository;

    private UserProfileRepository userProfileRepository;

    @Autowired
    private PermissionsRepository permissionsRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
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

        this.listError = new ArrayList<>();

    }

    @Override
    public ResponseEntity<Object> save(User user, HttpServletRequest request) {
        try {
            this.listError.clear();

            // cek email
            if (this.userRepository.existsByEmail(user.getEmail())) {
                this.listError.add(Constants.EMAIL_ADDRESS_ALREADY_EXIST);
            }

            // cek username
            if (this.userRepository.existsByUsername(user.getUsername())) {
                this.listError.add(Constants.USERNAME_ALREADY_EXIST);
            }

            // cek role
            Optional<Role> optionalRole = this.roleRepository.findByName(user.getRole().getName());
            if (optionalRole.isEmpty()) {
                this.listError.add(Constants.INVALID_ROLE);
            }

            //return if you got error
            if (!this.listError.isEmpty()) {
                return Response.badRequest(this.listError, "FVUSR01001", request);
            } else {
                optionalRole.ifPresent(user::setRole);
            }

            // user profile
            user.setProfile(UserProfile.builder().user(user).build());

            //set default permissions by role
            List<RolePermissions> listRolePermissions = this.rolePermissionRepository.findAllByRole_NameAndIsActiveIsTrue(user.getRole().getName());
            Set<Permissions> collect = listRolePermissions.stream().map(RolePermissions::getPermission).collect(Collectors.toSet());
            user.setPermissions(collect);

            this.userRepository.save(user);

            //mapping userResponse
            UserRegRespDto userResponse = this.modelMapper.map(user, UserRegRespDto.class);
            userResponse.setRole(user.getRole().getName());

            return Response.created(Constants.ACCOUNT_CREATED, userResponse, request);

        } catch (Throwable e) {
            this.listError.add(e.getMessage());
            return Response.internalServerError(this.listError, "FEUSR01001", request);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> update(Long id, User userUpdate, HttpServletRequest request) {
        try {
            this.listError.clear();
            Optional<User> optionalUser = this.userRepository.findFirstByIDAndIsActive(id, true);

            if (optionalUser.isPresent()) {
                // cek email
                if (this.userRepository.existsByEmail(userUpdate.getEmail())) {
                    this.listError.add(Constants.EMAIL_ADDRESS_ALREADY_EXIST);
                    return Response.badRequest(this.listError, "FVUSR01002", request);
                }

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
                    user.setProfile(userUpdate.getProfile());
                }

                // update user
                user.setEmail(userUpdate.getEmail());
                user.setPhoneNumber(userUpdate.getPhoneNumber());

                //save change
                this.userRepository.save(user);

                UserDetailDto userDetailDto = this.modelMapper.map(user, UserDetailDto.class);
                userDetailDto.setRole(user.getRole().getName());
                //return response
                return Response.success(Constants.UPDATES_SUCCESS, userDetailDto, request);
            } else {
                this.listError.add(Constants.INVALID_REQUEST);
                return Response.badRequest(this.listError, "FVUSR01003", request);
            }
        } catch (Throwable t) {
            //return error
            this.listError.add(t.getMessage());
            return Response.internalServerError(this.listError, "FEUSR01004", request);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        Optional<User> optionalUser = this.userRepository.findFirstByIDAndIsActive(id, true);
        if (optionalUser.isPresent()) {
            optionalUser.ifPresent(user -> user.setIsActive(false));
            return new ResponseEntity<>("Berhasil di hapus", HttpStatus.OK);
        } else
            return new ResponseEntity<>("User tidak ditemukan", HttpStatus.BAD_REQUEST);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        Iterable<User> iterableUser = this.userRepository.findAll();

        List<UserDetailDto> listUserDetailDTO = new ArrayList<>();
        iterableUser.forEach(user -> {
            UserDetailDto userDetail = this.modelMapper.map(user, UserDetailDto.class);
            userDetail.setRole(user.getRole().getName());
            userDetail.setProfile(this.modelMapper.map(user.getProfile(), UserProfileDto.class));
            listUserDetailDTO.add(userDetail);
            if (user.getAddresses() != null && !user.getAddresses().isEmpty()) {
                userDetail.setAddresses(this.modelUserAddressToDTO(user.getAddresses()));
            }

        });
        return Response.success(Constants.SUCCESS, listUserDetailDTO, request);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDetailDto userDetailDTO = this.modelMapper.map(user, UserDetailDto.class);
            userDetailDTO.setRole(user.getRole().getName());
            userDetailDTO.setProfile(this.modelMapper.map(user.getProfile(), UserProfileDto.class));

            // address
            if (user.getAddresses() != null && !user.getAddresses().isEmpty()) {
                userDetailDTO.setAddresses(this.modelUserAddressToDTO(user.getAddresses()));
            }

            // permission
            if (user.getPermissions() != null && !user.getPermissions().isEmpty()) {
                userDetailDTO.setPermissions(this.modelPermissionToDto(user.getPermissions()));
            }

//            return new ResponseHandler().handleResponse("Berhasil", HttpStatus.OK, optionalUser, null, request);
            return Response.success(Constants.SUCCESS, userDetailDTO, request);
        }

        return new ResponseHandler().handleResponse("Kosong", HttpStatus.OK, null, null, request);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        // Implementasi logika untuk mencari user berdasarkan parameter tertentu
        return null;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findByIdWithAddressStatus(Long userId, Boolean addressStatus, HttpServletRequest request) {
        try {
            addressStatus = (addressStatus == null) ? Boolean.TRUE : addressStatus;

            Optional<User> optionalUser = this.userRepository.findFirstByIDAndIsActive(userId, true);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                List<UserAddress> listActiveUserAddress = this.userAddressRepository.findAllByUserAndIsActive(user, addressStatus);
                user.setAddresses(listActiveUserAddress);

                UserDetailDto userDetailDTO = this.userModelToDTO(user);

                return new ResponseEntity<>(userDetailDTO, HttpStatus.OK);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    // Fungsi untuk Permission
//    @Transactional
//    public ResponseEntity<Object> savePermission(Permission permission, HttpServletRequest request) {
//        // Implementasi logika untuk menyimpan permission ke dalam database
//        return null;
//    }

    public User dtoUserToModel(Object dto) {
        try {
            User customer = this.modelMapper.map(dto, User.class);

            return customer;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }


    public UserDetailDto userModelToDTO(User user) {


        UserDetailDto userDetailDTO = this.modelMapper.map(user, UserDetailDto.class);

        userDetailDTO.setRole(user.getRole().getName());

        //profile
        UserProfileDto userProfileDto = null;
        if (user.getProfile() != null) {
            userProfileDto = this.modelMapper.map(user.getProfile(), UserProfileDto.class);
        }
        userDetailDTO.setProfile(userProfileDto);

        //address
        List<AddressDto> addressDtoList = new ArrayList<>();
        for (UserAddress userAddress : user.getAddresses()) {
            AddressDto addressDto = this.modelMapper.map(userAddress, AddressDto.class);
            addressDtoList.add(addressDto);
        }
        userDetailDTO.setAddresses(addressDtoList);

//        //permission
//        List<String> list = user.getPermissions().stream().map(Permissions::getName).toList();

//        return UserDetailDTO.builder().id(user.getID()).username(user.getUsername()).email(user.getEmail()).phoneNumber(user.getPhoneNumber()).role(user.getRole().getName()).profile(userProfileDto).build();
        return userDetailDTO;
    }

    private List<AddressDto> modelUserAddressToDTO(List<UserAddress> listUserAddress) {
        return this.modelMapper.map(listUserAddress, new TypeToken<List<AddressDto>>() {
        }.getType());
    }

    private Set<PermissionDto> modelPermissionToDto(Set<Permissions> permissions) {
        return this.modelMapper.map(permissions, new TypeToken<Set<PermissionDto>>() {
        }.getType());
    }
}
