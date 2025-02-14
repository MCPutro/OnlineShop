/*
 Navicat Premium Data Transfer

 Source Server         : MS SQL Server
 Source Server Type    : SQL Server
 Source Server Version : 15002000 (15.00.2000)
 Source Host           : localhost:1433
 Source Catalog        : OnlineShop
 Source Schema         : Transactions

 Target Server Type    : SQL Server
 Target Server Version : 15002000 (15.00.2000)
 File Encoding         : 65001

 Date: 14/02/2025 09:06:54
*/


-- ----------------------------
-- Table structure for Carts
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Transactions].[Carts]') AND type IN ('U'))
	DROP TABLE [Transactions].[Carts]
GO

CREATE TABLE [Transactions].[Carts] (
  [ID] bigint  IDENTITY(1,1) NOT NULL,
  [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CreatedDate] datetime2(6)  NOT NULL,
  [IsAvailable] bit DEFAULT 1 NOT NULL,
  [Price] float(53)  NOT NULL,
  [ProductId] bigint  NOT NULL,
  [Quantity] int  NOT NULL,
  [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [UpdatedDate] datetime2(6)  NULL,
  [UserId] bigint  NOT NULL
)
GO

ALTER TABLE [Transactions].[Carts] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Carts
-- ----------------------------
SET IDENTITY_INSERT [Transactions].[Carts] ON
GO

INSERT INTO [Transactions].[Carts] ([ID], [CreatedBy], [CreatedDate], [IsAvailable], [Price], [ProductId], [Quantity], [UpdatedBy], [UpdatedDate], [UserId]) VALUES (N'3', N'system', N'2025-02-12 14:04:54.377720', N'1', N'80.7038985541801', N'2', N'5', N'system', N'2025-02-12 14:46:13.165961', N'1')
GO

INSERT INTO [Transactions].[Carts] ([ID], [CreatedBy], [CreatedDate], [IsAvailable], [Price], [ProductId], [Quantity], [UpdatedBy], [UpdatedDate], [UserId]) VALUES (N'4', N'system', N'2025-02-12 14:57:09.054546', N'1', N'135.89275476636', N'3', N'3', NULL, NULL, N'1')
GO

INSERT INTO [Transactions].[Carts] ([ID], [CreatedBy], [CreatedDate], [IsAvailable], [Price], [ProductId], [Quantity], [UpdatedBy], [UpdatedDate], [UserId]) VALUES (N'7', N'system', N'2025-02-12 17:09:34.182523', N'1', N'507.480173049904', N'1', N'3', N'system', N'2025-02-12 17:09:45.408497', N'1')
GO

INSERT INTO [Transactions].[Carts] ([ID], [CreatedBy], [CreatedDate], [IsAvailable], [Price], [ProductId], [Quantity], [UpdatedBy], [UpdatedDate], [UserId]) VALUES (N'11', N'system', N'2025-02-13 16:27:51.433671', N'1', N'135.89275476636', N'3', N'2', NULL, NULL, N'2')
GO

SET IDENTITY_INSERT [Transactions].[Carts] OFF
GO


-- ----------------------------
-- Table structure for OrderItems
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Transactions].[OrderItems]') AND type IN ('U'))
	DROP TABLE [Transactions].[OrderItems]
GO

CREATE TABLE [Transactions].[OrderItems] (
  [ID] bigint  IDENTITY(1,1) NOT NULL,
  [ProductId] bigint  NULL,
  [ProductPrice] float(53)  NULL,
  [Quantity] int  NULL,
  [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CreatedDate] datetime2(6)  NOT NULL,
  [SubTotalPrice] float(53)  NULL,
  [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [UpdatedDate] datetime2(6)  NULL,
  [OrderId] bigint  NOT NULL
)
GO

ALTER TABLE [Transactions].[OrderItems] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of OrderItems
-- ----------------------------
SET IDENTITY_INSERT [Transactions].[OrderItems] ON
GO

INSERT INTO [Transactions].[OrderItems] ([ID], [ProductId], [ProductPrice], [Quantity], [CreatedBy], [CreatedDate], [SubTotalPrice], [UpdatedBy], [UpdatedDate], [OrderId]) VALUES (N'5', N'3', N'135.89275476636', N'4', N'system', N'2025-02-13 16:25:56.096069', N'543.571019065441', NULL, NULL, N'60')
GO

INSERT INTO [Transactions].[OrderItems] ([ID], [ProductId], [ProductPrice], [Quantity], [CreatedBy], [CreatedDate], [SubTotalPrice], [UpdatedBy], [UpdatedDate], [OrderId]) VALUES (N'6', N'1', N'135.89275476636', N'4', N'system', N'2025-02-13 16:25:56.102073', N'543.571019065441', NULL, NULL, N'60')
GO

INSERT INTO [Transactions].[OrderItems] ([ID], [ProductId], [ProductPrice], [Quantity], [CreatedBy], [CreatedDate], [SubTotalPrice], [UpdatedBy], [UpdatedDate], [OrderId]) VALUES (N'7', N'3', N'135.89275476636', N'4', N'system', N'2025-02-13 16:25:56.096069', N'543.571019065441', NULL, NULL, N'61')
GO

INSERT INTO [Transactions].[OrderItems] ([ID], [ProductId], [ProductPrice], [Quantity], [CreatedBy], [CreatedDate], [SubTotalPrice], [UpdatedBy], [UpdatedDate], [OrderId]) VALUES (N'8', N'1', N'135.89275476636', N'4', N'system', N'2025-02-13 16:25:56.102073', N'543.571019065441', NULL, NULL, N'62')
GO

INSERT INTO [Transactions].[OrderItems] ([ID], [ProductId], [ProductPrice], [Quantity], [CreatedBy], [CreatedDate], [SubTotalPrice], [UpdatedBy], [UpdatedDate], [OrderId]) VALUES (N'9', N'1', N'135.89275476636', N'4', N'system', N'2025-02-13 16:25:56.102073', N'543.571019065441', NULL, NULL, N'63')
GO

INSERT INTO [Transactions].[OrderItems] ([ID], [ProductId], [ProductPrice], [Quantity], [CreatedBy], [CreatedDate], [SubTotalPrice], [UpdatedBy], [UpdatedDate], [OrderId]) VALUES (N'10', N'1', N'135.89275476636', N'4', N'system', N'2025-02-13 16:25:56.102073', N'543.571019065441', NULL, NULL, N'64')
GO

INSERT INTO [Transactions].[OrderItems] ([ID], [ProductId], [ProductPrice], [Quantity], [CreatedBy], [CreatedDate], [SubTotalPrice], [UpdatedBy], [UpdatedDate], [OrderId]) VALUES (N'11', N'1', N'135.89275476636', N'4', N'system', N'2025-02-13 16:25:56.102073', N'543.571019065441', NULL, NULL, N'64')
GO

SET IDENTITY_INSERT [Transactions].[OrderItems] OFF
GO


-- ----------------------------
-- Table structure for Orders
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Transactions].[Orders]') AND type IN ('U'))
	DROP TABLE [Transactions].[Orders]
