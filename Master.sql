/*
 Navicat Premium Data Transfer

 Source Server         : MS SQL Server
 Source Server Type    : SQL Server
 Source Server Version : 15002000 (15.00.2000)
 Source Host           : localhost:1433
 Source Catalog        : OnlineShop
 Source Schema         : Master

 Target Server Type    : SQL Server
 Target Server Version : 15002000 (15.00.2000)
 File Encoding         : 65001

 Date: 31/01/2025 19:12:22
*/


-- ----------------------------
-- Table structure for DefaultRolePermission
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Master].[DefaultRolePermission]') AND type IN ('U'))
	DROP TABLE [Master].[DefaultRolePermission]
GO

CREATE TABLE [Master].[DefaultRolePermission] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [RoleId] bigint  NOT NULL,
  [PermissionId] bigint  NULL,
  [isActive] bit  NULL,
  [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CreatedDate] datetime2(6)  NOT NULL,
  [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [UpdatedDate] datetime2(6)  NULL
)
GO

ALTER TABLE [Master].[DefaultRolePermission] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of DefaultRolePermission
-- ----------------------------
SET IDENTITY_INSERT [Master].[DefaultRolePermission] ON
GO

INSERT INTO [Master].[DefaultRolePermission] ([id], [RoleId], [PermissionId], [isActive], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'1', N'2', N'2', N'1', N'1', N'2025-01-24 19:38:46.871000', NULL, NULL)
GO

INSERT INTO [Master].[DefaultRolePermission] ([id], [RoleId], [PermissionId], [isActive], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'2', N'2', N'3', N'0', N'1', N'2025-01-24 19:38:46.871000', NULL, NULL)
GO

INSERT INTO [Master].[DefaultRolePermission] ([id], [RoleId], [PermissionId], [isActive], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'3', N'1', N'4', N'1', N'1', N'2025-01-24 19:38:46.871000', NULL, NULL)
GO

INSERT INTO [Master].[DefaultRolePermission] ([id], [RoleId], [PermissionId], [isActive], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'4', N'1', N'5', N'1', N'1', N'2025-01-24 19:38:46.871000', NULL, NULL)
GO

INSERT INTO [Master].[DefaultRolePermission] ([id], [RoleId], [PermissionId], [isActive], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'5', N'1', N'2', N'1', N'1', N'2025-01-24 19:38:46.871000', NULL, NULL)
GO

INSERT INTO [Master].[DefaultRolePermission] ([id], [RoleId], [PermissionId], [isActive], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'11', N'1', N'6', N'1', N'1', N'2025-01-31 10:09:36.000000', NULL, NULL)
GO

SET IDENTITY_INSERT [Master].[DefaultRolePermission] OFF
GO


-- ----------------------------
-- Table structure for Permissions
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Master].[Permissions]') AND type IN ('U'))
	DROP TABLE [Master].[Permissions]
GO

CREATE TABLE [Master].[Permissions] (
  [ID] bigint  IDENTITY(1,1) NOT NULL,
  [Name] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Description] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CreatedDate] datetime2(6)  NOT NULL,
  [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [UpdatedDate] datetime2(6)  NULL
)
GO

ALTER TABLE [Master].[Permissions] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Permissions
-- ----------------------------
SET IDENTITY_INSERT [Master].[Permissions] ON
GO

INSERT INTO [Master].[Permissions] ([ID], [Name], [Description], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'2', N'PROFILE', N'Permission to show own profile detail', N'sistem', N'2025-01-24 01:30:05.000000', NULL, NULL)
GO

INSERT INTO [Master].[Permissions] ([ID], [Name], [Description], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'3', N'EDITUSERDETAIL', N'Permission to edit other users details', N'sistem', N'2025-01-24 19:43:49.730000', NULL, NULL)
GO

INSERT INTO [Master].[Permissions] ([ID], [Name], [Description], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'4', N'SHOWUSERS', N'Permission to show all user', N'sistem', N'2025-01-24 19:43:49.730000', NULL, NULL)
GO

INSERT INTO [Master].[Permissions] ([ID], [Name], [Description], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'5', N'SHOWUSERDETAIL', N'Permission to show other users detail', N'sistem', N'2025-01-24 19:43:49.730000', NULL, NULL)
GO

