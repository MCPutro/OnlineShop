package com.codebean.UserService.model;

import com.codebean.UserService.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionOperations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 24 Jan 2025 00:31
@Last Modified 24 Jan 2025 00:31
Version 1.0
*/

@SpringBootTest
class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionOperations transactionOperations;

    @Test
    void name() {
        this.transactionOperations.executeWithoutResult(transactionStatus -> {
            Optional<User> byId = this.userRepository.findById(29L);
            User user = byId.get();
            System.out.println(user.getPermissions().size());
        });

    }
}