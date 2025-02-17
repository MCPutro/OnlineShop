package com.codebean.UserService.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 07 Feb 2025 23:48
@Last Modified 07 Feb 2025 23:48
Version 1.0
*/


import com.codebean.UserService.dto.request.UserAddressReq;
import com.codebean.UserService.handler.Response;
import com.codebean.UserService.model.UserAddress;
import com.codebean.UserService.service.UserAddressService;
import com.codebean.UserService.service.ValidateService;
import com.codebean.UserService.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserAddressController {

    @Autowired
    private ValidateService validateService;

    @Autowired
    private UserAddressService userAddressService;

    @PostMapping(path = "/user/address",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> addAddressByToken(@RequestBody UserAddressReq dto,
                                               HttpServletRequest request
    ) {
        // validate dto
        this.validateService.validate(dto, "FVADRCTRL02001", request);

        try {
            UserAddress userAddress = new UserAddress();
            BeanUtils.copyProperties(dto, userAddress);

            return this.userAddressService.save(userAddress, request);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR : " + e.getMessage());
            return Response.internalServerError(Constants.ACCOUNT_FAILED_TO_UPDATE, "FEADRCTRL02001", request);
        }
    }

    @PutMapping(path = "/user/address/{addressId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updateAddressByToken(@PathVariable(value = "addressId") Long addressId,
                                                  @RequestBody UserAddressReq dto,
                                                  HttpServletRequest request
    ) {
        // validate dto
        this.validateService.validate(dto, "FVADRCTRL02020", request);

        try {

            UserAddress userAddress = new UserAddress();
            BeanUtils.copyProperties(dto, userAddress);

            return this.userAddressService.update(addressId, userAddress, request);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR : " + e.getMessage());
            return Response.internalServerError(Constants.ACCOUNT_FAILED_TO_UPDATE, "FEADRCTRL02021", request);
        }
    }


    @DeleteMapping(path = "/user/address/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> deleteAddressByToken(@PathVariable(value = "addressId") Long addressId,
                                                  HttpServletRequest request
    ) {
        try {
            return this.userAddressService.delete(addressId, request);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR : " + e.getMessage());
            return Response.internalServerError(Constants.ACCOUNT_FAILED_TO_UPDATE, "FEADRCTRL02030", request);
        }
    }

    @GetMapping(path = "/user/address",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAllAddressByToken(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                  @RequestParam(value = "sizePerPage", required = false, defaultValue = "50") Integer sizePerPage,
                                                  HttpServletRequest request) {
        try {
            Pageable Pageable = PageRequest.of(page, sizePerPage);

            return this.userAddressService.findAll(Pageable, request);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR : " + e.getMessage());
            return Response.internalServerError(Constants.ACCOUNT_FAILED_TO_UPDATE, "FEADRCTRL02040", request);
        }
    }

    @GetMapping(path = "/user/address/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAddressByToken(@PathVariable(value = "addressId") Long addressId,
                                               HttpServletRequest request
    ) {
        try {
            return this.userAddressService.findById(addressId, request);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR : " + e.getMessage());
            return Response.internalServerError(Constants.ACCOUNT_FAILED_TO_UPDATE, "FEADRCTRL02030", request);
        }
    }

    //fitur search belum

}
