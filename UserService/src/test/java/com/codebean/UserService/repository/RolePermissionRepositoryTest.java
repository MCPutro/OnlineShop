package com.codebean.UserService.repository;

import com.codebean.UserService.model.RolePermissions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 24 Jan 2025 19:44
@Last Modified 24 Jan 2025 19:44
Version 1.0
*/

@SpringBootTest
class RolePermissionRepositoryTest {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Test
    void FindByRoleName() {
        List<RolePermissions> customer = this.rolePermissionRepository.findAllByRole_NameAndIsActiveIsTrue("Customer");
        assertNotNull(customer);
        assertEquals(2, customer.size());
        for (RolePermissions rolePermissions : customer) {
            System.out.println(rolePermissions.getPermission().getName());
        }
    }
}