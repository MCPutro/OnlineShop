package com.codebean.UserService.controller;

import com.codebean.UserService.dto.request.UserAddressReqDto;
import com.codebean.UserService.dto.request.UserCreateReqDto;
import com.codebean.UserService.dto.request.UserUpdateReqDto;
import com.codebean.UserService.handler.Response;
import com.codebean.UserService.model.Role;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private ValidationService validationService;

    @PreAuthorize("hasAuthority('ADDUSER')")
    @PostMapping(path = "/user-create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody UserCreateReqDto dto, HttpServletRequest request) {
        try {
            this.validationService.validate(dto, "asdad", request);

            User user = this.userService.dtoUserToModel(dto);
            user.setRole(new Role(dto.getRole()));

            return  this.userService.save(user, request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.badRequest(e.getMessage(), "FVUSR010001", request);
        }

    }

    @PreAuthorize("hasAuthority('SHOWUSERS')") //done
    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllUsers(HttpServletRequest request) {
        return this.userService.findAll(null, request);
    }

    @PreAuthorize("hasAuthority('SHOWUSERDETAIL')") //done
    @GetMapping(path = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserById(@PathVariable(value = "userId") Long userId, HttpServletRequest request) {
        return this.userService.findById(userId, request);
    }

    @PreAuthorize("hasAuthority('PROFILE')") //done
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

    @PreAuthorize("hasAuthority('PROFILE')") //done
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

    @PreAuthorize("hasAuthority('EDITUSERDETAIL')") //done
    @PatchMapping(path = "/user/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUserProfile(@PathVariable(value = "userId") Long userId, @RequestBody UserUpdateReqDto dto, HttpServletRequest request) {
        this.validationService.validate(dto, "FVUSR01002", request);
        User user = this.userService.dtoUserToModel(dto);
        return this.userService.update(userId, user, request);
    }

    @DeleteMapping(path = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteUserById(@PathVariable(value = "userId") Long userId, HttpServletRequest request) {
        return this.userService.delete(userId, request);
    }

    @PreAuthorize("hasAuthority('PROFILE')") // done
    @PostMapping(path = "/user/address", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUserAddressByToken(@RequestBody UserAddressReqDto dto, HttpServletRequest request) {
        // validate dto
        this.validationService.validate(dto, "FVUSR01002", request);

        // get userid from token
        Long userId = Long.valueOf((String) request.getAttribute(Constants.USER_ID));

        UserAddress address = userAddressService.addressDTOtoUserAddressModel(dto);

        return this.userAddressService.addAddress(userId, address, request);
    }

    @PreAuthorize("hasAuthority('EDITUSERDETAIL')") //done
    @PostMapping(path = "/user/{userId}/address", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUserAddress(@PathVariable(value = "userId") Long userId, @RequestBody UserAddressReqDto dto, HttpServletRequest request) {
        this.validationService.validate(dto, "FVUSR01002", request);
        UserAddress address = userAddressService.addressDTOtoUserAddressModel(dto);
        return this.userAddressService.addAddress(userId, address, request);
    }

    @PreAuthorize("hasAuthority('PROFILE')") // done
    @PatchMapping(path = "/user/address/{addressId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUserAddressByToken(@PathVariable(value = "addressId") Long addressId, @RequestBody UserAddressReqDto dto, HttpServletRequest request) {
        // validate dto
        this.validationService.validate(dto, "FVUSR01002", request);

        // get user id from token
        Long userId = Long.valueOf((String) request.getAttribute(Constants.USER_ID));

        UserAddress address = userAddressService.addressDTOtoUserAddressModel(dto);
        address.setID(addressId);

        return this.userAddressService.update(userId, address, request);
    }

    @PreAuthorize("hasAnyAuthority('EDITUSERDETAIL')")//cek
    @PatchMapping(path = "/user/{userId}/address/{addressId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUserAddress(@PathVariable(value = "userId") Long userId, @PathVariable(value = "addressId") Long addressId, @RequestBody UserAddressReqDto dto, HttpServletRequest request) {
        this.validationService.validate(dto, "FVUSR01002", request);
        UserAddress address = userAddressService.addressDTOtoUserAddressModel(dto);
        address.setID(addressId);
        return this.userAddressService.update(userId, address, request);
    }

    @PreAuthorize("hasAnyAuthority('BLMDIBUAT')")
    @DeleteMapping(path = "/user/{userId}/address/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUserAddress(@PathVariable(value = "userId") Long userId, @PathVariable(value = "addressId") Long addressId, HttpServletRequest request) {
        return this.userAddressService.delete(addressId, request);
    }

    @PreAuthorize("hasAnyAuthority('BLMDIBUAT')")
    @DeleteMapping(path = "/user/address/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUserAddressByToken(@PathVariable(value = "addressId") Long addressId, HttpServletRequest request) {
        // get user id from token

        return null;
    }

}
