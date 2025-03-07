/*
 Navicat Premium Dump SQL

 Source Server         : SQL Server
 Source Server Type    : SQL Server
 Source Server Version : 15004430 (15.00.4430)
 Source Host           : localhost:1433
 Source Catalog        : OnlineShop
 Source Schema         : Product

 Target Server Type    : SQL Server
 Target Server Version : 15004430 (15.00.4430)
 File Encoding         : 65001

 Date: 07/03/2025 10:01:26
*/


-- ----------------------------
-- Table structure for Categories
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Product].[Categories]') AND type IN ('U'))
	DROP TABLE [Product].[Categories]
GO

CREATE TABLE [Product].[Categories] (
  [IsActive] bit DEFAULT 1 NOT NULL,
  [CreatedDate] datetime2(6)  NOT NULL,
  [ID] bigint  IDENTITY(1,1) NOT NULL,
  [UpdatedDate] datetime2(6)  NULL,
  [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Name] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL
)
GO

ALTER TABLE [Product].[Categories] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Categories
-- ----------------------------
SET IDENTITY_INSERT [Product].[Categories] ON
GO

INSERT INTO [Product].[Categories] ([IsActive], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Name], [UpdatedBy]) VALUES (N'1', N'2025-02-08 17:51:59.004465', N'1', NULL, N'oleh sistem', N'Electronics', NULL)
GO

INSERT INTO [Product].[Categories] ([IsActive], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Name], [UpdatedBy]) VALUES (N'1', N'2025-02-08 17:51:59.108595', N'3', NULL, N'oleh sistem', N'Books', NULL)
GO

INSERT INTO [Product].[Categories] ([IsActive], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Name], [UpdatedBy]) VALUES (N'1', N'2025-02-08 17:51:59.110589', N'4', NULL, N'oleh sistem', N'Fashion', NULL)
GO

INSERT INTO [Product].[Categories] ([IsActive], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Name], [UpdatedBy]) VALUES (N'1', N'2025-02-08 17:51:59.112585', N'5', NULL, N'oleh sistem', N'Sports & Outdoors', NULL)
GO

INSERT INTO [Product].[Categories] ([IsActive], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Name], [UpdatedBy]) VALUES (N'1', N'2025-02-08 17:51:59.113581', N'6', NULL, N'oleh sistem', N'Beauty & Health', NULL)
GO

INSERT INTO [Product].[Categories] ([IsActive], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Name], [UpdatedBy]) VALUES (N'1', N'2025-02-08 17:51:59.115390', N'7', NULL, N'oleh sistem', N'Toys & Games', NULL)
GO

INSERT INTO [Product].[Categories] ([IsActive], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Name], [UpdatedBy]) VALUES (N'1', N'2025-02-08 17:51:59.117390', N'8', NULL, N'oleh sistem', N'Groceries', NULL)
GO

INSERT INTO [Product].[Categories] ([IsActive], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Name], [UpdatedBy]) VALUES (N'1', N'2025-02-08 17:51:59.119385', N'9', NULL, N'oleh sistem', N'Automotive', NULL)
GO

INSERT INTO [Product].[Categories] ([IsActive], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Name], [UpdatedBy]) VALUES (N'1', N'2025-02-08 17:51:59.120382', N'10', NULL, N'oleh sistem', N'Furniture', NULL)
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
  [CategoryId] bigint  NOT NULL,
  [ProductId] bigint  NOT NULL
)
GO

