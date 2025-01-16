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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "Role")
@EntityListeners(AuditingEntityListener.class)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long ID;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;

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

    public Role() {
    }

    public Role(String name) {
        this.name = name;
        this.setCreatedDate(new Date());
        this.setUpdatedDate(new Date());
    }
}
