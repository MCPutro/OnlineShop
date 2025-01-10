package com.codebean.UserService.model;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 10 Jan 2025 11:51
@Last Modified 10 Jan 2025 11:51
Version 1.0
*/

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class RolePermissions {


    private Long roleId;

    private Long permissionId;

    @Column(name = "CreatedBy", updatable = false, nullable = false)
    private String createdBy;

    @Column(name = "CreatedDate", updatable = false, nullable = false)
    private Date createdDate = new Date();

    @Column(name = "UpdatedBy", insertable = false)
    private String updatedBy;

    @Column(name = "UpdatedDate", insertable = false)
    private Date updatedDate;
}
