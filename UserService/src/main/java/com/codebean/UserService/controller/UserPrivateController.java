package com.codebean.UserService.controller;

import com.codebean.UserService.dto.request.UserAddressDto;
import com.codebean.UserService.dto.request.UserUpdateReqDto;
import com.codebean.UserService.model.User;
import com.codebean.UserService.service.UserService;
import com.codebean.UserService.service.ValidationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/private")
public class UserPrivateController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidationService validator;

    @GetMapping("/v1/customer")
    public ResponseEntity<Object> getAllUsers(HttpServletRequest request) {
        return this.userService.findAll(null, request);
    }

    @PatchMapping(path = "/v1/customer/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCustomerDetails(@PathVariable(value = "id") Long id, @RequestBody UserUpdateReqDto dto, HttpServletRequest request) {
        this.validator.validate(dto, "FVUSR01002", request);

        User user = this.userService.custDTOtoModel(dto, "Customer", "sistem");

        return this.userService.update(id, user, request);
    }

    @PatchMapping(path = "/v1/customer/address/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUserAddress(@RequestBody UserAddressDto dto, HttpServletRequest request) {

        User user = userService.custDTOtoModel(dto, "Customer", "sistem");
        user.getAddresses().forEach(address -> {
            System.out.println(address.getAddress());
            System.out.println(address.getCountry());
            System.out.println(address.getPostalCode());
        });

        return null;
    }
}
