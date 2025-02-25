package com.codebean.UserService.model;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 07:33
@Last Modified 10 Jan 2025 07:33
Version 1.0
*/

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Setter
@Getter
@Entity
@Table(name = "Users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Username", unique = true, nullable = false, updatable = false)
    private String username;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Email", unique = true, nullable = false)
    private String email;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "IsActive", columnDefinition = "bit default 1 not null") //ONLY_SQL_SERVER
    private Boolean isActive = true;

    @Column(name = "IsDelete", columnDefinition = "bit default 0 not null") //ONLY_SQL_SERVER
    private Boolean isDelete = false;

    @ManyToOne
    @JoinColumn(name = "RoleId", referencedColumnName = "ID", nullable = false, foreignKey = @ForeignKey(name = "FK_User_Role"))
    private Role role;

    @Column(name = "Name")
    private String name;

    @Column(name = "DateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "ProfilePicture")
    private String profilePicture;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // cascade di sini maksud nya akan melakukan update data di table user address juga
    private List<UserAddress> addresses; // Daftar alamat yang dimiliki user, 1 user bisa punya banyak alamat, lihat dari sisi class user

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
    @Column(name = "UpdatedDate")
    private LocalDateTime updatedDate;

    public void addAddress(UserAddress address) {
        this.addresses.add(address);
        address.setUser(this);
    }

}