GO

CREATE TABLE [Transactions].[Orders] (
  [ID] bigint  IDENTITY(1,1) NOT NULL,
  [AddressId] bigint  NOT NULL,
  [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CreatedDate] datetime2(6)  NOT NULL,
  [OrderDate] date  NOT NULL,
  [OrderStatus] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [TotalPrice] float(53)  NOT NULL,
  [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [UpdatedDate] datetime2(6)  NULL,
  [UserId] bigint  NOT NULL
)
GO

ALTER TABLE [Transactions].[Orders] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Orders
-- ----------------------------
SET IDENTITY_INSERT [Transactions].[Orders] ON
GO

INSERT INTO [Transactions].[Orders] ([ID], [AddressId], [CreatedBy], [CreatedDate], [OrderDate], [OrderStatus], [TotalPrice], [UpdatedBy], [UpdatedDate], [UserId]) VALUES (N'60', N'1', N'system', N'2025-02-13 16:25:56.027072', N'2025-02-13', N'PENDING', N'1087.14203813088', NULL, NULL, N'2')
GO

INSERT INTO [Transactions].[Orders] ([ID], [AddressId], [CreatedBy], [CreatedDate], [OrderDate], [OrderStatus], [TotalPrice], [UpdatedBy], [UpdatedDate], [UserId]) VALUES (N'61', N'1', N'system', N'2025-02-13 16:25:56.027072', N'2025-02-13', N'PENDING', N'1087.14203813088', NULL, NULL, N'1')
GO

INSERT INTO [Transactions].[Orders] ([ID], [AddressId], [CreatedBy], [CreatedDate], [OrderDate], [OrderStatus], [TotalPrice], [UpdatedBy], [UpdatedDate], [UserId]) VALUES (N'62', N'1', N'system', N'2025-02-13 16:25:56.027072', N'2025-02-13', N'PENDING', N'1087.14203813088', NULL, NULL, N'1')
GO

INSERT INTO [Transactions].[Orders] ([ID], [AddressId], [CreatedBy], [CreatedDate], [OrderDate], [OrderStatus], [TotalPrice], [UpdatedBy], [UpdatedDate], [UserId]) VALUES (N'63', N'1', N'system', N'2025-02-13 16:25:56.027072', N'2025-02-13', N'PENDING', N'1087.14203813088', NULL, NULL, N'1')
GO

INSERT INTO [Transactions].[Orders] ([ID], [AddressId], [CreatedBy], [CreatedDate], [OrderDate], [OrderStatus], [TotalPrice], [UpdatedBy], [UpdatedDate], [UserId]) VALUES (N'64', N'1', N'system', N'2025-02-13 16:25:56.027072', N'2025-02-13', N'PENDING', N'1087.14203813088', NULL, NULL, N'1')
GO

SET IDENTITY_INSERT [Transactions].[Orders] OFF
GO


-- ----------------------------
-- Auto increment value for Carts
-- ----------------------------
DBCC CHECKIDENT ('[Transactions].[Carts]', RESEED, 11)
GO


-- ----------------------------
-- Indexes structure for table Carts
-- ----------------------------
CREATE NONCLUSTERED INDEX [IDX_Cart_UserId]
ON [Transactions].[Carts] (
  [UserId] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table Carts
-- ----------------------------
ALTER TABLE [Transactions].[Carts] ADD CONSTRAINT [PK__Carts__3214EC27D95EC0F0] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for OrderItems
-- ----------------------------
DBCC CHECKIDENT ('[Transactions].[OrderItems]', RESEED, 11)
GO


-- ----------------------------
-- Primary Key structure for table OrderItems
-- ----------------------------
ALTER TABLE [Transactions].[OrderItems] ADD CONSTRAINT [PK__OrderIte__3214EC27556B5A12] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for Orders
-- ----------------------------
DBCC CHECKIDENT ('[Transactions].[Orders]', RESEED, 64)
GO


-- ----------------------------
-- Primary Key structure for table Orders
-- ----------------------------
ALTER TABLE [Transactions].[Orders] ADD CONSTRAINT [PK__Orders__3214EC27CA57602D] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Foreign Keys structure for table OrderItems
-- ----------------------------
ALTER TABLE [Transactions].[OrderItems] ADD CONSTRAINT [FKn7f5nktjm994oc3n489mllyix] FOREIGN KEY ([OrderId]) REFERENCES [Transactions].[Orders] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

