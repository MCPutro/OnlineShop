/*
 Navicat Premium Data Transfer

 Source Server         : MS SQL Server
 Source Server Type    : SQL Server
 Source Server Version : 15002000 (15.00.2000)
 Source Host           : localhost:1433
 Source Catalog        : OnlineShop
 Source Schema         : Person

 Target Server Type    : SQL Server
 Target Server Version : 15002000 (15.00.2000)
 File Encoding         : 65001

 Date: 14/02/2025 09:06:30
*/


-- ----------------------------
-- Table structure for Modules
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Person].[Modules]') AND type IN ('U'))
	DROP TABLE [Person].[Modules]
GO

CREATE TABLE [Person].[Modules] (
  [ID] bigint  IDENTITY(1,1) NOT NULL,
  [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CreatedDate] datetime2(6)  NOT NULL,
  [Description] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [IsActive] bit DEFAULT 1 NOT NULL,
  [Name] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Path] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [UpdatedDate] datetime2(6)  NULL
)
GO

ALTER TABLE [Person].[Modules] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Modules
-- ----------------------------
SET IDENTITY_INSERT [Person].[Modules] ON
GO

INSERT INTO [Person].[Modules] ([ID], [CreatedBy], [CreatedDate], [Description], [IsActive], [Name], [Path], [UpdatedBy], [UpdatedDate]) VALUES (N'1', N'1', N'2025-02-06 08:49:36.889000', N'user management menu', N'1', N'user management', N'/user-manegement', N'system', N'2025-02-09 22:08:30.001037')
GO

INSERT INTO [Person].[Modules] ([ID], [CreatedBy], [CreatedDate], [Description], [IsActive], [Name], [Path], [UpdatedBy], [UpdatedDate]) VALUES (N'2', N'1', N'2025-02-06 08:49:36.889000', N'product management menu', N'1', N'product management', N'/product-manegement', N'system', N'2025-02-09 22:19:34.002852')
GO

INSERT INTO [Person].[Modules] ([ID], [CreatedBy], [CreatedDate], [Description], [IsActive], [Name], [Path], [UpdatedBy], [UpdatedDate]) VALUES (N'3', N'1', N'2025-02-06 08:49:36.889000', N'trx management menu', N'1', N'transaction management', N'/trx-manegement', N'system', N'2025-02-09 22:07:40.161418')
GO

INSERT INTO [Person].[Modules] ([ID], [CreatedBy], [CreatedDate], [Description], [IsActive], [Name], [Path], [UpdatedBy], [UpdatedDate]) VALUES (N'4', N'1', N'2025-02-06 08:49:36.889000', N'role management menu', N'1', N'role management', N'/role-manegement', N'system', N'2025-02-09 22:05:36.108148')
GO

INSERT INTO [Person].[Modules] ([ID], [CreatedBy], [CreatedDate], [Description], [IsActive], [Name], [Path], [UpdatedBy], [UpdatedDate]) VALUES (N'5', N'1', N'2025-02-09 13:59:44.000000', N'Show product to Shop', N'1', N'Shop product', N'/shop', N'system', N'2025-02-09 22:19:34.008875')
GO

SET IDENTITY_INSERT [Person].[Modules] OFF
GO


-- ----------------------------
-- Table structure for Permissions
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Person].[Permissions]') AND type IN ('U'))
	DROP TABLE [Person].[Permissions]
GO

CREATE TABLE [Person].[Permissions] (
  [ID] bigint  IDENTITY(1,1) NOT NULL,
  [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CreatedDate] datetime2(6)  NOT NULL,
  [Description] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [IsActive] bit DEFAULT 1 NOT NULL,
  [Name] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [UpdatedDate] datetime2(6)  NULL,
  [ModuleId] bigint  NULL
)
GO

ALTER TABLE [Person].[Permissions] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Permissions
-- ----------------------------
SET IDENTITY_INSERT [Person].[Permissions] ON
GO

INSERT INTO [Person].[Permissions] ([ID], [CreatedBy], [CreatedDate], [Description], [IsActive], [Name], [UpdatedBy], [UpdatedDate], [ModuleId]) VALUES (N'1', N'1', N'2025-02-06 08:50:07.051000', N'View user', N'1', N'VIEW_USER', NULL, NULL, N'1')
GO