INSERT INTO [Master].[Permissions] ([ID], [Name], [Description], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'6', N'ADDUSER', N'Permission to create new user', N'sistem', N'2025-01-24 19:43:49.730000', NULL, NULL)
GO

INSERT INTO [Master].[Permissions] ([ID], [Name], [Description], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'7', N'PRODUCTCATEGORYACTIVEVIEW', N'Permission to view active product and category', N'sistem', N'2025-01-31 10:25:19.000000', NULL, NULL)
GO

INSERT INTO [Master].[Permissions] ([ID], [Name], [Description], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'8', N'PRODUCTCATEGORYEDIT', N'Permission to add/edit product and category', N'sistem', N'2025-01-31 10:25:19.000000', NULL, NULL)
GO

INSERT INTO [Master].[Permissions] ([ID], [Name], [Description], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'9', N'PRODUCTCATEGORYVIEW', N'Permission to view all product and category', N'sistem', N'2025-01-31 10:25:19.000000', NULL, NULL)
GO

SET IDENTITY_INSERT [Master].[Permissions] OFF
GO


-- ----------------------------
-- Table structure for Role
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Master].[Role]') AND type IN ('U'))
	DROP TABLE [Master].[Role]
GO

CREATE TABLE [Master].[Role] (
  [ID] bigint  IDENTITY(1,1) NOT NULL,
  [Name] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CreatedDate] datetime2(6)  NOT NULL,
  [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [UpdatedDate] datetime2(6)  NULL,
  [IsActive] bit DEFAULT 1 NOT NULL
)
GO

ALTER TABLE [Master].[Role] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Role
-- ----------------------------
SET IDENTITY_INSERT [Master].[Role] ON
GO

INSERT INTO [Master].[Role] ([ID], [Name], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate], [IsActive]) VALUES (N'1', N'Admin', N'oleh sistem', N'2025-01-20 09:42:42.369307', NULL, NULL, N'1')
GO

INSERT INTO [Master].[Role] ([ID], [Name], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate], [IsActive]) VALUES (N'2', N'Customer', N'oleh sistem', N'2025-01-20 09:42:42.510767', NULL, NULL, N'1')
GO

SET IDENTITY_INSERT [Master].[Role] OFF
GO


-- ----------------------------
-- Table structure for UserAddress
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Master].[UserAddress]') AND type IN ('U'))
	DROP TABLE [Master].[UserAddress]
GO

CREATE TABLE [Master].[UserAddress] (
  [ID] bigint  IDENTITY(1,1) NOT NULL,
  [UserId] bigint  NULL,
  [Name] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Address] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Country] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [PostalCode] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [IsActive] bit DEFAULT 1 NOT NULL,
  [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CreatedDate] datetime2(6)  NOT NULL,
  [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [UpdatedDate] datetime2(6)  NULL
)
GO

ALTER TABLE [Master].[UserAddress] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of UserAddress
-- ----------------------------
SET IDENTITY_INSERT [Master].[UserAddress] ON
GO

INSERT INTO [Master].[UserAddress] ([ID], [UserId], [Name], [Address], [Country], [PostalCode], [IsActive], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'24', N'42', N'0000', N'456 Elm St, Shelbyville, USa asdas ad asdasewqeadxrwev ', N'0000', N'0000', N'0', N'42', N'2025-01-28 14:51:58.663062', N'admin123', N'2025-01-28 17:56:01.377334')
GO

INSERT INTO [Master].[UserAddress] ([ID], [UserId], [Name], [Address], [Country], [PostalCode], [IsActive], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'25', N'42', N'lapangan', N'456 Elm St, Shelbyville, USa asdas ad asdasewqeadxrwev ', N'disono', N'32145ad', N'0', N'42', N'2025-01-28 14:54:06.864120', NULL, NULL)
GO

INSERT INTO [Master].[UserAddress] ([ID], [UserId], [Name], [Address], [Country], [PostalCode], [IsActive], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'26', N'42', N'lapangan1', N'456 Elm St, Shelbyville, USa asdas ad asdasewqeadxrwev ', N'disono1', N'32145ad1', N'0', N'admin123', N'2025-01-28 16:30:57.674695', NULL, NULL)
GO

