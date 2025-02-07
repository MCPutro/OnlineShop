//package com.codebean.UserService;
//
///*
//IntelliJ IDEA 2024.2.4 (Community Edition)
//Build #IC-242.23726.103, built on October 23, 2024
//@Author mcputro a.k.a. Mu'ti Cahyono Putro
//Created on 24 Jan 2025 21:01
//@Last Modified 24 Jan 2025 21:01
//Version 1.0
//*/
//
//import com.codebean.UserService.model.Permission;
//import com.codebean.UserService.model.RolePermission;
//import com.codebean.UserService.model.User;
//import com.codebean.UserService.repository.RolePermissionRepository;
//import com.codebean.UserService.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.support.TransactionOperations;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@SpringBootTest
//public class UserPermissinTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RolePermissionRepository rolePermissionRepository;
//
//    @Autowired
//    private TransactionOperations transactionOperations;
//
//    @Test
//    void cekUser() {
//        this.transactionOperations.executeWithoutResult(status -> {
//            Optional<User> optionalUser = this.userRepository.findById(29L);
//
//            User user = optionalUser.get();
//
//            for (Permission data: user.getPermissions()){
//                System.out.println(data.getName());
//            }
//
//            List<RolePermission> customer = this.rolePermissionRepository.findAllByRole_NameAndIsActiveIsTrue("Customer");
//
//            Set<Permission> collect = customer.stream().map(RolePermission::getPermission).collect(Collectors.toSet());
//
//            user.setPermissions(collect);
//            this.userRepository.save(user);
//
//        });
//
//    }
//}
