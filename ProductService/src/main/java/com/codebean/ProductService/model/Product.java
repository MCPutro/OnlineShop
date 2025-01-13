package com.codebean.ProductService.model;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 13 Jan 2025 21:58
@Last Modified 13 Jan 2025 21:58
Version 1.0
*/

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Price", nullable = false)
    private Double price;

    @Column(name = "Stock", nullable = false)
    private Integer stock;

    @Column(name = "IsActive", nullable = false)
    private Boolean isActive;

    @Column(name = "Description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "ProductCategory",
//            uniqueConstraints = {@UniqueConstraint(name = "uniq_user_access",columnNames = {"UserId","AccessId"})},
            joinColumns = @JoinColumn(name = "ProductId"),
            inverseJoinColumns = @JoinColumn(name = "CategoryId")
            , uniqueConstraints = {@UniqueConstraint(name = "Uniq_Product_Category", columnNames = {"ProductId", "CategoryId"})}
    )
    private List<Category> categories;

    @Column(name = "CreatedBy", updatable = false, nullable = false)
    private String createdBy;

    @Column(name = "CreatedDate", updatable = false, nullable = false)
    private Date createdDate = new Date();

    @Column(name = "UpdatedBy", insertable = false)
    private String updatedBy;

    @Column(name = "UpdatedDate", insertable = false)
    private Date updatedDate;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", isActive=" + isActive +
                ", description='" + description + '\'' +
//                ", categories=" + categories +
                '}';
    }
}
