package com.codebean.UserService.model;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 11:51
@Last Modified 10 Jan 2025 11:51
Version 1.0
*/

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "DefaultRolePermission", uniqueConstraints = {@UniqueConstraint(columnNames = {"RoleId", "PermissionId"})})
@EntityListeners(AuditingEntityListener.class)
public class RolePermissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "RoleId", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "PermissionId")
    private Permissions permission;

    private Boolean isActive;

    @Column(name = "CreatedBy", updatable = false, nullable = false)
    @CreatedBy
    private String createdBy;

    @Column(name = "CreatedDate", updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "UpdatedBy", insertable = false)
    private String updatedBy;

    @Column(name = "UpdatedDate", insertable = false)
    @LastModifiedDate
    private LocalDateTime updatedDate;
}
