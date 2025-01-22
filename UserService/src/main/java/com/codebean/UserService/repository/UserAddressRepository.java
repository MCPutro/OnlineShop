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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAddressRepository extends CrudRepository<UserAddress, Long> {

    List<UserAddress> findAllByUserAndIsActive(User user, Boolean isActive);

    Optional<UserAddress> findFirstByIDAndIsActiveIsTrue(Long id);
//    @Modifying
//    @Query("update UserAddress ua set ua.isActive = false where ua.ID = :addressId  ")
//    int softDeleteById(@Param("addressId") Long id);

    List<UserAddress> findAllByIsActiveIsTrueAndUser_ID(Long userID);

}
