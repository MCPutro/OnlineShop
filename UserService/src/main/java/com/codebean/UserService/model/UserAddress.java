package com.codebean.UserService.model;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 07:34
@Last Modified 10 Jan 2025 07:34
Version 1.0
*/

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UserAddress")
@EntityListeners(AuditingEntityListener.class)
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_UserAddress_User"))
    // Kolom userId pada tabel UserAddress(saat ini) akan menyimpan referensi ke ID user, Parameter referencedColumnName dalam anotasi @JoinColumn digunakan untuk menentukan nama kolom yang akan dirujuk oleh foreign key di entitas yang sedang didefinisikan
    private User user; // beberapa alamat bisa di milikik oleh user yang sama, jadi relasinya banyak alamat menuju ke 1 user

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "Country", nullable = false)
    private String country;

    @Column(name = "PostalCode", nullable = false)
    private String postalCode;

    @Column(name = "CreatedBy", updatable = false, nullable = false)
    private String createdBy;

    @Column(name = "CreatedDate", updatable = false, nullable = false)
    @CreatedDate
    private Date createdDate;

    @Column(name = "UpdatedBy", insertable = false)
    private String updatedBy;

    @Column(name = "UpdatedDate", insertable = false)
    @LastModifiedDate
    private Date updatedDate;
}
