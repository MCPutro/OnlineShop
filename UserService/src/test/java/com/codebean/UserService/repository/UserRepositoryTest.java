package com.codebean.UserService.repository;

import com.codebean.UserService.model.Role;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 08:17
@Last Modified 10 Jan 2025 08:17
Version 1.0
*/

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository userRoleRepository;

    private static List<Role> listUserRoles = new ArrayList<>();


    @Test
    @Order(1)
    void TestInsertRole() {

        {
            Role userRole = new Role("Admin");
            userRole.setCreatedBy("sistem");
            userRole.setUpdatedBy("sistem");
            this.userRoleRepository.save(userRole);
            assertEquals(1, userRole.getID());
            assertNotNull(userRole.getID());
        }
        {
            Role userRole = new Role("Staff");
            userRole.setCreatedBy("sistem");
            userRole.setUpdatedBy("sistem");
            this.userRoleRepository.save(userRole);
            assertEquals(2, userRole.getID());
            assertNotNull(userRole.getID());
        }
        {
            Role userRole = new Role("Customer");
            userRole.setCreatedBy("sistem");
            userRole.setUpdatedBy("sistem");
            this.userRoleRepository.save(userRole);
            assertEquals(3, userRole.getID());
            assertNotNull(userRole.getID());
        }
    }

    @Test
    @Order(5)
    void TestFindAllRole() {
        Iterable<Role> all = this.userRoleRepository.findAll();
        assertNotNull(all);

        int size = 0;
        if (all instanceof Collection) {
            size = ((Collection<?>) all).size();
        }
        assertEquals(3, size);

        all.forEach(this.listUserRoles::add);

        assertEquals(3, this.listUserRoles.size());



    }

    @Test
    @Order(10)
    void TestDeleteRoleById() {
        Role userRole1 = this.listUserRoles.get(0);

        this.userRoleRepository.deleteById(userRole1.getID());

        Role userRole = this.userRoleRepository.findById(userRole1.getID()).orElse(null);

        assertNull(userRole);


    }
}