INSERT INTO [Person].[Permissions] ([ID], [CreatedBy], [CreatedDate], [Description], [IsActive], [Name], [UpdatedBy], [UpdatedDate], [ModuleId]) VALUES (N'2', N'1', N'2025-02-06 08:50:07.051000', N'Add/Edit user', N'1', N'MANAGE_USER', NULL, NULL, N'1')
GO

INSERT INTO [Person].[Permissions] ([ID], [CreatedBy], [CreatedDate], [Description], [IsActive], [Name], [UpdatedBy], [UpdatedDate], [ModuleId]) VALUES (N'3', N'1', N'2025-02-06 08:50:07.051000', N'View product & category', N'1', N'VIEW_PRODUCT', NULL, NULL, N'2')
GO

INSERT INTO [Person].[Permissions] ([ID], [CreatedBy], [CreatedDate], [Description], [IsActive], [Name], [UpdatedBy], [UpdatedDate], [ModuleId]) VALUES (N'4', N'1', N'2025-02-06 08:50:07.051000', N'Add/Edit product & category', N'1', N'MANAGE_PRODUCT', NULL, NULL, N'2')
GO

INSERT INTO [Person].[Permissions] ([ID], [CreatedBy], [CreatedDate], [Description], [IsActive], [Name], [UpdatedBy], [UpdatedDate], [ModuleId]) VALUES (N'5', N'1', N'2025-02-06 08:50:07.051000', N'View transactions', N'1', N'VIEW_TRX', NULL, NULL, N'3')
GO

INSERT INTO [Person].[Permissions] ([ID], [CreatedBy], [CreatedDate], [Description], [IsActive], [Name], [UpdatedBy], [UpdatedDate], [ModuleId]) VALUES (N'6', N'1', N'2025-02-06 08:50:07.051000', N'Add/Edit transactions', N'1', N'MANAGE_TRX', NULL, NULL, N'3')
GO

INSERT INTO [Person].[Permissions] ([ID], [CreatedBy], [CreatedDate], [Description], [IsActive], [Name], [UpdatedBy], [UpdatedDate], [ModuleId]) VALUES (N'7', N'1', N'2025-02-06 08:50:07.051000', N'View Role list', N'1', N'VIEW_ROLE', NULL, NULL, N'4')
GO

INSERT INTO [Person].[Permissions] ([ID], [CreatedBy], [CreatedDate], [Description], [IsActive], [Name], [UpdatedBy], [UpdatedDate], [ModuleId]) VALUES (N'8', N'1', N'2025-02-06 08:50:07.051000', N'Add/Edit Role', N'1', N'MANAGE_ROLE', NULL, NULL, N'4')
GO

INSERT INTO [Person].[Permissions] ([ID], [CreatedBy], [CreatedDate], [Description], [IsActive], [Name], [UpdatedBy], [UpdatedDate], [ModuleId]) VALUES (N'9', N'1', N'2025-02-09 14:01:22.000000', N'Show Product to Customer', N'1', N'SHOP', NULL, NULL, N'5')
GO

SET IDENTITY_INSERT [Person].[Permissions] OFF
GO


-- ----------------------------
-- Table structure for RolePermissions
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Person].[RolePermissions]') AND type IN ('U'))
	DROP TABLE [Person].[RolePermissions]
GO

