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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @EntityGraph(attributePaths = {"role"})
    List<User> findAllByEmailOrUsername(String email, String username);

    @EntityGraph(attributePaths = {"role", "addresses"})
    Optional<User> findFirstByIdAndIsDelete(Long id, boolean isDelete);

    List<User> findAllByIsDelete(Boolean isDelete);

    @EntityGraph(attributePaths = {"role"})
    Page<User> findAllByIsDelete(Pageable pageable, Boolean isDelete);

    Page<User> findAllByUsernameContainingIgnoreCaseAndIsDelete(Pageable pageable, String username, Boolean isDelete);

    Page<User> findAllByEmailContainingIgnoreCaseAndIsDelete(Pageable pageable, String email, Boolean isDelete);

    @Query("SELECT u FROM User u JOIN u.role r WHERE u.isDelete = :isDelete AND r.name LIKE %:roleName%")
    Page<User> findAllUserByStatusDeleteAndRoleName(@Param("isDelete") Boolean isDelete,
                                                    @Param("roleName") String roleName,
                                                    Pageable pageable);

    @EntityGraph(attributePaths = {"role"})
    Optional<User> findFirstByUsername(String username);
}