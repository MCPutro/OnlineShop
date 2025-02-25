package com.codebean.UserService.repository;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 07 Feb 2025 23:07
@Last Modified 07 Feb 2025 23:07
Version 1.0
*/

import com.codebean.UserService.model.Module;
import com.codebean.UserService.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    List<Module> findAllByIsActive(Boolean isActive);

    @Query("SELECT m FROM Module m INNER JOIN FETCH m.permissions p WHERE m.isActive = :moduleStatus and p.isActive = :permissionStatus")
    List<Module> findAllByModuleStatusAndPermissionsStatus(@Param("moduleStatus") Boolean moduleStatus,
                                                           @Param("permissionStatus") Boolean permissionStatus);

    @Query("SELECT m FROM Module m INNER JOIN FETCH m.permissions")
    List<Module> getAllModules();
}
