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

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long ID;

    @Column(name = "Username", unique = true, nullable = false)
    private String username;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Email", unique = true, nullable = false)
    private String email;

    @Column(name = "PhoneNumber", unique = true)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "RoleId", referencedColumnName = "ID", nullable = false)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserProfile userProfile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // cascade di sini maksud nya akan melakukan update data di table user address juga
    private List<UserAddress> addresses; // Daftar alamat yang dimiliki user, 1 user bisa punya banyak alamat, lihat dari sisi class user

    @Column(name = "CreatedBy", updatable = false, nullable = false)
    private String createdBy;

    @Column(name = "CreatedDate", updatable = false, nullable = false)
    private Date createdDate = new Date();

    @Column(name = "UpdatedBy", insertable = false)
    private String updatedBy;

    @Column(name = "UpdatedDate", insertable = false)
    private Date updatedDate;

    @Column(name = "DeletedAt")
    private Date deletedAt;

    @ManyToMany
    @JoinTable(
            name = "UserPermissions",
//            uniqueConstraints = {@UniqueConstraint(name = "uniq_user_access",columnNames = {"UserId","AccessId"})},
            joinColumns = @JoinColumn(name = "UserId"),
            inverseJoinColumns = @JoinColumn(name = "AccessId")
            , uniqueConstraints = {@UniqueConstraint(name = "Uniq_User_Permissions", columnNames = {"UserId", "AccessId"})}
    )
    private List<Permissions> listPermission;

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                ", deletedAt=" + deletedAt +
                ", userProfile=" + userProfile +
                ", addresses=" + addresses +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedDate=" + updatedDate +
                ", listPermission=" + listPermission +
                '}';
    }
}

