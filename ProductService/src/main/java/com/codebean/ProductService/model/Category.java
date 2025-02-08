package com.codebean.ProductService.model;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Jan 2025 22:00
@Last Modified 13 Jan 2025 22:00
Version 1.0
*/

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Categories")
@EntityListeners(AuditingEntityListener.class)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Name", unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products;

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

    public Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
