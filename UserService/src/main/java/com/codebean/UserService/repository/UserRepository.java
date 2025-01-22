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
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    List<User> findAllByRole_Name(String role);

    List<User> findAllByRole_NameAndIsActive(String roleName, Boolean isActive);

    Optional<User> findFirstByIDAndIsActive(Long id, Boolean isActive);

    Optional<User> findFirstByUsername(String username);
}