CREATE TABLE [Person].[RolePermissions] (
  [ID] bigint  IDENTITY(1,1) NOT NULL,
  [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CreatedDate] datetime2(6)  NOT NULL,
  [IsActive] bit DEFAULT 1 NOT NULL,
  [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [UpdatedDate] datetime2(6)  NULL,
  [PermissionId] bigint  NOT NULL,
  [RoleId] bigint  NOT NULL
)
GO

ALTER TABLE [Person].[RolePermissions] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of RolePermissions
-- ----------------------------
SET IDENTITY_INSERT [Person].[RolePermissions] ON
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'1', N'1', N'2025-02-09 22:45:56.000000', N'1', NULL, NULL, N'1', N'1')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'2', N'1', N'2025-02-09 22:45:56.000000', N'1', NULL, NULL, N'2', N'1')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'3', N'1', N'2025-02-09 22:45:56.000000', N'1', NULL, NULL, N'3', N'1')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'4', N'1', N'2025-02-09 22:45:56.000000', N'1', NULL, NULL, N'4', N'1')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'5', N'1', N'2025-02-09 22:45:56.000000', N'1', NULL, NULL, N'7', N'1')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'6', N'1', N'2025-02-09 22:45:56.000000', N'1', NULL, NULL, N'8', N'1')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'7', N'1', N'2025-02-09 22:45:56.000000', N'1', NULL, NULL, N'9', N'2')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'8', N'admin001', N'2025-02-10 20:36:12.921279', N'0', N'admin001', N'2025-02-10 20:37:12.917128', N'1', N'3')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'9', N'admin001', N'2025-02-10 20:36:12.929225', N'0', N'admin001', N'2025-02-10 20:37:12.918125', N'2', N'3')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'10', N'admin001', N'2025-02-10 20:36:12.933214', N'0', N'admin001', N'2025-02-10 20:37:12.918125', N'3', N'3')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'11', N'admin001', N'2025-02-10 20:37:12.904163', N'1', NULL, NULL, N'7', N'3')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'12', N'admin001', N'2025-02-10 20:37:12.907156', N'1', NULL, NULL, N'9', N'3')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'13', N'admin001', N'2025-02-10 20:38:34.893825', N'0', N'admin001', N'2025-02-10 20:40:03.215238', N'1', N'4')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'14', N'admin001', N'2025-02-10 20:38:34.898806', N'0', N'admin001', N'2025-02-10 20:40:03.215238', N'2', N'4')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'15', N'admin001', N'2025-02-10 20:38:34.901801', N'1', N'admin001', N'2025-02-10 20:47:31.015613', N'3', N'4')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'16', N'admin001', N'2025-02-10 20:38:34.904794', N'1', N'admin001', N'2025-02-10 20:47:31.060492', N'4', N'4')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'17', N'admin001', N'2025-02-10 20:38:34.907783', N'1', N'admin001', N'2025-02-10 20:47:31.070466', N'5', N'4')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'18', N'admin001', N'2025-02-10 20:40:03.197288', N'1', NULL, NULL, N'6', N'4')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'19', N'admin001', N'2025-02-10 20:40:03.205265', N'0', N'admin001', N'2025-02-10 20:47:31.122327', N'7', N'4')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'20', N'admin001', N'2025-02-10 20:40:03.208255', N'0', N'admin001', N'2025-02-10 20:47:31.123328', N'8', N'4')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'21', N'admin001', N'2025-02-10 20:40:03.211247', N'0', N'admin001', N'2025-02-10 20:47:31.123328', N'9', N'4')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'22', N'1', N'2025-02-11 22:28:24.801000', N'1', NULL, NULL, N'9', N'1')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'23', N'1', N'2025-02-11 22:28:24.801000', N'1', NULL, NULL, N'6', N'1')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'24', N'1', N'2025-02-11 22:28:24.801000', N'1', NULL, NULL, N'5', N'1')
GO

SET IDENTITY_INSERT [Person].[RolePermissions] OFF
GO


-- ----------------------------
-- Table structure for Roles
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Person].[Roles]') AND type IN ('U'))
	DROP TABLE [Person].[Roles]
GO

CREATE TABLE [Person].[Roles] (
  [ID] bigint  IDENTITY(1,1) NOT NULL,
  [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CreatedDate] datetime2(6)  NOT NULL,
  [IsActive] bit DEFAULT 1 NOT NULL,
  [Name] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [UpdatedDate] datetime2(6)  NULL
)
GO

ALTER TABLE [Person].[Roles] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Roles
-- ----------------------------
SET IDENTITY_INSERT [Person].[Roles] ON
GO

INSERT INTO [Person].[Roles] ([ID], [CreatedBy], [CreatedDate], [IsActive], [Name], [UpdatedBy], [UpdatedDate]) VALUES (N'1', N'1', N'2025-02-09 22:36:20.000000', N'1', N'Admin', NULL, NULL)
GO

