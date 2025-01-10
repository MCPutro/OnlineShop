package com.codebean.UserService.repository;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 08:14
@Last Modified 10 Jan 2025 08:14
Version 1.0
*/

import com.codebean.UserService.model.UserProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {
}
