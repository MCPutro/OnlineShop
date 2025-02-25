package com.codebean.UserService.repository;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 08:14
@Last Modified 10 Jan 2025 08:14
Version 1.0
*/

import com.codebean.UserService.model.User;
import com.codebean.UserService.model.UserAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

    Optional<UserAddress> findFirstByIdAndIsActiveIsTrueAndUser_Id(Long addressId, Long userID);

    Optional<UserAddress> findFirstByIdAndIsActive(Long id, Boolean isActive);

    Page<UserAddress> findAllByUser_IdAndIsActive(Long userID, Boolean isActive, Pageable pageable);

    Page<UserAddress> findAllByUser_IdAndNameContainingIgnoreCaseAndIsActive(Long userID, String name, Boolean isActive, Pageable pageable);

    Page<UserAddress> findAllByUser_IdAndAddressContainingIgnoreCaseAndIsActive(Long userID, String address, Boolean isActive, Pageable pageable);

    Page<UserAddress> findAllByUser_IdAndPostalCodeContainingIgnoreCaseAndIsActive(Long userID, String postalCode, Boolean isActive, Pageable pageable);

}