ALTER TABLE [Product].[ProductCategory] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of ProductCategory
-- ----------------------------
INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'10', N'1')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'7', N'2')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'3')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'10', N'3')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'4')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'5')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'5')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'10', N'5')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'6')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'7')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'3', N'7')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'8')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'10', N'8')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'3', N'9')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'9')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'9')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'9', N'10')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'11')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'6', N'11')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'11')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'12')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'12')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'13')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'13')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'7', N'13')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'14')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'10', N'14')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'15')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'9', N'15')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'10', N'15')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'16')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'4', N'16')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'16')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'17')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'7', N'17')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'18')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'19')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'10', N'20')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'21')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'22')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'9', N'22')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'4', N'23')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'23')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'24')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'4', N'24')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'7', N'25')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'3', N'26')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'26')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'27')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'7', N'27')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'28')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'4', N'29')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'9', N'30')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'3', N'31')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'32')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'33')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'34')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'35')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'6', N'35')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'36')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'3', N'36')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'6', N'36')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'4', N'37')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'9', N'37')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'10', N'37')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'38')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'38')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'4', N'39')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'7', N'39')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'9', N'39')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'40')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'3', N'40')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'41')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'3', N'42')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'4', N'43')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'6', N'43')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'7', N'43')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'44')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'7', N'44')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'45')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'45')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'10', N'45')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'46')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'9', N'46')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'4', N'47')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'10', N'47')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'48')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'3', N'48')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'3', N'49')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'49')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'6', N'49')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'50')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'9', N'50')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'51')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'52')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'52')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'53')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'7', N'53')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'9', N'53')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'9', N'54')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'55')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'3', N'56')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'6', N'57')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'7', N'57')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'57')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'58')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'10', N'58')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'59')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'60')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'3', N'60')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'6', N'60')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'61')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'4', N'62')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'4', N'63')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'6', N'63')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'3', N'64')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'6', N'64')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'9', N'64')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'65')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'6', N'65')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'10', N'66')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'7', N'67')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'67')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'68')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'7', N'68')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'10', N'68')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'3', N'69')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'6', N'69')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'4', N'70')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'70')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'10', N'70')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'3', N'71')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'71')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'7', N'72')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'10', N'73')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'7', N'74')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'74')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'75')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'76')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'77')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'77')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'4', N'78')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'4', N'79')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'7', N'79')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'7', N'80')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'4', N'81')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'82')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'3', N'83')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'3', N'84')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'84')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'6', N'85')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'6', N'86')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'3', N'87')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'6', N'87')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'88')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'4', N'89')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'89')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'4', N'90')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'6', N'90')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'91')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'91')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'92')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'6', N'92')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'92')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'93')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'3', N'93')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'93')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'4', N'94')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'94')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'95')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'95')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'9', N'95')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'96')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'96')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'6', N'96')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'97')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'98')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'3', N'98')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'8', N'98')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'3', N'99')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'99')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'6', N'100')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'101')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'5', N'101')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'7', N'101')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'1', N'102')
GO

INSERT INTO [Product].[ProductCategory] ([CategoryId], [ProductId]) VALUES (N'6', N'102')
GO


-- ----------------------------
-- Table structure for Products
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[Product].[Products]') AND type IN ('U'))
	DROP TABLE [Product].[Products]
GO

CREATE TABLE [Product].[Products] (
  [IsActive] bit DEFAULT 1 NOT NULL,
  [Price] float(53)  NOT NULL,
  [Stock] int  NOT NULL,
  [version] int  NOT NULL,
  [CreatedDate] datetime2(6)  NOT NULL,
  [ID] bigint  IDENTITY(1,1) NOT NULL,
  [UpdatedDate] datetime2(6)  NULL,
  [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Description] varchar(500) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [Name] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [SKU] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL
)
GO

