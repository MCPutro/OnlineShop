package com.codebean.UserService.repository;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 24 Jan 2025 01:31
@Last Modified 24 Jan 2025 01:31
Version 1.0
*/

import com.codebean.UserService.model.Permissions;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PermissionsRepository extends CrudRepository<Permissions, Long> {
    Optional<Permissions> findFirstByName(String name);
}
