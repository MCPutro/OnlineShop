/*
 Navicat Premium Dump SQL

 Source Server         : SQL Server
 Source Server Type    : SQL Server
 Source Server Version : 15004430 (15.00.4430)
 Source Host           : localhost:1433
 Source Catalog        : OnlineShop
 Source Schema         : Person

 Target Server Type    : SQL Server
 Target Server Version : 15004430 (15.00.4430)
 File Encoding         : 65001

 Date: 07/03/2025 10:01:08
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

INSERT INTO [Person].[Modules] ([ID], [CreatedBy], [CreatedDate], [Description], [IsActive], [Name], [Path], [UpdatedBy], [UpdatedDate]) VALUES (N'1', N'1', N'2025-02-06 08:49:36.889000', N'user management menu', N'1', N'user management', N'/user-management', N'system', N'2025-02-09 22:08:30.001037')
GO

INSERT INTO [Person].[Modules] ([ID], [CreatedBy], [CreatedDate], [Description], [IsActive], [Name], [Path], [UpdatedBy], [UpdatedDate]) VALUES (N'2', N'1', N'2025-02-06 08:49:36.889000', N'product management menu', N'1', N'product management', N'/product-management', N'system', N'2025-02-09 22:19:34.002852')
GO

INSERT INTO [Person].[Modules] ([ID], [CreatedBy], [CreatedDate], [Description], [IsActive], [Name], [Path], [UpdatedBy], [UpdatedDate]) VALUES (N'3', N'1', N'2025-02-06 08:49:36.889000', N'trx management menu', N'1', N'transaction management', N'/trx-management', N'system', N'2025-02-09 22:07:40.161418')
GO

INSERT INTO [Person].[Modules] ([ID], [CreatedBy], [CreatedDate], [Description], [IsActive], [Name], [Path], [UpdatedBy], [UpdatedDate]) VALUES (N'4', N'1', N'2025-02-06 08:49:36.889000', N'role management menu', N'1', N'role management', N'/role-management', N'system', N'2025-02-09 22:05:36.108148')
GO

INSERT INTO [Person].[Modules] ([ID], [CreatedBy], [CreatedDate], [Description], [IsActive], [Name], [Path], [UpdatedBy], [UpdatedDate]) VALUES (N'5', N'1', N'2025-02-09 13:59:44.000000', N'Show product to Shop', N'1', N'Shop', N'/shop', N'system', N'2025-02-09 22:19:34.008875')
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

INSERT INTO [Person].[Permissions] ([ID], [CreatedBy], [CreatedDate], [Description], [IsActive], [Name], [UpdatedBy], [UpdatedDate], [ModuleId]) VALUES (N'9', N'1', N'2025-02-09 14:01:22.000000', N'View Product to Customer', N'1', N'SHOP', NULL, NULL, N'5')
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

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'10', N'admin001', N'2025-02-10 20:36:12.933214', N'1', N'admin001', N'2025-03-06 22:39:55.052596', N'3', N'3')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'11', N'admin001', N'2025-02-10 20:37:12.904163', N'0', N'admin001', N'2025-03-06 22:39:55.115053', N'7', N'3')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'12', N'admin001', N'2025-02-10 20:37:12.907156', N'0', N'admin001', N'2025-03-06 22:39:55.115053', N'9', N'3')
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

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'25', N'1', N'2025-02-09 22:45:56.000000', N'0', N'admin001', N'2025-03-06 18:41:32.091403', N'5', N'2')
GO

INSERT INTO [Person].[RolePermissions] ([ID], [CreatedBy], [CreatedDate], [IsActive], [UpdatedBy], [UpdatedDate], [PermissionId], [RoleId]) VALUES (N'10025', N'admin001', N'2025-03-06 22:39:55.082247', N'1', NULL, NULL, N'4', N'3')
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

INSERT INTO [Person].[Roles] ([ID], [CreatedBy], [CreatedDate], [IsActive], [Name], [UpdatedBy], [UpdatedDate]) VALUES (N'3', N'admin001', N'2025-02-10 20:36:12.822006', N'0', N'Staff', N'admin001', N'2025-03-06 22:39:36.840086')
GO

INSERT INTO [Person].[Roles] ([ID], [CreatedBy], [CreatedDate], [IsActive], [Name], [UpdatedBy], [UpdatedDate]) VALUES (N'4', N'admin001', N'2025-02-10 20:38:34.888832', N'0', N'Staff 2', N'admin001', N'2025-02-10 20:41:34.379551')
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
  [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CreatedDate] datetime2(6)  NOT NULL,
  [IsActive] bit DEFAULT 1 NOT NULL,
  [Name] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [PostalCode] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [UpdatedDate] datetime2(6)  NULL,
  [UserId] bigint  NULL,
  [Province] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Regency] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL
)
GO

ALTER TABLE [Person].[UserAddress] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of UserAddress
-- ----------------------------
SET IDENTITY_INSERT [Person].[UserAddress] ON
GO

INSERT INTO [Person].[UserAddress] ([ID], [Address], [CreatedBy], [CreatedDate], [IsActive], [Name], [PostalCode], [UpdatedBy], [UpdatedDate], [UserId], [Province], [Regency]) VALUES (N'9', N'di lapangan bola', N'admin001', N'2025-03-06 18:51:25.156199', N'1', N'lapangan bola', N'32145ad', NULL, N'2025-03-06 18:51:25.156199', N'1', N'disono', N'disono')
GO

INSERT INTO [Person].[UserAddress] ([ID], [Address], [CreatedBy], [CreatedDate], [IsActive], [Name], [PostalCode], [UpdatedBy], [UpdatedDate], [UserId], [Province], [Regency]) VALUES (N'10', N'Asrama Yon Armed 9
Ciseureuh', N'customer001', N'2025-03-06 18:51:29.020188', N'1', N'Asrama Yon Armed 9', N'41118', NULL, N'2025-03-06 18:51:29.020188', N'2', N'Jawa Barat', N'purwakarta')
GO

INSERT INTO [Person].[UserAddress] ([ID], [Address], [CreatedBy], [CreatedDate], [IsActive], [Name], [PostalCode], [UpdatedBy], [UpdatedDate], [UserId], [Province], [Regency]) VALUES (N'11', N'gedung tua', N'customer001', N'2025-03-06 20:42:58.234201', N'1', N'gedung tua', N'123465', NULL, N'2025-03-06 20:42:58.234201', N'2', N'jatim', N'jabar')
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

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'1', N'system', N'2025-02-10 11:47:42.093936', NULL, N'admin@001.com', NULL, N'1', N'0', N'akun admin', N'$2a$10$JCJ0QT5IjGtutfynWZVRruSANgX8ZH58Zk5oDwqmrrxg4JzN7R0zS', NULL, N'https://gratisography.com/wp-content/uploads/2024/11/gratisography-augmented-reality-800x525.jpg', NULL, N'2025-02-10 11:47:42.093936', N'admin001', N'1')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'2', N'system', N'2025-02-10 20:18:24.339528', N'2025-03-06', N'customer@com.001', N'Laki - laki', N'1', N'0', N'contoh customer', N'$2a$10$ebVC1rlWpo1YGh7c.358NeI..txojLEiN.jIJPZ6.4v5kTqjPGht.', N'087778960669', N'https://gratisography.com/wp-content/uploads/2024/11/gratisography-augmented-reality-800x525.jpg', N'customer001', N'2025-03-06 18:44:17.238402', N'customer001', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'101', N'system', N'2025-03-05 18:36:14.679113', N'2025-03-06', N'customer@com.3', N'Perempuan', N'0', N'0', N'NAME-customer003', N'$2a$10$DoE0pw7Fvg14MTpUoOSndejv10rrmPjYGGHQPY2YcZsr6TGQfk3cK', N'12313', NULL, N'admin001', N'2025-03-06 22:36:01.534227', N'customer003', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'102', N'system', N'2025-03-05 18:36:14.898226', NULL, N'customer@com.4', NULL, N'1', N'0', N'NAME-customer004', N'$2a$10$q0NtZ7lQPUuy0X3YKf1PIeKjZb1AX/SBxnsFb//HSEavXZ09ig7pC', NULL, NULL, NULL, N'2025-03-05 18:36:14.898226', N'customer004', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'103', N'system', N'2025-03-05 18:36:15.093669', NULL, N'customer@com.5', NULL, N'1', N'0', N'NAME-customer005', N'$2a$10$ORnBZMDvoietJH4To59WVO/JPwGCZbPMmISHjutV9nv8RrTi1KWYy', NULL, NULL, NULL, N'2025-03-05 18:36:15.093669', N'customer005', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'104', N'system', N'2025-03-05 18:36:15.309993', NULL, N'customer@com.6', NULL, N'1', N'0', N'NAME-customer006', N'$2a$10$/xC//1k6agCXocE8rotPnONr9K1qT.s8/sNon6OyO9mU3b6YEbC1C', NULL, NULL, NULL, N'2025-03-05 18:36:15.309993', N'customer006', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'105', N'system', N'2025-03-05 18:36:15.495438', NULL, N'customer@com.7', NULL, N'1', N'0', N'NAME-customer007', N'$2a$10$fLaUiFSTvWOR7eFC0huIPe70e9cInSw2doCVFNmt0.APzu0r7AxU6', NULL, NULL, NULL, N'2025-03-05 18:36:15.495438', N'customer007', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'106', N'system', N'2025-03-05 18:36:15.642035', NULL, N'customer@com.8', NULL, N'1', N'0', N'NAME-customer008', N'$2a$10$jhKhlzI4QhfUJ.Kg7ysPceU7gm4RrdORXB9vMitFGmlT3/dfieNCa', NULL, NULL, NULL, N'2025-03-05 18:36:15.642035', N'customer008', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'107', N'system', N'2025-03-05 18:36:15.790295', NULL, N'customer@com.9', NULL, N'1', N'0', N'NAME-customer009', N'$2a$10$yMrYBcRrexMypbJOkEAqaON18jiZH8WoMnL6WpP3K6zBbmGgtC2/q', NULL, NULL, NULL, N'2025-03-05 18:36:15.790295', N'customer009', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'108', N'system', N'2025-03-05 18:36:15.935804', NULL, N'customer@com.10', NULL, N'1', N'0', N'NAME-customer0010', N'$2a$10$TQFMz0WtbtBwf2SEOL79aeQPzdOxZkWfjCiBovwfcd3zG5HGxdhmO', NULL, NULL, NULL, N'2025-03-05 18:36:15.935804', N'customer0010', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'109', N'system', N'2025-03-05 18:36:16.085571', NULL, N'customer@com.11', NULL, N'1', N'0', N'NAME-customer0011', N'$2a$10$Qio0yLZlMz4Eh7QIDTE79OXmtef4ZVl/ReD8C5GLmQzlFN5nP42su', NULL, NULL, NULL, N'2025-03-05 18:36:16.085571', N'customer0011', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'110', N'system', N'2025-03-05 18:36:16.277947', NULL, N'customer@com.12', NULL, N'1', N'0', N'NAME-customer0012', N'$2a$10$9n4F39MzFGEtoZ9Y.aJRheW1u76yTfyM56QkLSk0rGgSFoiRBvsRO', NULL, NULL, NULL, N'2025-03-05 18:36:16.277947', N'customer0012', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'111', N'system', N'2025-03-05 18:36:16.477732', NULL, N'customer@com.13', NULL, N'1', N'0', N'NAME-customer0013', N'$2a$10$MXrPD1Es59oydt5OBZeKk.X7OFicgzXV9cSxrqjknW8HT.bZ.dABC', NULL, NULL, NULL, N'2025-03-05 18:36:16.477732', N'customer0013', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'112', N'system', N'2025-03-05 18:36:16.627558', NULL, N'customer@com.14', NULL, N'1', N'0', N'NAME-customer0014', N'$2a$10$abQn2zyo7ferFDu05SKuu.dQ2r6dforKCS2Gh1YhUdts5e1KlP.oq', NULL, NULL, NULL, N'2025-03-05 18:36:16.627558', N'customer0014', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'113', N'system', N'2025-03-05 18:36:16.780623', NULL, N'customer@com.15', NULL, N'1', N'0', N'NAME-customer0015', N'$2a$10$I2tA2.TseVhF/dplHJhcZutjFvajPVVu6TxYhOBEE97iQnHuDO4Tu', NULL, NULL, NULL, N'2025-03-05 18:36:16.780623', N'customer0015', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'114', N'system', N'2025-03-05 18:36:16.970041', NULL, N'customer@com.16', NULL, N'1', N'0', N'NAME-customer0016', N'$2a$10$4YYtTFf.0d4gkLexx7Y4K.mvi1wtZXpc2jYXgxgbSu.gEFU.0.np6', NULL, NULL, NULL, N'2025-03-05 18:36:16.970041', N'customer0016', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'115', N'system', N'2025-03-05 18:36:17.115866', NULL, N'customer@com.17', NULL, N'1', N'0', N'NAME-customer0017', N'$2a$10$Y.YcfXQUUIF8YROB3eU5zuQeGdiTi5acgRFVgThDG.JfhaAy1wg3S', NULL, NULL, NULL, N'2025-03-05 18:36:17.115866', N'customer0017', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'116', N'system', N'2025-03-05 18:36:17.266769', NULL, N'customer@com.18', NULL, N'1', N'0', N'NAME-customer0018', N'$2a$10$k7eMdiEgdsQ0Hp3pVNJgx.NdUb9PPQIte4UiWWlrG8rojED5VHX.i', NULL, NULL, NULL, N'2025-03-05 18:36:17.266769', N'customer0018', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'117', N'system', N'2025-03-05 18:36:17.422194', NULL, N'customer@com.19', NULL, N'1', N'0', N'NAME-customer0019', N'$2a$10$pFn.RjK6jiYigwLauef/8uk/XTsATXEyx73u7Lv4YqynNIyA7IMjq', NULL, NULL, NULL, N'2025-03-05 18:36:17.422194', N'customer0019', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'118', N'system', N'2025-03-05 18:36:17.575820', NULL, N'customer@com.20', NULL, N'1', N'0', N'NAME-customer0020', N'$2a$10$3zbGKNOfL07f/tCsGgyeS.3wpnN/P02mRTxk9No7cAPPhrE/0PyV2', NULL, NULL, NULL, N'2025-03-05 18:36:17.575820', N'customer0020', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'119', N'system', N'2025-03-05 18:36:17.724804', NULL, N'customer@com.21', NULL, N'1', N'0', N'NAME-customer0021', N'$2a$10$yt2E47RsiNPlc5pzgQdE2.Rh6fiBVPNME/As2lmsjCLM8v.t25E7q', NULL, NULL, NULL, N'2025-03-05 18:36:17.724804', N'customer0021', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'120', N'system', N'2025-03-05 18:36:17.875888', NULL, N'customer@com.22', NULL, N'1', N'0', N'NAME-customer0022', N'$2a$10$g8uI5PnGnHN2ply8u5T8GO4m78yJlAjTlbJFNdbsuzUMuAM/G6ClS', NULL, NULL, NULL, N'2025-03-05 18:36:17.875888', N'customer0022', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'121', N'system', N'2025-03-05 18:36:18.024394', NULL, N'customer@com.23', NULL, N'1', N'0', N'NAME-customer0023', N'$2a$10$oFFKUEHhBYJqzq6p8Ub8NuCIORqdL5drmMpT.pvC3KQEy.tzy1QYe', NULL, NULL, NULL, N'2025-03-05 18:36:18.024394', N'customer0023', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'122', N'system', N'2025-03-05 18:36:18.177943', NULL, N'customer@com.24', NULL, N'1', N'0', N'NAME-customer0024', N'$2a$10$e6/McswLMvLeHHNx0fE1vu0DDvEp45bZPm8kdBGcswOmjArNVmXlu', NULL, NULL, NULL, N'2025-03-05 18:36:18.177943', N'customer0024', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'123', N'system', N'2025-03-05 18:36:18.451076', NULL, N'customer@com.25', NULL, N'1', N'0', N'NAME-customer0025', N'$2a$10$UjQ7gqEoV4bkwWBE0L5uLO7VUaooUIkeTSlmgYJgmgmPw1ojxMfcC', NULL, NULL, NULL, N'2025-03-05 18:36:18.451076', N'customer0025', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'124', N'system', N'2025-03-05 18:36:18.601964', NULL, N'customer@com.26', NULL, N'1', N'0', N'NAME-customer0026', N'$2a$10$ZIZF5PBNgjzc6FZIxvp2O.SeUTYZyWobJRKx2.lXhLTO9a054anSa', NULL, NULL, NULL, N'2025-03-05 18:36:18.601964', N'customer0026', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'125', N'system', N'2025-03-05 18:36:18.746956', NULL, N'customer@com.27', NULL, N'1', N'0', N'NAME-customer0027', N'$2a$10$uejgKSbMrJ2vfTFOCNTj1elObJqHXoLf56ZqRPjgV4Aeo.6SG4dqa', NULL, NULL, NULL, N'2025-03-05 18:36:18.746956', N'customer0027', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'126', N'system', N'2025-03-05 18:36:18.904864', NULL, N'customer@com.28', NULL, N'1', N'0', N'NAME-customer0028', N'$2a$10$BVVKSme2FxyARn16tEhBI.KA0e1jiujJJsWMtYwiNjV8jfM07o14y', NULL, NULL, NULL, N'2025-03-05 18:36:18.904864', N'customer0028', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'127', N'system', N'2025-03-05 18:36:19.060005', NULL, N'customer@com.29', NULL, N'1', N'0', N'NAME-customer0029', N'$2a$10$OR5/zVJlfByzzg6BAF3ByuZcD/Fd6XEXS2N475lj0IjRZ6IfO3KUe', NULL, NULL, NULL, N'2025-03-05 18:36:19.060005', N'customer0029', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'128', N'system', N'2025-03-05 18:36:19.256014', NULL, N'customer@com.30', NULL, N'1', N'0', N'NAME-customer0030', N'$2a$10$UM/qD04LFSJPNL.0.5egyO3M3Hph2v2bSq0qCq.hQlYntUxQxwXKa', NULL, NULL, NULL, N'2025-03-05 18:36:19.256014', N'customer0030', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'129', N'system', N'2025-03-05 18:36:19.460056', NULL, N'customer@com.31', NULL, N'1', N'0', N'NAME-customer0031', N'$2a$10$AGpexp3pccdfiqfrmb5ro.iOd51UWjVtgKA04/QkRQ7EOFiU4btNG', NULL, NULL, NULL, N'2025-03-05 18:36:19.460056', N'customer0031', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'130', N'system', N'2025-03-05 18:36:19.649879', NULL, N'customer@com.32', NULL, N'1', N'0', N'NAME-customer0032', N'$2a$10$KjbJ8sgnSULSxUr4isPI6eLEmnrjSiujq/N9/f7Pjtu/CeWxpmHbO', NULL, NULL, NULL, N'2025-03-05 18:36:19.649879', N'customer0032', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'131', N'system', N'2025-03-05 18:36:19.796310', NULL, N'customer@com.33', NULL, N'1', N'0', N'NAME-customer0033', N'$2a$10$k4Xa5xiY7R6qjSoaaqFznOvtN820WckQtgTBprCxX4pgb85wcx3yy', NULL, NULL, NULL, N'2025-03-05 18:36:19.796310', N'customer0033', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'132', N'system', N'2025-03-05 18:36:19.946315', NULL, N'customer@com.34', NULL, N'1', N'0', N'NAME-customer0034', N'$2a$10$hpikMi7PYY65aCh0wWr3HezJL.EBBad1oVSqzo41EbIkYZlVI38yu', NULL, NULL, NULL, N'2025-03-05 18:36:19.946315', N'customer0034', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'133', N'system', N'2025-03-05 18:36:20.096339', NULL, N'customer@com.35', NULL, N'1', N'0', N'NAME-customer0035', N'$2a$10$vnQVgGA7Dmcvzqqd0.wszuR9VxOKi4J6eo7Y/IrK.fyl3CKTaYojW', NULL, NULL, NULL, N'2025-03-05 18:36:20.096339', N'customer0035', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'134', N'system', N'2025-03-05 18:36:20.243070', NULL, N'customer@com.36', NULL, N'1', N'0', N'NAME-customer0036', N'$2a$10$4Ne2dyr9f/dWLJi9rM7zzOoE5C5ZnLLVqDpD.88PXywnjNyMZAnmq', NULL, NULL, NULL, N'2025-03-05 18:36:20.243070', N'customer0036', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'135', N'system', N'2025-03-05 18:36:20.391775', NULL, N'customer@com.37', NULL, N'1', N'0', N'NAME-customer0037', N'$2a$10$nWopNaBF.qQJNZgvrUk2ieK.hIuSAxzGYiL.8zIpF9ujBPgdKpS8K', NULL, NULL, NULL, N'2025-03-05 18:36:20.391775', N'customer0037', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'136', N'system', N'2025-03-05 18:36:20.545946', NULL, N'customer@com.38', NULL, N'1', N'0', N'NAME-customer0038', N'$2a$10$tGQ2p6Q.Kr7Q4zvLWifp4eVgKjvnbMdKfZ10QLbH5F0xDp3oYSBKO', NULL, NULL, NULL, N'2025-03-05 18:36:20.545946', N'customer0038', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'137', N'system', N'2025-03-05 18:36:20.703172', NULL, N'customer@com.39', NULL, N'1', N'0', N'NAME-customer0039', N'$2a$10$rmIpa3km5XXsKpNw/lE25uW.WILT2h.dMXMqkZmmt0Fk3XtsyRiKS', NULL, NULL, NULL, N'2025-03-05 18:36:20.703172', N'customer0039', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'138', N'system', N'2025-03-05 18:36:20.857006', NULL, N'customer@com.40', NULL, N'1', N'0', N'NAME-customer0040', N'$2a$10$4jrcGREji6WMwHkVvCUnl.iMEW9d9Agkb8vtCF3hGiKh5i8gIJK9q', NULL, NULL, NULL, N'2025-03-05 18:36:20.857006', N'customer0040', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'139', N'system', N'2025-03-05 18:36:21.051820', NULL, N'customer@com.41', NULL, N'1', N'0', N'NAME-customer0041', N'$2a$10$8AfyEbqeSTbrOdby79K0cuKMMNzzXpa6GNu7pp9R6yL7Bt4IBcrx.', NULL, NULL, NULL, N'2025-03-05 18:36:21.051820', N'customer0041', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'140', N'system', N'2025-03-05 18:36:21.196508', NULL, N'customer@com.42', NULL, N'1', N'0', N'NAME-customer0042', N'$2a$10$YJInSg6KSSp/ADb8Jl78E.jneSYJOI4N3F.Ev2ZB1c7L0k.uXVvue', NULL, NULL, NULL, N'2025-03-05 18:36:21.196508', N'customer0042', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'141', N'system', N'2025-03-05 18:36:21.411555', NULL, N'customer@com.43', NULL, N'1', N'0', N'NAME-customer0043', N'$2a$10$7NBd9U.QvtJWHf9lT3wmXeivJnjcxXSx7TGJnKyGkHeyEE.xTuIV2', NULL, NULL, NULL, N'2025-03-05 18:36:21.411555', N'customer0043', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'142', N'system', N'2025-03-05 18:36:21.611633', NULL, N'customer@com.44', NULL, N'1', N'0', N'NAME-customer0044', N'$2a$10$Zqn3EkwSzA.wLJVndFlJ/uFziy7ElrBaPEr.Xm22.LAZSd4BVUZy.', NULL, NULL, NULL, N'2025-03-05 18:36:21.611633', N'customer0044', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'143', N'system', N'2025-03-05 18:36:21.770334', NULL, N'customer@com.45', NULL, N'1', N'0', N'NAME-customer0045', N'$2a$10$2A.0P9gT1u0F5gGGhXxQ5.oX/L7vFb8cBn/XxHN1JXWqvMCnf8DI.', NULL, NULL, NULL, N'2025-03-05 18:36:21.770334', N'customer0045', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'144', N'system', N'2025-03-05 18:36:21.967471', NULL, N'customer@com.46', NULL, N'1', N'0', N'NAME-customer0046', N'$2a$10$p5Mli70PAd30Bum.giB6te/mANgyONSDzjtdrFK393H7xq/QxQl5q', NULL, NULL, NULL, N'2025-03-05 18:36:21.967471', N'customer0046', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'145', N'system', N'2025-03-05 18:36:22.158775', NULL, N'customer@com.47', NULL, N'1', N'0', N'NAME-customer0047', N'$2a$10$gyNXuR7wIkxmARTr3.E0Ku0CoxTHrjAaHfdcvkqcZWBCaVknXmBju', NULL, NULL, NULL, N'2025-03-05 18:36:22.158775', N'customer0047', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'146', N'system', N'2025-03-05 18:36:22.307024', NULL, N'customer@com.48', NULL, N'1', N'0', N'NAME-customer0048', N'$2a$10$JynfJIvUGS32RwY9NEk5ZeKCIJUdLBimyXZShNSZOhK.Ty3nSoGvy', NULL, NULL, NULL, N'2025-03-05 18:36:22.307024', N'customer0048', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'147', N'system', N'2025-03-05 18:36:22.460348', NULL, N'customer@com.49', NULL, N'1', N'0', N'NAME-customer0049', N'$2a$10$I93bzFNjjvJxwBgXlQo7Vuq1H.3rFseCIlxW3E2hCNle4/U3FJBYO', NULL, NULL, NULL, N'2025-03-05 18:36:22.460348', N'customer0049', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'148', N'system', N'2025-03-05 18:36:22.629231', NULL, N'customer@com.50', NULL, N'0', N'0', N'NAME-customer0050', N'$2a$10$sZCvZ9IV.qqAXLmVOegPoeqfsfV7g8gDfVCCzQIPktixmWvXDI6Ra', NULL, NULL, NULL, N'2025-03-05 18:36:22.629231', N'customer0050', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'149', N'system', N'2025-03-05 18:36:22.797240', NULL, N'customer@com.51', NULL, N'1', N'0', N'NAME-customer0051', N'$2a$10$YENYRy2UnW8Ecmasem4oYe177YPHLB13OZER9N2wbrc6ySnObo0O2', NULL, NULL, NULL, N'2025-03-05 18:36:22.797240', N'customer0051', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'150', N'system', N'2025-03-05 18:36:22.995090', NULL, N'customer@com.52', NULL, N'1', N'0', N'NAME-customer0052', N'$2a$10$LxwXQEIZdBhuxoPI80f7muEigGoHumdMgimQKcAPUSHlBN.wNAgSm', NULL, NULL, NULL, N'2025-03-05 18:36:22.995090', N'customer0052', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'151', N'system', N'2025-03-05 18:36:23.180323', NULL, N'customer@com.53', NULL, N'1', N'0', N'NAME-customer0053', N'$2a$10$vcIBo5drvX2k/1WhGtB7M.Mrn84aap9sBXz9O1A8vS9OTpninxvQe', NULL, NULL, NULL, N'2025-03-05 18:36:23.180323', N'customer0053', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'152', N'system', N'2025-03-05 18:36:23.324467', NULL, N'customer@com.54', NULL, N'1', N'0', N'NAME-customer0054', N'$2a$10$Q1FNJX0wL8hN84nxIr3SaeOt6qyMJdLvnK16EvXoG4y/3f3SM3Vrq', NULL, NULL, NULL, N'2025-03-05 18:36:23.324467', N'customer0054', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'153', N'system', N'2025-03-05 18:36:23.462455', NULL, N'customer@com.55', NULL, N'1', N'0', N'NAME-customer0055', N'$2a$10$iCufAsYbpoDX24P3mr5FJOgb.XoxyFgKgV19YPBaCPuBcDeF0Hdl.', NULL, NULL, NULL, N'2025-03-05 18:36:23.462455', N'customer0055', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'154', N'system', N'2025-03-05 18:36:23.610816', NULL, N'customer@com.56', NULL, N'1', N'0', N'NAME-customer0056', N'$2a$10$cf.rJ8En4m2WofZ9rUPGSuSCg/vg2Ng.w/Ruh.BSLY1drbktc.SlO', NULL, NULL, NULL, N'2025-03-05 18:36:23.610816', N'customer0056', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'155', N'system', N'2025-03-05 18:36:23.781656', NULL, N'customer@com.57', NULL, N'1', N'0', N'NAME-customer0057', N'$2a$10$21n6RtvSsM0n1KeBZwpkb.PUuAdYcggxYoMv/Id177k2PhRII31Z6', NULL, NULL, NULL, N'2025-03-05 18:36:23.781656', N'customer0057', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'156', N'system', N'2025-03-05 18:36:23.961030', NULL, N'customer@com.58', NULL, N'1', N'0', N'NAME-customer0058', N'$2a$10$JvcndRezkLMbpVMXukmY3O23jCu931Cbd3hsLy4W4L8dkMBGeSOeC', NULL, NULL, NULL, N'2025-03-05 18:36:23.961030', N'customer0058', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'157', N'system', N'2025-03-05 18:36:24.102756', NULL, N'customer@com.59', NULL, N'1', N'0', N'NAME-customer0059', N'$2a$10$Ejm/ia5Br4VXwuSZq4/oxeMsob9o71MWXyT2/QNz5ZU4SgP5SHAo6', NULL, NULL, NULL, N'2025-03-05 18:36:24.102756', N'customer0059', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'158', N'system', N'2025-03-05 18:36:24.254023', NULL, N'customer@com.60', NULL, N'1', N'0', N'NAME-customer0060', N'$2a$10$EVH4ZaxDqjMUKKzHh7DKzeXsbpvStXFGetiTn5gDXc6XuEok5H5Ey', NULL, NULL, NULL, N'2025-03-05 18:36:24.254023', N'customer0060', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'159', N'system', N'2025-03-05 18:36:24.400664', NULL, N'customer@com.61', NULL, N'1', N'0', N'NAME-customer0061', N'$2a$10$tKclJnkRauCdAF6DZQrMv.UvsDMqqiceOG.4HunHk/L4VibgPH1OO', NULL, NULL, NULL, N'2025-03-05 18:36:24.400664', N'customer0061', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'160', N'system', N'2025-03-05 18:36:24.587173', NULL, N'customer@com.62', NULL, N'1', N'0', N'NAME-customer0062', N'$2a$10$RPrZ0jtKE5Kt8rORp3PrteTGfMtFD4d/6psjIybMApL0GUUvrRiiS', NULL, NULL, NULL, N'2025-03-05 18:36:24.587173', N'customer0062', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'161', N'system', N'2025-03-05 18:36:24.730242', NULL, N'customer@com.63', NULL, N'1', N'0', N'NAME-customer0063', N'$2a$10$9u9aJ8vLsAbAZkmd8jp4V.gZqE3EfWzW0G4RjAxxDlgrdF3/5QuJ2', NULL, NULL, NULL, N'2025-03-05 18:36:24.730242', N'customer0063', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'162', N'system', N'2025-03-05 18:36:24.872104', NULL, N'customer@com.64', NULL, N'1', N'0', N'NAME-customer0064', N'$2a$10$sW1G8guHsTcMy4B9XZE7/eQ/BoWLv6cemka2ZSNBcj1.LeXdT7qEC', NULL, NULL, NULL, N'2025-03-05 18:36:24.872104', N'customer0064', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'163', N'system', N'2025-03-05 18:36:25.036922', NULL, N'customer@com.65', NULL, N'1', N'0', N'NAME-customer0065', N'$2a$10$Qd8QcNdQ/lswXTjAZiirXuv2ZRu3bHKtaOOyI8YnafttzXQh12Tse', NULL, NULL, NULL, N'2025-03-05 18:36:25.036922', N'customer0065', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'164', N'system', N'2025-03-05 18:36:25.240885', NULL, N'customer@com.66', NULL, N'1', N'0', N'NAME-customer0066', N'$2a$10$s4Tkh5UGOrQ8xfoQiizrBuiKXVBfg1RYK.wPig1wpcsIhTnQHmgqm', NULL, NULL, NULL, N'2025-03-05 18:36:25.240885', N'customer0066', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'165', N'system', N'2025-03-05 18:36:25.429895', NULL, N'customer@com.67', NULL, N'1', N'0', N'NAME-customer0067', N'$2a$10$M/0s6zdWheLEp5.HGGIBfOliS2TRCQtHDYN2KA65rDrdf3gZ4I.Iy', NULL, NULL, NULL, N'2025-03-05 18:36:25.429895', N'customer0067', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'166', N'system', N'2025-03-05 18:36:25.580467', NULL, N'customer@com.68', NULL, N'1', N'0', N'NAME-customer0068', N'$2a$10$m2FkNmJ19R0zFO6spfKk/e8rVM.kPsU5oruCeZ/L3fPcs3bsmomLC', NULL, NULL, NULL, N'2025-03-05 18:36:25.580467', N'customer0068', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'167', N'system', N'2025-03-05 18:36:25.765034', NULL, N'customer@com.69', NULL, N'1', N'0', N'NAME-customer0069', N'$2a$10$FhJ01WEfCNqGaCmnsypF4.2UxL0I10sinEUyFDXCKzMpFHqlCK0eO', NULL, NULL, NULL, N'2025-03-05 18:36:25.765034', N'customer0069', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'168', N'system', N'2025-03-05 18:36:25.905043', NULL, N'customer@com.70', NULL, N'1', N'0', N'NAME-customer0070', N'$2a$10$xa7izeNUlWaS2ffGSYzJdOSv84VO5GPqMQcIpigk.KFqmywr1ajUK', NULL, NULL, NULL, N'2025-03-05 18:36:25.905043', N'customer0070', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'169', N'system', N'2025-03-05 18:36:26.045868', NULL, N'customer@com.71', NULL, N'1', N'0', N'NAME-customer0071', N'$2a$10$YAgyfzb0S3hDcIATKjPJLOhe2P6YnF1yQWnLUkXqpIxVKTEIKT5VO', NULL, NULL, NULL, N'2025-03-05 18:36:26.045868', N'customer0071', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'170', N'system', N'2025-03-05 18:36:26.232805', NULL, N'customer@com.72', NULL, N'1', N'0', N'NAME-customer0072', N'$2a$10$lSagxVw7dMcH04yZdctBBeSgIQ/D43Ln/nGriaOs26VJZAe02YMS6', NULL, NULL, NULL, N'2025-03-05 18:36:26.232805', N'customer0072', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'171', N'system', N'2025-03-05 18:36:26.415205', NULL, N'customer@com.73', NULL, N'1', N'0', N'NAME-customer0073', N'$2a$10$9ozFytfNgQlCZ8ga3HQKM.8YpKbJsI7z1LTPNmURLTq2GC9o9wJkW', NULL, NULL, NULL, N'2025-03-05 18:36:26.415205', N'customer0073', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'172', N'system', N'2025-03-05 18:36:26.597366', NULL, N'customer@com.74', NULL, N'1', N'0', N'NAME-customer0074', N'$2a$10$ASevzAB.J/vl91HphTfRsuSLLLxul17BQ9/teiG2P4ow0RX8Uhm3W', NULL, NULL, NULL, N'2025-03-05 18:36:26.597366', N'customer0074', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'173', N'system', N'2025-03-05 18:36:26.744031', NULL, N'customer@com.75', NULL, N'1', N'0', N'NAME-customer0075', N'$2a$10$cgZuqi/hox7L5DXya52IweJui3i7rbzFu6CGAp5uk7bWGDENZoLi2', NULL, NULL, NULL, N'2025-03-05 18:36:26.744031', N'customer0075', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'174', N'system', N'2025-03-05 18:36:26.888877', NULL, N'customer@com.76', NULL, N'1', N'0', N'NAME-customer0076', N'$2a$10$e6/Nf0lJCArodC.1Uq7Kvu0zD1eTJcLPOILdD/thu0MNG8EBtbTXW', NULL, NULL, NULL, N'2025-03-05 18:36:26.888877', N'customer0076', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'175', N'system', N'2025-03-05 18:36:27.028964', NULL, N'customer@com.77', NULL, N'1', N'0', N'NAME-customer0077', N'$2a$10$dV//gE2gJDlGcKfF8ZvbKOy/QKr0fFKtyN2mG7pOX7xhboqza7keC', NULL, NULL, NULL, N'2025-03-05 18:36:27.028964', N'customer0077', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'176', N'system', N'2025-03-05 18:36:27.169673', NULL, N'customer@com.78', NULL, N'1', N'0', N'NAME-customer0078', N'$2a$10$TcERN2/RpSe.oV6kaj1xl.JDCYN9b1QKSJljELRujWPW6JmI8b.ei', NULL, NULL, NULL, N'2025-03-05 18:36:27.169673', N'customer0078', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'177', N'system', N'2025-03-05 18:36:27.353400', NULL, N'customer@com.79', NULL, N'1', N'0', N'NAME-customer0079', N'$2a$10$n2M81Cve5q8fMuvu/TIdyOZB0bzjTSO48WYg3Waum/b0tKyvpeB8O', NULL, NULL, NULL, N'2025-03-05 18:36:27.353400', N'customer0079', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'178', N'system', N'2025-03-05 18:36:27.550980', NULL, N'customer@com.80', NULL, N'1', N'0', N'NAME-customer0080', N'$2a$10$G.WCw3NznlbN5ww.VA4ku.RjmiqvcdYU4iVKd/gET6Deznnpu1LR6', NULL, NULL, NULL, N'2025-03-05 18:36:27.550980', N'customer0080', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'179', N'system', N'2025-03-05 18:36:27.694557', NULL, N'customer@com.81', NULL, N'1', N'0', N'NAME-customer0081', N'$2a$10$EOhlQMcpoCMhV9lFPjCfje40oXlz9B90R2ShMrtgZ3TiXSnKBP7Qm', NULL, NULL, NULL, N'2025-03-05 18:36:27.694557', N'customer0081', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'180', N'system', N'2025-03-05 18:36:27.834618', NULL, N'customer@com.82', NULL, N'1', N'0', N'NAME-customer0082', N'$2a$10$AYsvz3mwP8mQmWkktc5LXuxTyJnz6LHwNDAc16QiO1o5AJxBctLoy', NULL, NULL, NULL, N'2025-03-05 18:36:27.834618', N'customer0082', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'181', N'system', N'2025-03-05 18:36:27.978903', NULL, N'customer@com.83', NULL, N'1', N'0', N'NAME-customer0083', N'$2a$10$NooYPb8gKhlbIE6LWYS5t.cICiaZK2XX.HOPGT4y1kyV5O.yMA37y', NULL, NULL, NULL, N'2025-03-05 18:36:27.978903', N'customer0083', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'182', N'system', N'2025-03-05 18:36:28.130974', NULL, N'customer@com.84', NULL, N'1', N'0', N'NAME-customer0084', N'$2a$10$bOHMqR6nrUADr9NWUuPHQ.Pua.H52u.73MIfdRLFvO4uRW5vmbZNq', NULL, NULL, NULL, N'2025-03-05 18:36:28.130974', N'customer0084', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'183', N'system', N'2025-03-05 18:36:28.278821', NULL, N'customer@com.85', NULL, N'1', N'0', N'NAME-customer0085', N'$2a$10$pjg22PMygcKiyGh/uzPo.OsX8ftzE8WTDcRUS5QT1GJM/xtQQawCG', NULL, NULL, NULL, N'2025-03-05 18:36:28.278821', N'customer0085', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'184', N'system', N'2025-03-05 18:36:28.419709', NULL, N'customer@com.86', NULL, N'1', N'0', N'NAME-customer0086', N'$2a$10$fmK6q9Uv7Ot2fxyrK.UYN.mssByfsY3iSwT2VpD9G8YyfkGyFtDLq', NULL, NULL, NULL, N'2025-03-05 18:36:28.419709', N'customer0086', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'185', N'system', N'2025-03-05 18:36:28.618691', NULL, N'customer@com.87', NULL, N'1', N'0', N'NAME-customer0087', N'$2a$10$e8UPBGS.sOkHt407FJYSB..ePzOb5l3Asvq9mR3b60LSAv7saQdD2', NULL, NULL, NULL, N'2025-03-05 18:36:28.618691', N'customer0087', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'186', N'system', N'2025-03-05 18:36:28.814852', NULL, N'customer@com.88', NULL, N'1', N'0', N'NAME-customer0088', N'$2a$10$3ObvTTNQVrpDVWmZPPiKAuajCTeX9igt.XIzaoHsm3.T0v21.5okS', NULL, NULL, NULL, N'2025-03-05 18:36:28.814852', N'customer0088', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'187', N'system', N'2025-03-05 18:36:29.000899', NULL, N'customer@com.89', NULL, N'1', N'0', N'NAME-customer0089', N'$2a$10$a1FjPQ6i/Y1wCA5Y4OyKW.0lluIq4YsjMZALb1BUMx/ckoFRpgE3y', NULL, NULL, NULL, N'2025-03-05 18:36:29.000899', N'customer0089', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'188', N'system', N'2025-03-05 18:36:29.139717', NULL, N'customer@com.90', NULL, N'1', N'0', N'NAME-customer0090', N'$2a$10$X8kijq86fFmLD8TA/u1GDu/cruOKawjukT7WpasnanTlYAuMWUxNe', NULL, NULL, NULL, N'2025-03-05 18:36:29.139717', N'customer0090', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'189', N'system', N'2025-03-05 18:36:29.281658', NULL, N'customer@com.91', NULL, N'1', N'0', N'NAME-customer0091', N'$2a$10$Y.6pjpZ7UevTt4svgalNbelC2teoxLQ6Fz4.Z86rqzAxRISV8.nXi', NULL, NULL, NULL, N'2025-03-05 18:36:29.281658', N'customer0091', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'190', N'system', N'2025-03-05 18:36:29.431806', NULL, N'customer@com.92', NULL, N'1', N'0', N'NAME-customer0092', N'$2a$10$bjDw3chqnCAukp0vltdD2.CofnHJj4onXgtwE1MNeQhjvIEnmfO.m', NULL, NULL, NULL, N'2025-03-05 18:36:29.431806', N'customer0092', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'191', N'system', N'2025-03-05 18:36:29.575704', NULL, N'customer@com.93', NULL, N'1', N'0', N'NAME-customer0093', N'$2a$10$GoPnWQKBYxBtbLqsxwq3GuGrN.jaDCc25wsgs6avdX0WAnEhVXARq', NULL, NULL, NULL, N'2025-03-05 18:36:29.575704', N'customer0093', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'192', N'system', N'2025-03-05 18:36:29.720331', NULL, N'customer@com.94', NULL, N'1', N'0', N'NAME-customer0094', N'$2a$10$wiIHIiW3dALh4YbbeyBS5eNpKs0pOXai9cFh.oSWYmYg.g5dS3Z9u', NULL, NULL, NULL, N'2025-03-05 18:36:29.720331', N'customer0094', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'193', N'system', N'2025-03-05 18:36:29.864302', NULL, N'customer@com.95', NULL, N'1', N'0', N'NAME-customer0095', N'$2a$10$36CRrDiEmpUNVgTmQ5qTg.4GOtbcu0rvlIDe.fPD/IuTO5N0E49pC', NULL, NULL, NULL, N'2025-03-05 18:36:29.864302', N'customer0095', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'194', N'system', N'2025-03-05 18:36:30.013599', NULL, N'customer@com.96', NULL, N'1', N'0', N'NAME-customer0096', N'$2a$10$HWAY/Z6Hzq0jNR0EAsOkYOv1nhehGIK6qDg4gEtnEoneMhVY5FvMq', NULL, NULL, NULL, N'2025-03-05 18:36:30.013599', N'customer0096', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'195', N'system', N'2025-03-05 18:36:30.155573', NULL, N'customer@com.97', NULL, N'1', N'0', N'NAME-customer0097', N'$2a$10$bNi/K9HeXVghsdu0MClvVeXEKa0mbcvyRqv3PvuRVax65yUUOHxcK', NULL, NULL, NULL, N'2025-03-05 18:36:30.155573', N'customer0097', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'196', N'system', N'2025-03-05 18:36:30.300848', NULL, N'customer@com.98', NULL, N'1', N'0', N'NAME-customer0098', N'$2a$10$BBdeu9zy4LwpxP4AljxLEumD46Xn3hMLPc.JyLtk6.lK1E27Q9Ada', NULL, NULL, NULL, N'2025-03-05 18:36:30.300848', N'customer0098', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'197', N'system', N'2025-03-05 18:36:30.439892', NULL, N'customer@com.99', NULL, N'1', N'0', N'NAME-customer0099', N'$2a$10$Gq5JNirIMSNBLMB3yKp8.uAMKr4ZQNK8p4mnRJQEbpIoTtG0ZY8BC', NULL, NULL, NULL, N'2025-03-05 18:36:30.439892', N'customer0099', N'2')
GO

INSERT INTO [Person].[Users] ([ID], [CreatedBy], [CreatedDate], [DateOfBirth], [Email], [Gender], [IsActive], [IsDelete], [Name], [Password], [PhoneNumber], [ProfilePicture], [UpdatedBy], [UpdatedDate], [Username], [RoleId]) VALUES (N'198', N'system', N'2025-03-05 18:36:30.589526', NULL, N'customer@com.100', NULL, N'1', N'0', N'NAME-customer00100', N'$2a$10$q69LljaVLUn6zhvmaEKdQegY4awg04Y85AZ5zo.4NEqiLin4Y/Uwa', NULL, NULL, NULL, N'2025-03-05 18:36:30.589526', N'customer00100', N'2')
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
DBCC CHECKIDENT ('[Person].[RolePermissions]', RESEED, 10025)
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
DBCC CHECKIDENT ('[Person].[UserAddress]', RESEED, 11)
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
DBCC CHECKIDENT ('[Person].[Users]', RESEED, 198)
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