ALTER TABLE [Product].[Products] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Products
-- ----------------------------
SET IDENTITY_INSERT [Product].[Products] ON
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'602574', N'9', N'3', N'2025-02-08 17:51:59.187695', N'1', N'2025-03-06 19:22:14.279854', N'oleh sistem', N'Description for Product 1.', N'Product 1', N'SKU10000', N'customer001')
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'360415', N'35', N'2', N'2025-02-08 17:51:59.218647', N'2', N'2025-03-06 20:46:47.759172', N'oleh sistem', N'Description for Product 2.', N'Product 2', N'SKU10001', N'customer001')
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'626315', N'118', N'9', N'2025-02-08 17:51:59.224723', N'3', N'2025-02-13 16:25:55.955865', N'oleh sistem', N'Description for Product 3.', N'Product 3', N'SKU10002', N'customer001')
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'392940', N'6', N'0', N'2025-02-08 17:51:59.231704', N'4', NULL, N'oleh sistem', N'Description for Product 4.', N'Product 4', N'SKU10003', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'765913', N'23', N'0', N'2025-02-08 17:51:59.237689', N'5', NULL, N'oleh sistem', N'Description for Product 5.', N'Product 5', N'SKU10004', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'380480', N'101', N'0', N'2025-02-08 17:51:59.244668', N'6', NULL, N'oleh sistem', N'Description for Product 6.', N'Product 6', N'SKU10005', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'84666', N'80', N'0', N'2025-02-08 17:51:59.251650', N'7', NULL, N'oleh sistem', N'Description for Product 7.', N'Product 7', N'SKU10006', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'562160', N'105', N'0', N'2025-02-08 17:51:59.258631', N'8', NULL, N'oleh sistem', N'Description for Product 8.', N'Product 8', N'SKU10007', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'12217', N'27', N'0', N'2025-02-08 17:51:59.264615', N'9', NULL, N'oleh sistem', N'Description for Product 9.', N'Product 9', N'SKU10008', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'424136', N'106', N'0', N'2025-02-08 17:51:59.272593', N'10', NULL, N'oleh sistem', N'Description for Product 10.', N'Product 10', N'SKU10009', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'2535', N'206', N'0', N'2025-02-08 17:51:59.277584', N'11', NULL, N'oleh sistem', N'Description for Product 11.', N'Product 11', N'SKU10010', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'567952', N'145', N'0', N'2025-02-08 17:51:59.284565', N'12', NULL, N'oleh sistem', N'Description for Product 12.', N'Product 12', N'SKU10011', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'577177', N'37', N'0', N'2025-02-08 17:51:59.290549', N'13', NULL, N'oleh sistem', N'Description for Product 13.', N'Product 13', N'SKU10012', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'462333', N'58', N'0', N'2025-02-08 17:51:59.297529', N'14', NULL, N'oleh sistem', N'Description for Product 14.', N'Product 14', N'SKU10013', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'216144', N'20', N'0', N'2025-02-08 17:51:59.304219', N'15', NULL, N'oleh sistem', N'Description for Product 15.', N'Product 15', N'SKU10014', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'762419', N'19', N'0', N'2025-02-08 17:51:59.310294', N'16', NULL, N'oleh sistem', N'Description for Product 16.', N'Product 16', N'SKU10015', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'387800', N'57', N'0', N'2025-02-08 17:51:59.317275', N'17', NULL, N'oleh sistem', N'Description for Product 17.', N'Product 17', N'SKU10016', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'759196', N'159', N'0', N'2025-02-08 17:51:59.323259', N'18', NULL, N'oleh sistem', N'Description for Product 18.', N'Product 18', N'SKU10017', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'781433', N'47', N'0', N'2025-02-08 17:51:59.328247', N'19', NULL, N'oleh sistem', N'Description for Product 19.', N'Product 19', N'SKU10018', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'410939', N'146', N'0', N'2025-02-08 17:51:59.333234', N'20', NULL, N'oleh sistem', N'Description for Product 20.', N'Product 20', N'SKU10019', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'92278', N'109', N'0', N'2025-02-08 17:51:59.338219', N'21', NULL, N'oleh sistem', N'Description for Product 21.', N'Product 21', N'SKU10020', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'448264', N'200', N'0', N'2025-02-08 17:51:59.343206', N'22', NULL, N'oleh sistem', N'Description for Product 22.', N'Product 22', N'SKU10021', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'395820', N'141', N'0', N'2025-02-08 17:51:59.348878', N'23', NULL, N'oleh sistem', N'Description for Product 23.', N'Product 23', N'SKU10022', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'847020', N'161', N'0', N'2025-02-08 17:51:59.354861', N'24', NULL, N'oleh sistem', N'Description for Product 24.', N'Product 24', N'SKU10023', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'893100', N'125', N'0', N'2025-02-08 17:51:59.359847', N'25', NULL, N'oleh sistem', N'Description for Product 25.', N'Product 25', N'SKU10024', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'503510', N'20', N'0', N'2025-02-08 17:51:59.364834', N'26', NULL, N'oleh sistem', N'Description for Product 26.', N'Product 26', N'SKU10025', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'737190', N'131', N'0', N'2025-02-08 17:51:59.370816', N'27', NULL, N'oleh sistem', N'Description for Product 27.', N'Product 27', N'SKU10026', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'380206', N'75', N'0', N'2025-02-08 17:51:59.377798', N'28', NULL, N'oleh sistem', N'Description for Product 28.', N'Product 28', N'SKU10027', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'327265', N'58', N'0', N'2025-02-08 17:51:59.383782', N'29', NULL, N'oleh sistem', N'Description for Product 29.', N'Product 29', N'SKU10028', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'636767', N'85', N'0', N'2025-02-08 17:51:59.388769', N'30', NULL, N'oleh sistem', N'Description for Product 30.', N'Product 30', N'SKU10029', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'898033', N'100', N'0', N'2025-02-08 17:51:59.393757', N'31', NULL, N'oleh sistem', N'Description for Product 31.', N'Product 31', N'SKU10030', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'555926', N'126', N'0', N'2025-02-08 17:51:59.398744', N'32', NULL, N'oleh sistem', N'Description for Product 32.', N'Product 32', N'SKU10031', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'624382', N'205', N'0', N'2025-02-08 17:51:59.403730', N'33', NULL, N'oleh sistem', N'Description for Product 33.', N'Product 33', N'SKU10032', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'232446', N'74', N'0', N'2025-02-08 17:51:59.408717', N'34', NULL, N'oleh sistem', N'Description for Product 34.', N'Product 34', N'SKU10033', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'9993', N'72', N'0', N'2025-02-08 17:51:59.412706', N'35', NULL, N'oleh sistem', N'Description for Product 35.', N'Product 35', N'SKU10034', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'755457', N'67', N'0', N'2025-02-08 17:51:59.418692', N'36', NULL, N'oleh sistem', N'Description for Product 36.', N'Product 36', N'SKU10035', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'888378', N'56', N'0', N'2025-02-08 17:51:59.425670', N'37', NULL, N'oleh sistem', N'Description for Product 37.', N'Product 37', N'SKU10036', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'479209', N'161', N'0', N'2025-02-08 17:51:59.431656', N'38', NULL, N'oleh sistem', N'Description for Product 38.', N'Product 38', N'SKU10037', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'187803', N'109', N'0', N'2025-02-08 17:51:59.438635', N'39', NULL, N'oleh sistem', N'Description for Product 39.', N'Product 39', N'SKU10038', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'688605', N'112', N'0', N'2025-02-08 17:51:59.445618', N'40', NULL, N'oleh sistem', N'Description for Product 40.', N'Product 40', N'SKU10039', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'775268', N'153', N'0', N'2025-02-08 17:51:59.450603', N'41', NULL, N'oleh sistem', N'Description for Product 41.', N'Product 41', N'SKU10040', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'732675', N'145', N'0', N'2025-02-08 17:51:59.454595', N'42', NULL, N'oleh sistem', N'Description for Product 42.', N'Product 42', N'SKU10041', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'736724', N'32', N'0', N'2025-02-08 17:51:59.459579', N'43', NULL, N'oleh sistem', N'Description for Product 43.', N'Product 43', N'SKU10042', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'678691', N'166', N'0', N'2025-02-08 17:51:59.464565', N'44', NULL, N'oleh sistem', N'Description for Product 44.', N'Product 44', N'SKU10043', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'845662', N'159', N'0', N'2025-02-08 17:51:59.469554', N'45', NULL, N'oleh sistem', N'Description for Product 45.', N'Product 45', N'SKU10044', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'294817', N'50', N'0', N'2025-02-08 17:51:59.474541', N'46', NULL, N'oleh sistem', N'Description for Product 46.', N'Product 46', N'SKU10045', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'739009', N'26', N'0', N'2025-02-08 17:51:59.478559', N'47', NULL, N'oleh sistem', N'Description for Product 47.', N'Product 47', N'SKU10046', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'466608', N'65', N'0', N'2025-02-08 17:51:59.483517', N'48', NULL, N'oleh sistem', N'Description for Product 48.', N'Product 48', N'SKU10047', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'528906', N'168', N'0', N'2025-02-08 17:51:59.488505', N'49', NULL, N'oleh sistem', N'Description for Product 49.', N'Product 49', N'SKU10048', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'114452', N'117', N'0', N'2025-02-08 17:51:59.493488', N'50', NULL, N'oleh sistem', N'Description for Product 50.', N'Product 50', N'SKU10049', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'140909', N'175', N'0', N'2025-02-08 17:51:59.498507', N'51', NULL, N'oleh sistem', N'Description for Product 51.', N'Product 51', N'SKU10050', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'403120', N'176', N'0', N'2025-02-08 17:51:59.503276', N'52', NULL, N'oleh sistem', N'Description for Product 52.', N'Product 52', N'SKU10051', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'175655', N'84', N'0', N'2025-02-08 17:51:59.507234', N'53', NULL, N'oleh sistem', N'Description for Product 53.', N'Product 53', N'SKU10052', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'396765', N'69', N'0', N'2025-02-08 17:51:59.513301', N'54', NULL, N'oleh sistem', N'Description for Product 54.', N'Product 54', N'SKU10053', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'725975', N'88', N'0', N'2025-02-08 17:51:59.518288', N'55', NULL, N'oleh sistem', N'Description for Product 55.', N'Product 55', N'SKU10054', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'772062', N'199', N'0', N'2025-02-08 17:51:59.522278', N'56', NULL, N'oleh sistem', N'Description for Product 56.', N'Product 56', N'SKU10055', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'261009', N'113', N'0', N'2025-02-08 17:51:59.526266', N'57', NULL, N'oleh sistem', N'Description for Product 57.', N'Product 57', N'SKU10056', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'519201', N'36', N'0', N'2025-02-08 17:51:59.531290', N'58', NULL, N'oleh sistem', N'Description for Product 58.', N'Product 58', N'SKU10057', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'221992', N'69', N'0', N'2025-02-08 17:51:59.536240', N'59', NULL, N'oleh sistem', N'Description for Product 59.', N'Product 59', N'SKU10058', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'89317', N'197', N'0', N'2025-02-08 17:51:59.540045', N'60', NULL, N'oleh sistem', N'Description for Product 60.', N'Product 60', N'SKU10059', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'649766', N'121', N'0', N'2025-02-08 17:51:59.545037', N'61', NULL, N'oleh sistem', N'Description for Product 61.', N'Product 61', N'SKU10060', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'327496', N'110', N'0', N'2025-02-08 17:51:59.548620', N'62', NULL, N'oleh sistem', N'Description for Product 62.', N'Product 62', N'SKU10061', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'871899', N'113', N'0', N'2025-02-08 17:51:59.553833', N'63', NULL, N'oleh sistem', N'Description for Product 63.', N'Product 63', N'SKU10062', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'685976', N'192', N'0', N'2025-02-08 17:51:59.558096', N'64', NULL, N'oleh sistem', N'Description for Product 64.', N'Product 64', N'SKU10063', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'524687', N'41', N'0', N'2025-02-08 17:51:59.565078', N'65', NULL, N'oleh sistem', N'Description for Product 65.', N'Product 65', N'SKU10064', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'146033', N'21', N'0', N'2025-02-08 17:51:59.569323', N'66', NULL, N'oleh sistem', N'Description for Product 66.', N'Product 66', N'SKU10065', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'603102', N'115', N'0', N'2025-02-08 17:51:59.573311', N'67', NULL, N'oleh sistem', N'Description for Product 67.', N'Product 67', N'SKU10066', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'855094', N'84', N'0', N'2025-02-08 17:51:59.578298', N'68', NULL, N'oleh sistem', N'Description for Product 68.', N'Product 68', N'SKU10067', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'397856', N'93', N'0', N'2025-02-08 17:51:59.583258', N'69', NULL, N'oleh sistem', N'Description for Product 69.', N'Product 69', N'SKU10068', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'374410', N'122', N'0', N'2025-02-08 17:51:59.587251', N'70', NULL, N'oleh sistem', N'Description for Product 70.', N'Product 70', N'SKU10069', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'104630', N'84', N'0', N'2025-02-08 17:51:59.593225', N'71', NULL, N'oleh sistem', N'Description for Product 71.', N'Product 71', N'SKU10070', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'513005', N'128', N'0', N'2025-02-08 17:51:59.597246', N'72', NULL, N'oleh sistem', N'Description for Product 72.', N'Product 72', N'SKU10071', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'652168', N'120', N'0', N'2025-02-08 17:51:59.601204', N'73', NULL, N'oleh sistem', N'Description for Product 73.', N'Product 73', N'SKU10072', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'461078', N'126', N'0', N'2025-02-08 17:51:59.605231', N'74', NULL, N'oleh sistem', N'Description for Product 74.', N'Product 74', N'SKU10073', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'372998', N'170', N'0', N'2025-02-08 17:51:59.610180', N'75', NULL, N'oleh sistem', N'Description for Product 75.', N'Product 75', N'SKU10074', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'642500', N'140', N'0', N'2025-02-08 17:51:59.615167', N'76', NULL, N'oleh sistem', N'Description for Product 76.', N'Product 76', N'SKU10075', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'2283', N'175', N'0', N'2025-02-08 17:51:59.619159', N'77', NULL, N'oleh sistem', N'Description for Product 77.', N'Product 77', N'SKU10076', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'578316', N'178', N'0', N'2025-02-08 17:51:59.623541', N'78', NULL, N'oleh sistem', N'Description for Product 78.', N'Product 78', N'SKU10077', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'625428', N'158', N'0', N'2025-02-08 17:51:59.627536', N'79', NULL, N'oleh sistem', N'Description for Product 79.', N'Product 79', N'SKU10078', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'396073', N'141', N'0', N'2025-02-08 17:51:59.631328', N'80', NULL, N'oleh sistem', N'Description for Product 80.', N'Product 80', N'SKU10079', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'505606', N'48', N'0', N'2025-02-08 17:51:59.635201', N'81', NULL, N'oleh sistem', N'Description for Product 81.', N'Product 81', N'SKU10080', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'379893', N'84', N'0', N'2025-02-08 17:51:59.639192', N'82', NULL, N'oleh sistem', N'Description for Product 82.', N'Product 82', N'SKU10081', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'557301', N'83', N'0', N'2025-02-08 17:51:59.644178', N'83', NULL, N'oleh sistem', N'Description for Product 83.', N'Product 83', N'SKU10082', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'341675', N'135', N'0', N'2025-02-08 17:51:59.648196', N'84', NULL, N'oleh sistem', N'Description for Product 84.', N'Product 84', N'SKU10083', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'335654', N'54', N'0', N'2025-02-08 17:51:59.652193', N'85', NULL, N'oleh sistem', N'Description for Product 85.', N'Product 85', N'SKU10084', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'800893', N'191', N'0', N'2025-02-08 17:51:59.656146', N'86', NULL, N'oleh sistem', N'Description for Product 86.', N'Product 86', N'SKU10085', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'435488', N'66', N'0', N'2025-02-08 17:51:59.660506', N'87', NULL, N'oleh sistem', N'Description for Product 87.', N'Product 87', N'SKU10086', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'44198', N'56', N'0', N'2025-02-08 17:51:59.665491', N'88', NULL, N'oleh sistem', N'Description for Product 88.', N'Product 88', N'SKU10087', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'68348', N'37', N'0', N'2025-02-08 17:51:59.669481', N'89', NULL, N'oleh sistem', N'Description for Product 89.', N'Product 89', N'SKU10088', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'186854', N'164', N'0', N'2025-02-08 17:51:59.678458', N'90', NULL, N'oleh sistem', N'Description for Product 90.', N'Product 90', N'SKU10089', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'435327', N'47', N'0', N'2025-02-08 17:51:59.682447', N'91', NULL, N'oleh sistem', N'Description for Product 91.', N'Product 91', N'SKU10090', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'879756', N'22', N'0', N'2025-02-08 17:51:59.686435', N'92', NULL, N'oleh sistem', N'Description for Product 92.', N'Product 92', N'SKU10091', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'251668', N'129', N'0', N'2025-02-08 17:51:59.691243', N'93', NULL, N'oleh sistem', N'Description for Product 93.', N'Product 93', N'SKU10092', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'109772', N'193', N'0', N'2025-02-08 17:51:59.696229', N'94', NULL, N'oleh sistem', N'Description for Product 94.', N'Product 94', N'SKU10093', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'274754', N'88', N'0', N'2025-02-08 17:51:59.700219', N'95', NULL, N'oleh sistem', N'Description for Product 95.', N'Product 95', N'SKU10094', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'298080', N'83', N'0', N'2025-02-08 17:51:59.704833', N'96', NULL, N'oleh sistem', N'Description for Product 96.', N'Product 96', N'SKU10095', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'37096', N'65', N'0', N'2025-02-08 17:51:59.709818', N'97', NULL, N'oleh sistem', N'Description for Product 97.', N'Product 97', N'SKU10096', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'592614', N'120', N'0', N'2025-02-08 17:51:59.713812', N'98', NULL, N'oleh sistem', N'Description for Product 98.', N'Product 98', N'SKU10097', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'869380', N'190', N'0', N'2025-02-08 17:51:59.717802', N'99', NULL, N'oleh sistem', N'Description for Product 99.', N'Product 99', N'SKU10098', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'416269', N'117', N'0', N'2025-02-08 17:51:59.721791', N'100', NULL, N'oleh sistem', N'Description for Product 100.', N'Product 100', N'SKU10099', NULL)
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'493595', N'1', N'7', N'2025-02-10 14:32:30.568724', N'101', N'2025-02-11 23:49:23.495652', N'system', N'product percobaan postman 22', N'postman product 22', N'SKU001-test', N'admin001')
GO

