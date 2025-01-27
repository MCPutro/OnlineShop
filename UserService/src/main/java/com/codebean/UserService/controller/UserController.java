package com.codebean.UserService.controller;

import com.codebean.UserService.dto.AddressDto;
import com.codebean.UserService.dto.request.UserUpdateReqDto;
import com.codebean.UserService.model.User;
import com.codebean.UserService.model.UserAddress;
import com.codebean.UserService.service.UserAddressService;
import com.codebean.UserService.service.UserService;
import com.codebean.UserService.service.ValidationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private ValidationService validator;

    @PreAuthorize("hasAuthority('ALLCUSTOMER')")
    @GetMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllUsers(HttpServletRequest request) {
        return this.userService.findAll(null, request);
    }

    @PreAuthorize("hasAuthority('PROFILE')")
    @GetMapping(path = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserByToken( HttpServletRequest request) {
        try {
            // get user id from jwt token
            Long userId = Long.valueOf((String) request.getAttribute("userId"));

            return this.userService.findByIdWithAddressStatus(userId, true, request);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ALLCUSTOMERDETAIL')")
    @GetMapping(path = "/customer/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserById(@PathVariable(value = "userId") Long userId, HttpServletRequest request) {
        return this.userService.delete(userId, request);
    }

    @DeleteMapping(path = "/customer/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteUserById(@PathVariable(value = "userId") Long userId, HttpServletRequest request) {
        return this.userService.delete(userId, request);
    }

    @PatchMapping(path = "/customer/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCustomerProfile(@PathVariable(value = "userId") Long userId, @RequestBody UserUpdateReqDto dto, HttpServletRequest request) {
        this.validator.validate(dto, "FVUSR01002", request);
        User user = this.userService.custDTOtoUserModel(dto, "Customer", "sistem");
        return this.userService.update(userId, user, request);
    }

    @PostMapping(path = "/customer/{userId}/address", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUserAddress(@PathVariable(value = "userId") Long userId, @RequestBody AddressDto dto, HttpServletRequest request) {
        this.validator.validate(dto, "FVUSR01002", request);
        UserAddress address = userAddressService.addressDTOtoUserAddressModel(dto);
        return this.userAddressService.addAddress(userId, address, request);
    }

    @PatchMapping(path = "/customer/{userId}/address/{addressId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUserAddress(@PathVariable(value = "userId") Long userId, @PathVariable(value = "addressId") Long addressId, @RequestBody AddressDto dto, HttpServletRequest request) {
        this.validator.validate(dto, "FVUSR01002", request);
        UserAddress address = userAddressService.addressDTOtoUserAddressModel(dto);
        address.setID(addressId);
        return this.userAddressService.update(userId, address, request);
    }

    @DeleteMapping(path = "/customer/{userId}/address/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUserAddress(@PathVariable(value = "userId") Long userId, @PathVariable(value = "addressId") Long addressId, HttpServletRequest request) {
        return this.userAddressService.delete(addressId, request);
    }


}
