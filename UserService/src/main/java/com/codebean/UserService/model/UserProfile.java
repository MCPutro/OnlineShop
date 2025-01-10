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

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "UserProfile")
public class UserProfile {

    @Id
    @Column(name = "UserId")
    private Long id;

    @OneToOne(mappedBy = "", cascade = CascadeType.ALL)
    @MapsId // Menandakan bahwa userId adalah primary key dan foreign key
    @JoinColumn(name = "UserId", referencedColumnName = "ID") // name = "UserId": Kolom UserId di tabel UserProfile(entity saat ini) adalah foreign key. referencedColumnName = "ID": Kolom ID di tabel users adalah kolom yang dirujuk sebagai primary key.
    private User user;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "DateOfBirth")
    private Date dateOfBirth;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "ProfilePicture")
    private String profilePicture;

    @Column(name = "CreatedBy",updatable = false,nullable = false)
    private String createdBy;

    @Column(name = "CreatedDate",updatable = false,nullable = false)
    private Date createdDate = new Date();

    @Column(name = "UpdatedBy",insertable = false)
    private String updatedBy;

    @Column(name = "UpdatedDate",insertable = false)
    private Date updatedDate;



}

