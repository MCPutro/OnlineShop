# Online Shop

### Demo : http://185.201.8.139:808
### Gambaran Struktur Projek

```
Online-Shop
│
├── pom.xml
│
├── UserService
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com.codebean.UserService
│   │   │   │       ├── dto
│   │   │   │       ├── model
│   │   │   │       ├── repository
│   │   │   │       ├── controller
│   │   │   │       ├── config
│   │   │   │       ├── filter
│   │   │   │       └── service
│   │   │   └── resources
│   │   │       └── application.properties
│   │   └── test
│   └── .gitignore
│
├── ProductService
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com.codebean.ProductService
│   │   │   │       ├── dto
│   │   │   │       ├── model
│   │   │   │       ├── repository
│   │   │   │       ├── controller
│   │   │   │       ├── config
│   │   │   │       ├── filter
│   │   │   │       └── service
│   │   │   └── resources
│   │   │       └── application.properties
│   │   └── test
│   └── .gitignore
│
├── ShareModule
│   ├── pom.xml
│   ├── src
│   │   └── main
│   │       │── java
│   │       │    └── com.codebean.sharemodule
│   │       │         └── Jwt
│   │       └── resources
│   └── .gitignore
│
├── TransactionService
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com.codebean.transactionservice
│   │   │   │       ├── client
│   │   │   │       ├── dto
│   │   │   │       ├── model
│   │   │   │       ├── repository
│   │   │   │       ├── controller
│   │   │   │       ├── config
│   │   │   │       ├── filter
│   │   │   │       └── service
│   │   │   └── resources
│   │   │       └── application.properties
│   │   └── test
│   └── .gitignore
│
├── AnalyticServices
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com.codebean.AnalyticServices
│   │   │   │       ├── dto
│   │   │   │       ├── controller
│   │   │   │       └── service
│   │   │   └── resources
│   │   │       └── application.properties
│   │   └── test
│   └── .gitignore
│
└── WebsiteUI
    ├── pom.xml
    ├── src
    │   ├── main
    │   │   ├── java
    │   │   │   └── com.codebean.websiteui
    │   │   │       ├── dto
    │   │   │       ├── model
    │   │   │       ├── repository
    │   │   │       ├── controller
    │   │   │       ├── config
    │   │   │       ├── filter
    │   │   │       └── service
    │   │   └── resources
    │   │       ├── static
    │   │       └── templates
    │   │           ├── coba_admin_dashboard.html
    │   │           ├── login.html
    │   │           ├── register.html
    │   │           ├── admin_list_product_category.html
    │   │           ├── admin_list_user.html
    │   │           ├── admin_add_user.html
    │   │           ├── admin_edit_user.html
    │   │           └── admin_view_user.html
    └── .gitignore
```

###     

1. **Proyek Induk (`Online-Shop`)**:
    - Ini adalah proyek utama yang menggabungkan beberapa sub-modul. Ini berisi file `pom.xml` yang mendefinisikan POM
      induk untuk seluruh proyek.

2. **Modul**:
    - **UserService** (8081):
        - Berisi logika bisnis dan akses data terkait manajemen pengguna, role dan permission.

    - **ProductService** (8082):
        - Mengelola operasi terkait produk dan kategory produk.

    - **TransactionService** (8083):
        - Mengelola operasi terkait cart, order/transaksi.

    - **AnalyticServices** (8084):
      - Mengelola proses terkait report.

    - **ShareModule**:
        - berisi utilitas atau komponen yang digunakan bersama di seluruh layanan lainnya. Seperti Jwt

    - **WebsiteUI**:
        - Mengelola komponen antarmuka pengguna dari aplikasi.

### Teknologi yang digunakan
 - Bahasa Pemrograman : Java dengan framework spring boot
 - Java JDK : JDK versi 21
 - Sistem Pengontrol Versi (Kode) : Git
 - Dokumentasi API : Postman
 - Database : MS SQL Server 2019 express
 - IDE : IntelliJ IDEA
 - Keamanan API : JWT
 - Cache Session : redis

### Initial data ada di folder [InitDatabse/Migrate](InitDatabase/Migrate)

### User & Password

Admin
> Username : admin001
>
> Password : 12345678

Customer
> Username : customer001
>
> Password : 12345678

### Postman Collection

untuk testing pake Postman/Insomnia bisa coba import
file [CodeBean-onlineshop.postman_collection.json](CodeBean-onlineshop.postman_collection.json)

### Permission

- hak akses untuk suatu menu, setiap user memiliki relasi 1 to 1 dengan role, 
- role memiliki relasi many to many permissions, relasi dapt di lihat pada table RolePermission.
- untuk list permission ada di table Permissions.
- setaip permission di kelompokan dan module/menu


### table session
```sql
CREATE TABLE session.SPRING_SESSION (
                                PRIMARY_ID CHAR(36) NOT NULL,
                                SESSION_ID CHAR(36) NOT NULL,
                                CREATION_TIME BIGINT NOT NULL,
                                LAST_ACCESS_TIME BIGINT NOT NULL,
                                MAX_INACTIVE_INTERVAL INT NOT NULL,
                                EXPIRY_TIME BIGINT NOT NULL,
                                PRINCIPAL_NAME VARCHAR(100),
                                CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON session.SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON session.SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON session.SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE session.SPRING_SESSION_ATTRIBUTES (
                                           SESSION_PRIMARY_ID CHAR(36) NOT NULL,
                                           ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
                                           ATTRIBUTE_BYTES binary NOT NULL,
                                           CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
                                           CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES session.SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
);

```