INSERT INTO [Master].[UserAddress] ([ID], [UserId], [Name], [Address], [Country], [PostalCode], [IsActive], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'27', N'41', N'000', N'456 Elm St, Shelbyville, USa asdas ad asdasewqeadxrwev ', N'000', N'000', N'0', N'admin123', N'2025-01-28 16:49:01.381254', N'admin123', N'2025-01-28 17:49:34.921306')
GO

INSERT INTO [Master].[UserAddress] ([ID], [UserId], [Name], [Address], [Country], [PostalCode], [IsActive], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'28', N'42', N'lapangan', N'456 Elm St, Shelbyville, USa asdas ad asdasewqeadxrwev ', N'disono', N'32145ad', N'0', N'customer001', N'2025-01-28 17:20:10.040099', NULL, NULL)
GO

INSERT INTO [Master].[UserAddress] ([ID], [UserId], [Name], [Address], [Country], [PostalCode], [IsActive], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'30', N'41', N'000', N'456 Elm St, Shelbyville, USa asdas ad asdasewqeadxrwev ', N'000', N'000', N'0', N'admin123', N'2025-01-28 17:21:54.902325', N'admin123', N'2025-01-28 17:58:10.287147')
GO

INSERT INTO [Master].[UserAddress] ([ID], [UserId], [Name], [Address], [Country], [PostalCode], [IsActive], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'31', N'41', N'2222', N'456 Elm St, Shelbyville, USa asdas ad asdasewqeadxrwev ', N'22', N'222', N'0', N'admin123', N'2025-01-28 17:22:33.422116', NULL, NULL)
GO

INSERT INTO [Master].[UserAddress] ([ID], [UserId], [Name], [Address], [Country], [PostalCode], [IsActive], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'32', N'41', N'2222', N'456 Elm St, Shelbyville, USa asdas ad asdasewqeadxrwev ', N'22', N'222', N'0', N'admin123', N'2025-01-28 17:23:02.420579', NULL, NULL)
GO

INSERT INTO [Master].[UserAddress] ([ID], [UserId], [Name], [Address], [Country], [PostalCode], [IsActive], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'33', N'41', N'333', N'456 Elm St, Shelbyville, USa asdas ad asdasewqeadxrwev ', N'333', N'333', N'1', N'admin123', N'2025-01-28 17:32:43.880447', NULL, NULL)
GO

INSERT INTO [Master].[UserAddress] ([ID], [UserId], [Name], [Address], [Country], [PostalCode], [IsActive], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'34', N'41', N'555', N'456 Elm St, Shelbyville, USa asdas ad asdasewqeadxrwev ', N'555', N'555', N'0', N'admin123', N'2025-01-28 17:34:06.041331', NULL, NULL)
GO

INSERT INTO [Master].[UserAddress] ([ID], [UserId], [Name], [Address], [Country], [PostalCode], [IsActive], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'35', N'42', N'666', N'456 Elm St, Shelbyville, USa asdas ad asdasewqeadxrwev ', N'666', N'666', N'0', N'admin123', N'2025-01-28 17:34:34.382464', NULL, NULL)
GO

INSERT INTO [Master].[UserAddress] ([ID], [UserId], [Name], [Address], [Country], [PostalCode], [IsActive], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'36', N'42', N'666', N'456 Elm St, Shelbyville, USa asdas ad asdasewqeadxrwev ', N'666', N'666', N'0', N'admin123', N'2025-01-28 17:35:44.445651', NULL, NULL)
GO

SET IDENTITY_INSERT [Master].[UserAddress] OFF
GO


-- ----------------------------
-- Table structure for UserPermissions
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Master].[UserPermissions]') AND type IN ('U'))
	DROP TABLE [Master].[UserPermissions]
GO

CREATE TABLE [Master].[UserPermissions] (
  [UserId] bigint  NOT NULL,
  [AccessId] bigint  NOT NULL
)
GO

ALTER TABLE [Master].[UserPermissions] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of UserPermissions
-- ----------------------------
INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'41', N'2')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'41', N'3')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'41', N'4')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'41', N'5')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'41', N'6')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'41', N'7')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'41', N'8')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'42', N'2')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'43', N'2')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'43', N'3')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'44', N'2')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'44', N'3')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'45', N'2')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'45', N'4')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'45', N'5')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'46', N'2')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'46', N'3')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'46', N'4')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'46', N'5')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'48', N'2')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'48', N'4')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'48', N'5')
GO

