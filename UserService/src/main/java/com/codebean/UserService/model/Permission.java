package com.codebean.UserService.model;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 11:01
@Last Modified 10 Jan 2025 11:01
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
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "Permissions")
@EntityListeners(AuditingEntityListener.class)
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long ID;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;

    @Column(name = "Description")
    private String description;

//    // cara many 2 many
//    @ManyToMany(mappedBy = "permissions")
//    private Set<Role> roles;

    //cara manual
    @OneToMany(mappedBy = "permission")
    private Set<RolePermission> rolePermissions;

    @ManyToOne
    @JoinColumn(name = "ModuleId", referencedColumnName = "ID")  // Kolom yang menghubungkan ke entitas Module
    private Module module;

    @Column(name = "IsActive", columnDefinition = "bit default 1 not null") //ONLY_SQL_SERVER
    private Boolean isActive = true;

    @CreatedBy
    @Column(name = "CreatedBy", updatable = false, nullable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = "CreatedDate", updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "UpdatedBy", insertable = false)
    private String updatedBy;

    @LastModifiedDate
    @Column(name = "UpdatedDate", insertable = false)
    private LocalDateTime updatedDate;

}
