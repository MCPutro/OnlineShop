package com.codebean.UserService.repository;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 08:14
@Last Modified 10 Jan 2025 08:14
Version 1.0
*/

import com.codebean.UserService.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findFirstByName(String name);

    Page<Role> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Role> findAllByIsActive(Boolean isActive, Pageable pageable);
}