INSERT INTO [Person].[Roles] ([ID], [CreatedBy], [CreatedDate], [IsActive], [Name], [UpdatedBy], [UpdatedDate]) VALUES (N'2', N'1', N'2025-02-09 22:36:20.000000', N'1', N'Customer', NULL, NULL)
GO

INSERT INTO [Person].[Roles] ([ID], [CreatedBy], [CreatedDate], [IsActive], [Name], [UpdatedBy], [UpdatedDate]) VALUES (N'3', N'admin001', N'2025-02-10 20:36:12.822006', N'1', N'tukang cuci kaki', N'admin001', N'2025-02-10 20:37:12.914136')
GO

INSERT INTO [Person].[Roles] ([ID], [CreatedBy], [CreatedDate], [IsActive], [Name], [UpdatedBy], [UpdatedDate]) VALUES (N'4', N'admin001', N'2025-02-10 20:38:34.888832', N'1', N'tukang baso', N'admin001', N'2025-02-10 20:41:34.379551')
GO

SET IDENTITY_INSERT [Person].[Roles] OFF
GO


-- ----------------------------
-- Table structure for UserAddress
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Person].[UserAddress]') AND type IN ('U'))
	DROP TABLE [Person].[UserAddress]
GO

CREATE TABLE [Person].[UserAddress] (
  [ID] bigint  IDENTITY(1,1) NOT NULL,
  [Address] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Country] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CreatedDate] datetime2(6)  NOT NULL,
  [IsActive] bit DEFAULT 1 NOT NULL,
  [Name] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [PostalCode] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [UpdatedDate] datetime2(6)  NULL,
  [UserId] bigint  NULL
)
GO

ALTER TABLE [Person].[UserAddress] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of UserAddress
-- ----------------------------
SET IDENTITY_INSERT [Person].[UserAddress] ON
GO

SET IDENTITY_INSERT [Person].[UserAddress] OFF
GO


-- ----------------------------
-- Table structure for Users
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Person].[Users]') AND type IN ('U'))
	DROP TABLE [Person].[Users]
GO

CREATE TABLE [Person].[Users] (
  [ID] bigint  IDENTITY(1,1) NOT NULL,
  [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CreatedDate] datetime2(6)  NOT NULL,
  [DateOfBirth] date  NULL,
  [Email] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Gender] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [IsActive] bit DEFAULT 1 NOT NULL,
  [IsDelete] bit DEFAULT 0 NOT NULL,
  [Name] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [Password] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [PhoneNumber] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [ProfilePicture] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [UpdatedDate] datetime2(6)  NULL,
  [Username] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [RoleId] bigint  NOT NULL
)
GO

ALTER TABLE [Person].[Users] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Users
-- ----------------------------
SET IDENTITY_INSERT [Person].[Users] ON
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'1', N'system', N'2025-02-10 11:47:42.093936', NULL, N'admin@001.com', NULL, N'1', N'0', NULL, N'$2a$10$JCJ0QT5IjGtutfynWZVRruSANgX8ZH58Zk5oDwqmrrxg4JzN7R0zS', NULL, NULL, NULL, N'2025-02-10 11:47:42.093936', N'admin001', N'1')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'2', N'system', N'2025-02-10 20:18:24.339528', NULL, N'customer@com.001', NULL, N'1', N'0', NULL, N'$2a$10$ebVC1rlWpo1YGh7c.358NeI..txojLEiN.jIJPZ6.4v5kTqjPGht.', NULL, NULL, NULL, N'2025-02-10 20:18:24.339528', N'customer001', N'2')
GO

SET IDENTITY_INSERT [Person].[Users] OFF
GO


-- ----------------------------
-- Auto increment value for Modules
-- ----------------------------
DBCC CHECKIDENT ('[Person].[Modules]', RESEED, 5)
GO


