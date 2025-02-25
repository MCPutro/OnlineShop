package com.codebean.UserService.service;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 20 Jan 2025 11:39
@Last Modified 20 Jan 2025 11:39
Version 1.0
*/

/**
 * Platform Code : ADR
 * Modul Code : 02
 * FV = Failed Validation
 * FE = Failed Error
 * ex = FVADR02001 -> [FV] [USR] [02] 001 -> [JENIS ERROR] [Platform Code] [MODUL CODE] [seq]
 */

import com.codebean.UserService.core.iService;
import com.codebean.UserService.dto.UserAddressDto;
import com.codebean.UserService.exception.ApiException;
import com.codebean.UserService.handler.Response;
import com.codebean.UserService.model.User;
import com.codebean.UserService.model.UserAddress;
import com.codebean.UserService.repository.UserAddressRepository;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserAddressService implements iService<UserAddress> {

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransformPagination transformPagination;

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public ResponseEntity<Object> save(UserAddress userAddress, HttpServletRequest request) {
        try {
            if (userAddress == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVADR02001", request);
            }

            //get user id from request
            Long userId = Long.valueOf(request.getAttribute(Constants.USER_ID).toString());
            Optional<User> optionalUserByUserId = this.userRepository.findFirstByIdAndIsDelete(userId, false);
            if (!optionalUserByUserId.isPresent()) {
                return Response.badRequest(Constants.ACCOUNT_NOT_FOUND, "FVADR02002", request);
            }

            User user = optionalUserByUserId.get();
            userAddress.setUser(user);
            this.userAddressRepository.save(userAddress);

            return Response.success(Constants.ADDRESS_SUCCESSFULLY_ADDED, null, request);
        } catch (Exception e) {
            // NEED SAVE TO LOG FILE
            e.printStackTrace();
            throw new ApiException(Constants.ADDRESS_FAILED_TO_CREATE, "FEADR02001", request);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> update(Long id, UserAddress userAddress, HttpServletRequest request) {
        try {
            if (userAddress == null) {
                return Response.badRequest(Constants.BAD_DATA, "FVADR02011", request);
            }

            //get user id from request
            Long userId = Long.valueOf(request.getAttribute(Constants.USER_ID).toString());

            Optional<UserAddress> optionalUserAddress = this.userAddressRepository.findFirstByIdAndIsActiveIsTrueAndUser_Id(id, userId);
            if (!optionalUserAddress.isPresent()) {
                return Response.badRequest(Constants.ADDRESS_NOT_FOUND, "FVADR02012", request);
            }

            UserAddress address = optionalUserAddress.get();
            address.setName(userAddress.getName());
            address.setAddress(userAddress.getAddress());
            address.setProvince(userAddress.getProvince());
            address.setRegency(userAddress.getRegency());
            address.setPostalCode(userAddress.getPostalCode());
            this.userAddressRepository.save(address);

            return Response.success(Constants.ADDRESS_UPDATED_SUCCESSFULLY, null, request);

//            Optional<User> optionalUserById = this.userRepository.findFirstByIDAndIsDelete(userId, false);
//            if (!optionalUserById.isPresent()) {
//                return Response.badRequest(Constants.ACCOUNT_NOT_FOUND, "FVADR02012", request);
//            }
//
//            // validate id from param and address id from optionalUserById
//            User user = optionalUserById.get();
////            boolean present = user.getAddresses().stream().filter(userAdr -> userAdr.getID().equals(id)).findFirst().isPresent();
//            boolean present = user.getAddresses().stream().anyMatch(userAdr -> userAdr.getID().equals(id));
//            if (!present) {
//                return Response.badRequest(Constants.ADDRESS_NOT_FOUND, "FVADR02014", request);
//            }

        } catch (Exception e) {
            // NEED SAVE TO LOG FILE
            throw new ApiException(Constants.ADDRESS_FAILED_TO_UPDATE, "FEADR02011", request);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try {
            Optional<UserAddress> optionalUserAddress = this.userAddressRepository.findFirstByIdAndIsActive(id, true);
            if (!optionalUserAddress.isPresent()) {
                return Response.badRequest(Constants.ADDRESS_NOT_FOUND, "FVADR02021", request);
            }

            //get user id from request
            Long userId = Long.valueOf(request.getAttribute(Constants.USER_ID).toString());
            UserAddress address = optionalUserAddress.get();
            if (address.getUser().getId() != userId) {
                return Response.badRequest(Constants.ADDRESS_NOT_FOUND, "FVADR02023", request);
            }

            optionalUserAddress.ifPresent(userAddress -> {
                userAddress.setIsActive(false);
            });

            return Response.success(Constants.ADDRESS_DELETED_SUCCESSFULLY, null, request);
        } catch (Exception e) {
            // NEED SAVE TO LOG FILE
            throw new ApiException(Constants.ADDRESS_FAILED_TO_DELETE, "FEADR02021", request);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        try {
            //get user id from request
            Long userId = Long.valueOf(request.getAttribute(Constants.USER_ID).toString());

            Page<UserAddress> pageActiveUserAddress = this.userAddressRepository.findAllByUser_IdAndIsActive(userId, true, pageable);
            List<UserAddress> content = pageActiveUserAddress.getContent();

            List<UserAddressDto> userAddressDto = this.listModelToDto(content);

            return Response.success(Constants.SUCCESS, this.transformPagination.transformPagination(userAddressDto, pageActiveUserAddress, "id", ""), request);
        } catch (Exception e) {
            // NEED SAVE TO LOG FILE
            throw new ApiException(Constants.ADDRESS_FAILED_TO_GET, "FEADR02031", request);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        try {
            //get user id from request
            Long userId = Long.valueOf(request.getAttribute(Constants.USER_ID).toString());

            Optional<UserAddress> optionalUserAddress = this.userAddressRepository.findFirstByIdAndIsActive(id, true);
            if (!optionalUserAddress.isPresent()) {
                return Response.badRequest(Constants.ADDRESS_NOT_FOUND, "FVADR02041", request);
            }

            UserAddress address = optionalUserAddress.get();

            if (userId != address.getUser().getId()) {
                return Response.badRequest(Constants.ADDRESS_NOT_FOUND, "FVADR02042", request);
            }

            UserAddressDto addressDto = this.modelMapper.map(address, UserAddressDto.class);

            return Response.success(Constants.SUCCESS, addressDto, request);
        } catch (Exception e) {
            // NEED SAVE TO LOG FILE
            throw new ApiException(Constants.ADDRESS_FAILED_TO_GET, "FEADR02041", request);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        try {
            //get user id from request
            Long userId = Long.valueOf(request.getAttribute(Constants.USER_ID).toString());

            Page<UserAddress> page;
            switch (columnName.toLowerCase()) {
                case "name":
                    page = this.userAddressRepository.findAllByUser_IdAndNameContainingIgnoreCaseAndIsActive(userId, value, true, pageable);
                    break;
                case "address":
                    page = this.userAddressRepository.findAllByUser_IdAndAddressContainingIgnoreCaseAndIsActive(userId, value, true, pageable);
                    break;
                case "postalcode":
                    page = this.userAddressRepository.findAllByUser_IdAndPostalCodeContainingIgnoreCaseAndIsActive(userId, value, true, pageable);
                    break;
                default:
                    page = this.userAddressRepository.findAllByUser_IdAndIsActive(userId, true, pageable);
                    break;
            }

            List<UserAddress> listUserAddress = page.getContent();

            List<UserAddressDto> userAddressDto = this.listModelToDto(listUserAddress);

            return Response.success(Constants.SUCCESS, this.transformPagination.transformPagination(userAddressDto, page, columnName, value), request);
        } catch (Exception e) {
            // NEED SAVE TO LOG FILE
            throw new ApiException(Constants.ADDRESS_FAILED_TO_GET, "FEADR02051", request);
        }
    }

    private List<UserAddressDto> listModelToDto(List<UserAddress> userAddressList) {
        return this.modelMapper.map(userAddressList, new TypeToken<List<UserAddressDto>>() {
        }.getType());
    }
}
