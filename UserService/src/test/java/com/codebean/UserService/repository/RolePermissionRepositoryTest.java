//package com.codebean.UserService.repository;
//
//import com.codebean.UserService.dto.PermissionDto;
//import com.codebean.UserService.dto.response.RolePermissionRespDto;
//import com.codebean.UserService.model.RolePermission;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
///*
//IntelliJ IDEA 2024.2.4 (Community Edition)
//Build #IC-242.23726.103, built on October 23, 2024
//@Author mcputro a.k.a. Mu'ti Cahyono Putro
//Created on 24 Jan 2025 19:44
//@Last Modified 24 Jan 2025 19:44
//Version 1.0
//*/
//
//@SpringBootTest
//class RolePermissionRepositoryTest {
//
//    @Autowired
//    private RolePermissionRepository rolePermissionRepository;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @Test
//    void FindByRoleName() {
//        List<RolePermission> customer = this.rolePermissionRepository.findAllByRole_NameAndIsActiveIsTrue("Customer");
//        assertNotNull(customer);
//        assertEquals(2, customer.size());
//        for (RolePermission rolePermissions : customer) {
//            System.out.println(rolePermissions.getPermission().getName());
//        }
//    }
//
//    @Test
//    void FindAllActiveRole() {
//        List<RolePermission> allByIsActive = this.rolePermissionRepository.findAllByRole_IsActiveAndIsActive(true, true);
//
//        Map<Long, RolePermissionRespDto> mapRole = new HashMap<>();
//
//        for (RolePermission rp : allByIsActive) {
//            // cek role udah ada belum
//            RolePermissionRespDto rolePer = mapRole.get(rp.getRole().getID());
//            if (rolePer == null) {
//                RolePermissionRespDto data = new RolePermissionRespDto(rp.getRole().getID(), rp.getRole().getName(), rp.getIsActive());
//
//                data.getPermissions().add(this.modelMapper.map(rp.getPermission(), PermissionDto.class));
//
//                mapRole.put(rp.getRole().getID(), data);
//            } else {
//                rolePer.getPermissions().add(this.modelMapper.map(rp.getPermission(), PermissionDto.class));
//            }
//        }
//
//        System.out.println(mapRole);
//
//    }
//}