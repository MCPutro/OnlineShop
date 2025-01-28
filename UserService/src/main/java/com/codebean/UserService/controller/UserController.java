package com.codebean.UserService.controller;

import com.codebean.UserService.dto.AddressDto;
import com.codebean.UserService.dto.request.UserUpdateReqDto;
import com.codebean.UserService.handler.Response;
import com.codebean.UserService.model.User;
import com.codebean.UserService.model.UserAddress;
import com.codebean.UserService.service.UserAddressService;
import com.codebean.UserService.service.UserService;
import com.codebean.UserService.service.ValidationService;
import com.codebean.UserService.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private ValidationService validationService;

    @PreAuthorize("hasAuthority('SHOWUSERS')")
    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllUsers(HttpServletRequest request) {
        return this.userService.findAll(null, request);
    }

    @PreAuthorize("hasAuthority('SHOWUSERDETAIL')")
    @GetMapping(path = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserById(@PathVariable(value = "userId") Long userId, HttpServletRequest request) {
        return this.userService.findById(userId, request);
    }

    @PreAuthorize("hasAuthority('PROFILE')")
    @GetMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserByToken(HttpServletRequest request) {
        try {
            // get user id from jwt token
            Long userId = Long.valueOf((String) request.getAttribute(Constants.USER_ID));

            return this.userService.findByIdWithAddressStatus(userId, true, request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.badRequest(e.getMessage(), "FVUSR010001", request);
        }
    }

    @PreAuthorize("hasAuthority('PROFILE')")
    @PatchMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUserProfileByToken(@RequestBody UserUpdateReqDto dto, HttpServletRequest request) {
        try {
            // validate dto
            this.validationService.validate(dto, "FVUSR020001", request);

            // get userid from token
            Long userId = Long.valueOf((String) request.getAttribute(Constants.USER_ID));

            // call service
            return this.userService.update(userId, this.userService.dtoUserToModel(dto), request);
        } catch (Exception e) {
            return Response.badRequest(e.getMessage(), "FVUSR020001", request);
        }
    }

    @PatchMapping(path = "/customer/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCustomerProfile(@PathVariable(value = "userId") Long userId, @RequestBody UserUpdateReqDto dto, HttpServletRequest request) {
        this.validationService.validate(dto, "FVUSR01002", request);
        User user = this.userService.dtoUserToModel(dto);
        return this.userService.update(userId, user, request);
    }

    @DeleteMapping(path = "/customer/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteUserById(@PathVariable(value = "userId") Long userId, HttpServletRequest request) {
        return this.userService.delete(userId, request);
    }

    @PostMapping(path = "/customer/{userId}/address", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUserAddress(@PathVariable(value = "userId") Long userId, @RequestBody AddressDto dto, HttpServletRequest request) {
        this.validationService.validate(dto, "FVUSR01002", request);
        UserAddress address = userAddressService.addressDTOtoUserAddressModel(dto);
        return this.userAddressService.addAddress(userId, address, request);
    }

    @PatchMapping(path = "/customer/{userId}/address/{addressId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUserAddress(@PathVariable(value = "userId") Long userId, @PathVariable(value = "addressId") Long addressId, @RequestBody AddressDto dto, HttpServletRequest request) {
        this.validationService.validate(dto, "FVUSR01002", request);
        UserAddress address = userAddressService.addressDTOtoUserAddressModel(dto);
        address.setID(addressId);
        return this.userAddressService.update(userId, address, request);
    }

    @DeleteMapping(path = "/customer/{userId}/address/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUserAddress(@PathVariable(value = "userId") Long userId, @PathVariable(value = "addressId") Long addressId, HttpServletRequest request) {
        return this.userAddressService.delete(addressId, request);
    }


}