-- ----------------------------
-- Uniques structure for table Modules
-- ----------------------------
ALTER TABLE [Person].[Modules] ADD CONSTRAINT [UKala8wgyjoem3c738i753hlypy] UNIQUE NONCLUSTERED ([Name] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

ALTER TABLE [Person].[Modules] ADD CONSTRAINT [UKhpvu99off5vdbxdh3mlfqfy81] UNIQUE NONCLUSTERED ([Path] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table Modules
-- ----------------------------
ALTER TABLE [Person].[Modules] ADD CONSTRAINT [PK__Modules__3214EC2772B0E8C0] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for Permissions
-- ----------------------------
DBCC CHECKIDENT ('[Person].[Permissions]', RESEED, 9)
GO


-- ----------------------------
-- Uniques structure for table Permissions
-- ----------------------------
ALTER TABLE [Person].[Permissions] ADD CONSTRAINT [UK54ynq1djisa8gsgfsyh6mridx] UNIQUE NONCLUSTERED ([Name] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table Permissions
-- ----------------------------
ALTER TABLE [Person].[Permissions] ADD CONSTRAINT [PK__Permissi__3214EC273263635E] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for RolePermissions
-- ----------------------------
DBCC CHECKIDENT ('[Person].[RolePermissions]', RESEED, 24)
GO


-- ----------------------------
-- Uniques structure for table RolePermissions
-- ----------------------------
ALTER TABLE [Person].[RolePermissions] ADD CONSTRAINT [Unique_Role_Permission] UNIQUE NONCLUSTERED ([RoleId] ASC, [PermissionId] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table RolePermissions
-- ----------------------------
ALTER TABLE [Person].[RolePermissions] ADD CONSTRAINT [PK__RolePerm__3214EC27BBA756A3] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for Roles
-- ----------------------------
DBCC CHECKIDENT ('[Person].[Roles]', RESEED, 4)
GO


-- ----------------------------
-- Uniques structure for table Roles
-- ----------------------------
ALTER TABLE [Person].[Roles] ADD CONSTRAINT [UK7321kxrp9y29l51cb9k6kxi2u] UNIQUE NONCLUSTERED ([Name] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table Roles
-- ----------------------------
ALTER TABLE [Person].[Roles] ADD CONSTRAINT [PK__Roles__3214EC276084740D] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for UserAddress
-- ----------------------------
DBCC CHECKIDENT ('[Person].[UserAddress]', RESEED, 1)
GO


-- ----------------------------
-- Primary Key structure for table UserAddress
-- ----------------------------
ALTER TABLE [Person].[UserAddress] ADD CONSTRAINT [PK__UserAddr__3214EC27A3A33198] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for Users
-- ----------------------------
DBCC CHECKIDENT ('[Person].[Users]', RESEED, 2)
GO


-- ----------------------------
-- Uniques structure for table Users
-- ----------------------------
ALTER TABLE [Person].[Users] ADD CONSTRAINT [UK9cw87ffd4i55ki0qpkwu63er] UNIQUE NONCLUSTERED ([Username] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

ALTER TABLE [Person].[Users] ADD CONSTRAINT [UKjdfr6kjrxekx1j5vrr77rp44t] UNIQUE NONCLUSTERED ([Email] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table Users
-- ----------------------------
ALTER TABLE [Person].[Users] ADD CONSTRAINT [PK__Users__3214EC27C4326030] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Foreign Keys structure for table Permissions
-- ----------------------------
ALTER TABLE [Person].[Permissions] ADD CONSTRAINT [FKhohav06qivbri89ym3r2da5p2] FOREIGN KEY ([ModuleId]) REFERENCES [Person].[Modules] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table RolePermissions
-- ----------------------------
ALTER TABLE [Person].[RolePermissions] ADD CONSTRAINT [FK12xeknp16lyyj2qaajtj5i2jt] FOREIGN KEY ([PermissionId]) REFERENCES [Person].[Permissions] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [Person].[RolePermissions] ADD CONSTRAINT [FKbjxtmsnf1y97h4lg1u1hnirgj] FOREIGN KEY ([RoleId]) REFERENCES [Person].[Roles] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table UserAddress
-- ----------------------------
ALTER TABLE [Person].[UserAddress] ADD CONSTRAINT [FK_Address_User] FOREIGN KEY ([UserId]) REFERENCES [Person].[Users] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table Users
-- ----------------------------
ALTER TABLE [Person].[Users] ADD CONSTRAINT [FK_User_Role] FOREIGN KEY ([RoleId]) REFERENCES [Person].[Roles] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

