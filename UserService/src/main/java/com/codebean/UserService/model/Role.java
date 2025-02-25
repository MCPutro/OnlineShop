package com.codebean.UserService.model;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 08:02
@Last Modified 10 Jan 2025 08:02
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
@Table(name = "Roles")
@EntityListeners(AuditingEntityListener.class)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;

    @Column(name = "IsActive", columnDefinition = "bit default 1 not null") //ONLY_SQL_SERVER
    private Boolean isActive = true;

//    // cara many 2 many
//    @ManyToMany
//    @JoinTable(
//            name = "RolePermissions",
//            joinColumns = @JoinColumn(name = "RoleId"),
//            inverseJoinColumns = @JoinColumn(name = "PermissionId")
//            , uniqueConstraints = {@UniqueConstraint(name = "Uniq_Role_Permissions", columnNames = {"RoleId", "PermissionId"})}
//    )
//    private Set<Permission> permissions;

    //cara manual
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<RolePermission> permissions;

    @Transient
    private Set<Long> permissionIds; // temporary permission id

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

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }
}