INSERT INTO [Product].[Products] ([IsActive], [Price], [Stock], [version], [CreatedDate], [ID], [UpdatedDate], [CreatedBy], [Description], [Name], [SKU], [UpdatedBy]) VALUES (N'1', N'227448', N'1', N'0', N'2025-02-10 20:12:32.887038', N'102', NULL, N'admin001', N'product percobaan postman', N'postman product', N'sku-001-postman', NULL)
GO

SET IDENTITY_INSERT [Product].[Products] OFF
GO


-- ----------------------------
-- Auto increment value for Categories
-- ----------------------------
DBCC CHECKIDENT ('[Product].[Categories]', RESEED, 11)
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
ALTER TABLE [Product].[Categories] ADD CONSTRAINT [PK__Categori__3214EC2766E0CB33] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table ProductCategory
-- ----------------------------
ALTER TABLE [Product].[ProductCategory] ADD CONSTRAINT [Uniq_Product_Category] PRIMARY KEY CLUSTERED ([ProductId], [CategoryId])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for Products
-- ----------------------------
DBCC CHECKIDENT ('[Product].[Products]', RESEED, 202)
GO


-- ----------------------------
-- Uniques structure for table Products
-- ----------------------------
ALTER TABLE [Product].[Products] ADD CONSTRAINT [UK4tg4ybrws4puc5le6g7kx6l5e] UNIQUE NONCLUSTERED ([SKU] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table Products
-- ----------------------------
ALTER TABLE [Product].[Products] ADD CONSTRAINT [PK__Products__3214EC27BC26EFEA] PRIMARY KEY CLUSTERED ([ID])
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

