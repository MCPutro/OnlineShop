package com.codebean.UserService.service;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 12:37
@Last Modified 10 Jan 2025 12:37
Version 1.0
*/

/**
 * Platform Code : USR
 * Modul Code : 01
 * FV = Failed Validation
 * FE = Failed Error
 * ex = FVUSR01001 -> [FV] [USR] [01] 001 -> [JENIS ERROR] [Platform Code] [MODUL CODE] [seq]
 */

import com.codebean.UserService.core.iService;
import com.codebean.UserService.dto.UserDto;
import com.codebean.UserService.handler.Response;
import com.codebean.UserService.model.Role;
import com.codebean.UserService.model.User;
import com.codebean.UserService.repository.RoleRepository;
import com.codebean.UserService.repository.UserRepository;
import com.codebean.UserService.utils.Constants;
import com.codebean.UserService.utils.TransformPagination;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements iService<User> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TransformPagination transformPagination;

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public ResponseEntity<Object> save(User user, HttpServletRequest request) {
        try {
            if (user == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVUSR01001", request);
            }

            List<User> listUserByEmailOrUsername = this.userRepository.findAllByEmailOrUsername(user.getEmail(), user.getUsername());
            if (listUserByEmailOrUsername != null && !listUserByEmailOrUsername.isEmpty()) {
                for (User u : listUserByEmailOrUsername) {
                    if (u.getEmail().equals(user.getEmail())) {
                        return Response.badRequest(Constants.EMAIL_ADDRESS_ALREADY_EXISTS, "FVUSR01002", request);
                    }

                    if (u.getUsername().equals(user.getUsername())) {
                        return Response.badRequest(Constants.USERNAME_ALREADY_EXISTS, "FVUSR01002", request);
                    }
                }
            }

            //role
            Optional<Role> optionalRoleByName = this.roleRepository.findFirstByName(user.getRole().getName());
            if (!optionalRoleByName.isPresent()) {
                return Response.badRequest(Constants.ROLE_NOT_FOUND, "FVUSR01003", request);
            } else {
                user.setRole(optionalRoleByName.get());
            }

            // hash password
            user.setPassword(this.passwordEncoder.encode(user.getUsername() + "." + user.getPassword()));

            this.userRepository.save(user);

            return Response.created(Constants.ACCOUNT_CREATED_SUCCESSFULLY, null, request);

        } catch (Exception e) {
            // NEED SAVE TO LOG
            return Response.internalServerError(Constants.ACCOUNT_FAILED_TO_SAVE, "FEUSR01003", request);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> update(Long id, User user, HttpServletRequest request) {
        try {
            if (user == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVUSR01011", request);
            }

            // find user by id
            Optional<User> optionalUserById = this.userRepository.findById(id);
            if (!optionalUserById.isPresent()) {
                return Response.badRequest(Constants.ACCOUNT_NOT_FOUND, "FVUSR01011", request);
            }

            // cek existing data
            List<User> listUserByEmailOrUsername = this.userRepository.findAllByEmailOrUsername(user.getEmail(), user.getUsername());
            if (listUserByEmailOrUsername != null && !listUserByEmailOrUsername.isEmpty()) {
                for (User u : listUserByEmailOrUsername) {
                    if (u.getEmail().equals(user.getEmail())) {
                        return Response.badRequest(Constants.EMAIL_ADDRESS_ALREADY_EXISTS, "FVUSR01012", request);
                    }

                    if (u.getUsername().equals(user.getUsername())) {
                        return Response.badRequest(Constants.USERNAME_ALREADY_EXISTS, "FVUSR01013", request);
                    }
                }
            }

            optionalUserById.ifPresent(user1 -> {
//                user1.setUsername(user.getUsername());
                user1.setEmail(user.getEmail());
                user1.setName(user.getName());
                user1.setPhoneNumber(user.getPhoneNumber());
                user1.setDateOfBirth(user.getDateOfBirth());
                user1.setGender(user.getGender());
                user1.setIsActive(user.getIsActive());
            });

            return Response.success(Constants.UPDATES_SUCCESS, null, request);
        } catch (Exception e) {
            return Response.internalServerError(Constants.ACCOUNT_FAILED_TO_UPDATE, "FEUSR01011", request);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try {
            if (id == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVUSR01021", request);
            }

            // validate user by id
            Optional<User> optionalUserByIDAndIsDelete = this.userRepository.findFirstByIDAndIsDelete(id, false);
            if (!optionalUserByIDAndIsDelete.isPresent()) {
                return Response.badRequest(Constants.ACCOUNT_NOT_FOUND, "FVUSR01021", request);
            }

            optionalUserByIDAndIsDelete.ifPresent(user1 -> {
                user1.setIsDelete(true);
            });

            return Response.success(Constants.ACCOUNT_DELETED_SUCCESSFULLY, null, request);
        } catch (Throwable t) {
            //NEED SAVE TO LOG
            return Response.internalServerError(Constants.ACCOUNT_FAILED_TO_DELETE, "FEUSR01021", request);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        try {
            Page<User> page = this.userRepository.findAllByIsDelete(pageable, false);

//            List<UserDto> listUserDto = this.listUserModelToDto(page.getContent());
            List<UserDto> listUserDto = page.getContent().stream().map(user -> {
                UserDto userDto = this.modelMapper.map(user, UserDto.class);
                userDto.setRole(user.getRole().getName());
                return userDto;
            }).toList();

            Map<String, Object> stringObjectMap = this.transformPagination.transformPagination(listUserDto, page, "id", "");

            return Response.success(Constants.SUCCESS, stringObjectMap, request);
        } catch (Throwable e) {
            return Response.internalServerError(Constants.FAILED_TO_GET_DATA, "FEUSR01031", request);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        try {
            Optional<User> optionalUserById = this.userRepository.findFirstByIDAndIsDelete(id, false);
            if (!optionalUserById.isPresent()) {
                return Response.badRequest(Constants.ACCOUNT_NOT_FOUND, "FVUSR01041", request);
            }

            User user = optionalUserById.get();
            UserDto userDto = this.modelMapper.map(user, UserDto.class);
            userDto.setRole(user.getRole().getName());

            return Response.success(Constants.SUCCESS, userDto, request);
        } catch (Exception e) {
            //NEED SAVE TO LOG
            return Response.internalServerError(Constants.ACCOUNT_NOT_FOUND, "FEUSR01041", request);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        try {
            Page<User> page;
            switch (columnName.toLowerCase()) {
                case "username":
                    page = this.userRepository.findAllByUsernameContainingIgnoreCaseAndIsDelete(pageable, value, false);
                    break;
                case "email":
                    page = this.userRepository.findAllByEmailContainingIgnoreCaseAndIsDelete(pageable, value, false);
                    break;
                case "rolename":
                    page = this.userRepository.findAllUserByStatusDeleteAndRoleName(false, value, pageable);
                    break;
                default:
                    page = this.userRepository.findAllByIsDelete(pageable, false);
                    break;
            }

            List<User> list = page.getContent();
            if (list.isEmpty()) {
                return Response.badRequest(Constants.ACCOUNT_NOT_FOUND, "FVUSR01051", request);
            }

//            List<UserDto> listUserDto = this.listUserModelToDto(list);
            List<UserDto> listUserDto = list.stream().map(user -> {
                UserDto userDto = this.modelMapper.map(user, UserDto.class);
                userDto.setRole(user.getRole().getName());
                return userDto;
            }).toList();

            Map<String, Object> stringObjectMap = this.transformPagination.transformPagination(listUserDto, page, columnName, value);

            return Response.success(Constants.SUCCESS, stringObjectMap, request);
        } catch (Exception e) {
            return Response.internalServerError(Constants.FAILED_TO_GET_DATA, "FEUSR01051", request);
        }
    }

    private List<UserDto> listUserModelToDto(List<User> listUser) {
        return this.modelMapper.map(listUser, new TypeToken<List<UserDto>>() {

        }.getType());
    }

    public User dtoToModel(UserDto dto) {
        return this.modelMapper.map(dto, User.class);
    }
}
