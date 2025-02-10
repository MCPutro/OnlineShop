package com.codebean.UserService.service;

import com.codebean.UserService.dto.ModuleDto;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 09 Feb 2025 19:53
@Last Modified 09 Feb 2025 19:53
Version 1.0
*/

@SpringBootTest
class ModuleServiceTest {

    @Autowired
    private ModuleService moduleService;


    @Test
    void findAllModulesTest() {

        HttpServletRequest request = new MockHttpServletRequest();
        ResponseEntity<Object> allModules = this.moduleService.findAllModules(request);

        assertTrue(allModules.getStatusCode().is2xxSuccessful());
        Map<String, Object> data = (Map<String, Object>) allModules.getBody();

        assert data != null;
        List<ModuleDto> test = (List<ModuleDto>) data.get("data");

        System.out.println(test);
    }

    @Test
    void findAllModulesByStatusTest() {

        HttpServletRequest request = new MockHttpServletRequest();
        ResponseEntity<Object> allModules = this.moduleService.findAllModulesByStatus(true, request);

        assertTrue(allModules.getStatusCode().is2xxSuccessful());
        Map<String, Object> data = (Map<String, Object>) allModules.getBody();

        assert data != null;
        List<ModuleDto> test = (List<ModuleDto>) data.get("data");

        System.out.println(test);
    }

    @Test
    void findAllByModuleStatusAndPermissionStatusTest() {

        HttpServletRequest request = new MockHttpServletRequest();
        ResponseEntity<Object> allModules = this.moduleService.findAllByModuleStatusAndPermissionStatus(true, true, request);

        assertTrue(allModules.getStatusCode().is2xxSuccessful());
        Map<String, Object> data = (Map<String, Object>) allModules.getBody();

        assert data != null;
        List<ModuleDto> test = (List<ModuleDto>) data.get("data");

        System.out.println(test);
    }
}