INSERT INTO [Master].[UserPermissions] ([UserId], [AccessId]) VALUES (N'48', N'6')
GO


-- ----------------------------
-- Table structure for UserProfile
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Master].[UserProfile]') AND type IN ('U'))
	DROP TABLE [Master].[UserProfile]
GO

CREATE TABLE [Master].[UserProfile] (
  [UserId] bigint  NOT NULL,
  [FirstName] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [Gender] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [LastName] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [DateOfBirth] date  NULL,
  [ProfilePicture] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CreatedDate] datetime2(6)  NOT NULL,
  [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [UpdatedDate] datetime2(6)  NULL
)
GO

ALTER TABLE [Master].[UserProfile] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of UserProfile
-- ----------------------------
INSERT INTO [Master].[UserProfile] ([UserId], [FirstName], [Gender], [LastName], [DateOfBirth], [ProfilePicture], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'41', N'coba12', N'unknow', N'lagi1', N'2025-01-03', NULL, N'system', N'2025-01-28 14:24:42.641003', N'admin123', N'2025-01-31 16:18:49.382543')
GO

INSERT INTO [Master].[UserProfile] ([UserId], [FirstName], [Gender], [LastName], [DateOfBirth], [ProfilePicture], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'42', N'coba12xxxx3', N'unknow2xxx', N'lagi12xxx', NULL, NULL, N'system', N'2025-01-28 14:38:25.368710', N'admin123', N'2025-01-28 17:18:35.957826')
GO

INSERT INTO [Master].[UserProfile] ([UserId], [FirstName], [Gender], [LastName], [DateOfBirth], [ProfilePicture], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'43', NULL, NULL, NULL, NULL, NULL, N'system', N'2025-01-29 21:02:28.572363', NULL, NULL)
GO

INSERT INTO [Master].[UserProfile] ([UserId], [FirstName], [Gender], [LastName], [DateOfBirth], [ProfilePicture], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'44', NULL, NULL, NULL, NULL, NULL, N'system', N'2025-01-29 21:11:33.910293', NULL, NULL)
GO

INSERT INTO [Master].[UserProfile] ([UserId], [FirstName], [Gender], [LastName], [DateOfBirth], [ProfilePicture], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'45', NULL, NULL, NULL, NULL, NULL, N'admin123', N'2025-01-30 00:33:02.089075', NULL, NULL)
GO

INSERT INTO [Master].[UserProfile] ([UserId], [FirstName], [Gender], [LastName], [DateOfBirth], [ProfilePicture], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'46', NULL, NULL, NULL, NULL, NULL, N'admin123', N'2025-01-30 09:53:10.369503', NULL, NULL)
GO

INSERT INTO [Master].[UserProfile] ([UserId], [FirstName], [Gender], [LastName], [DateOfBirth], [ProfilePicture], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'47', NULL, NULL, NULL, NULL, NULL, N'admin123', N'2025-01-30 09:56:44.341013', NULL, NULL)
GO

INSERT INTO [Master].[UserProfile] ([UserId], [FirstName], [Gender], [LastName], [DateOfBirth], [ProfilePicture], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'48', NULL, NULL, NULL, NULL, NULL, N'admin123', N'2025-01-31 18:10:14.342296', NULL, NULL)
GO


-- ----------------------------
-- Table structure for Users
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Master].[Users]') AND type IN ('U'))
	DROP TABLE [Master].[Users]
GO

CREATE TABLE [Master].[Users] (
  [ID] bigint  IDENTITY(1,1) NOT NULL,
  [Username] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [RoleId] bigint  NOT NULL,
  [IsActive] bit DEFAULT 1 NOT NULL,
  [Email] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Password] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [PhoneNumber] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CreatedDate] datetime2(6)  NOT NULL,
  [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [UpdatedDate] datetime2(6)  NULL
)
GO

ALTER TABLE [Master].[Users] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Users
-- ----------------------------
SET IDENTITY_INSERT [Master].[Users] ON
GO

