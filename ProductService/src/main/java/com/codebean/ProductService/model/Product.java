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
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "Products")
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SKU", nullable = false, unique = true, updatable = false)
    private String sku;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Price", nullable = false)
    private Double price;

    @Column(name = "Stock", nullable = false)
    private Integer stock;

    @Column(name = "IsActive", columnDefinition = "bit default 1 not null") //ONLY_SQL_SERVER
    private Boolean isActive = true;

    @Column(name = "Description")
    private String description;

    @Version
    private int version;

    @ManyToMany
    @JoinTable(
            name = "ProductCategory",
            joinColumns = @JoinColumn(name = "ProductId"),
            inverseJoinColumns = @JoinColumn(name = "CategoryId")
            , uniqueConstraints = {@UniqueConstraint(name = "Uniq_Product_Category", columnNames = {"ProductId", "CategoryId"})}
    )
    private Set<Category> categories;

    @Transient
    private Set<Long> categoryIds; // temporary category ids

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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", isActive=" + isActive +
                ", description='" + description + '\'' +
                ", categories=" + categoryIds +
                '}';
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void addAllCategory(Collection<Category> categories) {
        this.categories.addAll(categories);
    }
}
