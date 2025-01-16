package com.codebean.UserService.code;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Jan 2025 12:16
@Last Modified 13 Jan 2025 12:16
Version 1.0
*/

import com.codebean.UserService.dto.request.UserRegReqDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class codeTest {
    private ValidatorFactory factory;
    private Validator validator;

    @BeforeEach
    void setUp(){
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterEach
    void tearDown(){
        factory.close();
    }


    @Test
    void name() {
        UserRegReqDto registerDTO = new UserRegReqDto();
        registerDTO.setUsername("ad");

        validate(registerDTO);

    }

    void validate(Object o){
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(o);
        for (ConstraintViolation<Object> violation : constraintViolationSet){
            System.out.println(violation.getMessage());
            System.out.println(violation.getPropertyPath());
            System.out.println("++++++++");
        }
    }
}