INSERT INTO [Master].[Users] ([ID], [Username], [RoleId], [IsActive], [Email], [Password], [PhoneNumber], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'41', N'admin123', N'1', N'1', N'as112d2@sa5111.122', N'$2a$10$AUPeboLa/gjpQb7TJ2AqZOshw.yVt/I5OdiEbwPtfCVWoTawv7YPi', N'08123456789', N'system', N'2025-01-28 14:24:42.584069', N'admin123', N'2025-01-31 16:18:49.392543')
GO

INSERT INTO [Master].[Users] ([ID], [Username], [RoleId], [IsActive], [Email], [Password], [PhoneNumber], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'42', N'customer001', N'2', N'1', N'admin@baru.donk0211111', N'$2a$10$jreZu7OlIMyCkl7Y/ehvy.bvCzGTbkUO5Kc13K9syBcNHjhPOAi5a', N'0812345678911', N'system', N'2025-01-28 14:38:25.322803', N'admin123', N'2025-01-28 17:18:35.958824')
GO

INSERT INTO [Master].[Users] ([ID], [Username], [RoleId], [IsActive], [Email], [Password], [PhoneNumber], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'43', N'customer002', N'2', N'1', N'customer@lupa.com02', N'$2a$10$sH7DXBZAHc.vyfY.1v12EuL214AZ0HR/hapYnYFi.Ot3mf.SN/eIu', NULL, N'system', N'2025-01-29 21:02:28.524527', NULL, N'2025-01-29 21:02:28.524527')
GO

INSERT INTO [Master].[Users] ([ID], [Username], [RoleId], [IsActive], [Email], [Password], [PhoneNumber], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'44', N'customer003', N'2', N'1', N'customer@lupa.com03', N'$2a$10$7WhUtEKWTlclB2ZnD9UVje4F2o0wdxb7Bmxa40D5p2nUbe8xTwRPW', NULL, N'system', N'2025-01-29 21:11:33.835508', NULL, N'2025-01-29 21:11:33.835508')
GO

INSERT INTO [Master].[Users] ([ID], [Username], [RoleId], [IsActive], [Email], [Password], [PhoneNumber], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'45', N'em.chepe@gmail.com', N'1', N'1', N'kucing.terbang@gmail.com', N'111', NULL, N'admin123', N'2025-01-30 00:33:02.040174', NULL, N'2025-01-30 00:33:02.040174')
GO

INSERT INTO [Master].[Users] ([ID], [Username], [RoleId], [IsActive], [Email], [Password], [PhoneNumber], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'46', N'v-advo-fFadVrw6O', N'1', N'1', N'123@fasd.gmail', N'111', NULL, N'admin123', N'2025-01-30 09:53:10.247083', NULL, N'2025-01-30 09:53:10.247083')
GO

INSERT INTO [Master].[Users] ([ID], [Username], [RoleId], [IsActive], [Email], [Password], [PhoneNumber], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'47', N'v-gold-rP4JFs2rR', N'1', N'1', N'akununtukgame2@gmail.com', N'111', NULL, N'admin123', N'2025-01-30 09:56:44.335990', NULL, N'2025-01-30 09:56:44.335990')
GO

INSERT INTO [Master].[Users] ([ID], [Username], [RoleId], [IsActive], [Email], [Password], [PhoneNumber], [CreatedBy], [CreatedDate], [UpdatedBy], [UpdatedDate]) VALUES (N'48', N'paul123', N'1', N'1', N'qwe@123.zx', N'111', NULL, N'admin123', N'2025-01-31 18:10:14.290951', NULL, N'2025-01-31 18:10:14.290951')
GO

SET IDENTITY_INSERT [Master].[Users] OFF
GO


-- ----------------------------
-- Auto increment value for DefaultRolePermission
-- ----------------------------
DBCC CHECKIDENT ('[Master].[DefaultRolePermission]', RESEED, 11)
GO


-- ----------------------------
-- Indexes structure for table DefaultRolePermission
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [UK4irn80wulqgbphqpa4m123wr8]
ON [Master].[DefaultRolePermission] (
  [RoleId] ASC,
  [PermissionId] ASC
)
WHERE ([RoleId] IS NOT NULL AND [PermissionId] IS NOT NULL)
GO


