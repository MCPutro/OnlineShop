/*
 Navicat Premium Data Transfer

 Source Server         : MS SQL Server
 Source Server Type    : SQL Server
 Source Server Version : 15002000 (15.00.2000)
 Source Host           : localhost:1433
 Source Catalog        : OnlineShop
 Source Schema         : Product

 Target Server Type    : SQL Server
 Target Server Version : 15002000 (15.00.2000)
 File Encoding         : 65001

 Date: 31/01/2025 19:13:40
*/


-- ----------------------------
-- Table structure for Categories
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Product].[Categories]') AND type IN ('U'))
	DROP TABLE [Product].[Categories]
GO

CREATE TABLE [Product].[Categories] (
  [ID] bigint  IDENTITY(1,1) NOT NULL,
  [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CreatedDate] datetime2(6)  NOT NULL,
  [IsActive] bit DEFAULT 1 NOT NULL,
  [Name] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [UpdatedDate] datetime2(6)  NULL
)
GO

ALTER TABLE [Product].[Categories] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Categories
-- ----------------------------
SET IDENTITY_INSERT [Product].[Categories] ON
GO

INSERT INTO [Product].[Categories] ([ID], [CreatedBy], [CreatedDate], [IsActive], [Name], [UpdatedBy], [UpdatedDate]) VALUES (N'1', N'oleh sistem', N'2025-01-20 14:18:01.886932', N'1', N'sayuran1', NULL, NULL)
GO

INSERT INTO [Product].[Categories] ([ID], [CreatedBy], [CreatedDate], [IsActive], [Name], [UpdatedBy], [UpdatedDate]) VALUES (N'2', N'oleh sistem', N'2025-01-20 14:18:01.994738', N'0', N'mainan anak2 kecil 1', N'oleh sistem', N'2025-01-31 11:20:58.713908')
GO

INSERT INTO [Product].[Categories] ([ID], [CreatedBy], [CreatedDate], [IsActive], [Name], [UpdatedBy], [UpdatedDate]) VALUES (N'3', N'oleh sistem', N'2025-01-20 14:18:02.007704', N'1', N'sayuran3', NULL, NULL)
GO

INSERT INTO [Product].[Categories] ([ID], [CreatedBy], [CreatedDate], [IsActive], [Name], [UpdatedBy], [UpdatedDate]) VALUES (N'4', N'oleh sistem', N'2025-01-20 14:18:02.020670', N'1', N'sayuran', NULL, NULL)
GO

INSERT INTO [Product].[Categories] ([ID], [CreatedBy], [CreatedDate], [IsActive], [Name], [UpdatedBy], [UpdatedDate]) VALUES (N'7', N'oleh sistem', N'2025-01-20 14:52:16.002713', N'1', N'alat makan', NULL, NULL)
GO

INSERT INTO [Product].[Categories] ([ID], [CreatedBy], [CreatedDate], [IsActive], [Name], [UpdatedBy], [UpdatedDate]) VALUES (N'8', N'oleh sistem', N'2025-01-22 16:40:00.660736', N'1', N'mainan', NULL, NULL)
GO

INSERT INTO [Product].[Categories] ([ID], [CreatedBy], [CreatedDate], [IsActive], [Name], [UpdatedBy], [UpdatedDate]) VALUES (N'9', N'oleh sistem', N'2025-01-31 11:08:58.632822', N'1', N'alat makan 1', NULL, NULL)
GO

INSERT INTO [Product].[Categories] ([ID], [CreatedBy], [CreatedDate], [IsActive], [Name], [UpdatedBy], [UpdatedDate]) VALUES (N'10', N'oleh sistem', N'2025-01-31 11:10:47.135103', N'1', N'alat makan 2', NULL, NULL)
GO

SET IDENTITY_INSERT [Product].[Categories] OFF
GO


-- ----------------------------
-- Table structure for ProductCategory
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Product].[ProductCategory]') AND type IN ('U'))
	DROP TABLE [Product].[ProductCategory]
GO

CREATE TABLE [Product].[ProductCategory] (
  [ProductId] bigint  NOT NULL,
  [CategoryId] bigint  NOT NULL
)
GO

ALTER TABLE [Product].[ProductCategory] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of ProductCategory
-- ----------------------------

-- ----------------------------
-- Table structure for Products
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Product].[Products]') AND type IN ('U'))
	DROP TABLE [Product].[Products]
GO

CREATE TABLE [Product].[Products] (
  [ID] bigint  IDENTITY(1,1) NOT NULL,
  [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CreatedDate] datetime2(6)  NOT NULL,
  [Description] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [IsActive] bit DEFAULT 1 NOT NULL,
  [Name] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Price] float(53)  NOT NULL,
  [Stock] int  NOT NULL,
  [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [UpdatedDate] datetime2(6)  NULL,
  [version] int  NOT NULL
)
GO

ALTER TABLE [Product].[Products] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Products
-- ----------------------------
SET IDENTITY_INSERT [Product].[Products] ON
GO

SET IDENTITY_INSERT [Product].[Products] OFF
GO


-- ----------------------------
-- Auto increment value for Categories
-- ----------------------------
DBCC CHECKIDENT ('[Product].[Categories]', RESEED, 10)
GO


-- ----------------------------
-- Uniques structure for table Categories
-- ----------------------------
ALTER TABLE [Product].[Categories] ADD CONSTRAINT [UK5bu41k67tceb0gw9c047he2ki] UNIQUE NONCLUSTERED ([Name] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table Categories
-- ----------------------------
ALTER TABLE [Product].[Categories] ADD CONSTRAINT [PK__Categori__3214EC270C1830E7] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Uniques structure for table ProductCategory
-- ----------------------------
ALTER TABLE [Product].[ProductCategory] ADD CONSTRAINT [Uniq_Product_Category] UNIQUE NONCLUSTERED ([ProductId] ASC, [CategoryId] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for Products
-- ----------------------------
DBCC CHECKIDENT ('[Product].[Products]', RESEED, 1)
GO


-- ----------------------------
-- Primary Key structure for table Products
-- ----------------------------
ALTER TABLE [Product].[Products] ADD CONSTRAINT [PK__Products__3214EC27971A7922] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Foreign Keys structure for table ProductCategory
-- ----------------------------
ALTER TABLE [Product].[ProductCategory] ADD CONSTRAINT [FKq37t3nyhe79gc8dly7sxrshjd] FOREIGN KEY ([CategoryId]) REFERENCES [Product].[Categories] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [Product].[ProductCategory] ADD CONSTRAINT [FKi1tmx8x40yl63qb29jr1dsedp] FOREIGN KEY ([ProductId]) REFERENCES [Product].[Products] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