-- ----------------------------
-- Primary Key structure for table DefaultRolePermission
-- ----------------------------
ALTER TABLE [Master].[DefaultRolePermission] ADD CONSTRAINT [PK__DefaultR__3213E83F0BA82B66] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for Permissions
-- ----------------------------
DBCC CHECKIDENT ('[Master].[Permissions]', RESEED, 9)
GO


-- ----------------------------
-- Indexes structure for table Permissions
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [Permissions_name_index]
ON [Master].[Permissions] (
  [Name] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table Permissions
-- ----------------------------
ALTER TABLE [Master].[Permissions] ADD CONSTRAINT [PK__Permissi__3214EC270387CB36] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for Role
-- ----------------------------
DBCC CHECKIDENT ('[Master].[Role]', RESEED, 3)
GO


-- ----------------------------
-- Uniques structure for table Role
-- ----------------------------
ALTER TABLE [Master].[Role] ADD CONSTRAINT [UK3cum603hb0nklewavkwrw0cfy] UNIQUE NONCLUSTERED ([Name] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table Role
-- ----------------------------
ALTER TABLE [Master].[Role] ADD CONSTRAINT [PK__Role__3214EC27310B0BD3] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for UserAddress
-- ----------------------------
DBCC CHECKIDENT ('[Master].[UserAddress]', RESEED, 36)
GO


-- ----------------------------
-- Primary Key structure for table UserAddress
-- ----------------------------
ALTER TABLE [Master].[UserAddress] ADD CONSTRAINT [PK__UserAddr__3214EC27FB4ACEDA] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Uniques structure for table UserPermissions
-- ----------------------------
ALTER TABLE [Master].[UserPermissions] ADD CONSTRAINT [Uniq_User_Permissions] UNIQUE NONCLUSTERED ([UserId] ASC, [AccessId] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table UserProfile
-- ----------------------------
ALTER TABLE [Master].[UserProfile] ADD CONSTRAINT [PK__UserProf__1788CC4C35E37751] PRIMARY KEY CLUSTERED ([UserId])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for Users
-- ----------------------------
DBCC CHECKIDENT ('[Master].[Users]', RESEED, 48)
GO


-- ----------------------------
-- Uniques structure for table Users
-- ----------------------------
ALTER TABLE [Master].[Users] ADD CONSTRAINT [UK9cw87ffd4i55ki0qpkwu63er] UNIQUE NONCLUSTERED ([Username] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

ALTER TABLE [Master].[Users] ADD CONSTRAINT [UKjdfr6kjrxekx1j5vrr77rp44t] UNIQUE NONCLUSTERED ([Email] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table Users
-- ----------------------------
ALTER TABLE [Master].[Users] ADD CONSTRAINT [PK__Users__3214EC27554C8B17] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Foreign Keys structure for table DefaultRolePermission
-- ----------------------------
ALTER TABLE [Master].[DefaultRolePermission] ADD CONSTRAINT [FKmvi997f3yw48oidc2djqurs8i] FOREIGN KEY ([PermissionId]) REFERENCES [Master].[Permissions] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [Master].[DefaultRolePermission] ADD CONSTRAINT [FKhb4q497b52daw3torpomjy3kj] FOREIGN KEY ([RoleId]) REFERENCES [Master].[Role] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table UserAddress
-- ----------------------------
ALTER TABLE [Master].[UserAddress] ADD CONSTRAINT [FK_Address_User] FOREIGN KEY ([UserId]) REFERENCES [Master].[Users] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table UserPermissions
-- ----------------------------
ALTER TABLE [Master].[UserPermissions] ADD CONSTRAINT [FKg7q2xx46k15f29eub7fexagr0] FOREIGN KEY ([AccessId]) REFERENCES [Master].[Permissions] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [Master].[UserPermissions] ADD CONSTRAINT [FKby6cehnqxe6wovy6h3mux8vru] FOREIGN KEY ([UserId]) REFERENCES [Master].[Users] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table UserProfile
-- ----------------------------
ALTER TABLE [Master].[UserProfile] ADD CONSTRAINT [FK_Profile_User] FOREIGN KEY ([UserId]) REFERENCES [Master].[Users] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table Users
-- ----------------------------
ALTER TABLE [Master].[Users] ADD CONSTRAINT [FK_User_Role] FOREIGN KEY ([RoleId]) REFERENCES [Master].[Role] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

