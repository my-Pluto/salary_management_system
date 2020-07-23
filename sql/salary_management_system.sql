/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 8.0.17 : Database - sms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sms` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `sms`;

/*Table structure for table `count_item` */

DROP TABLE IF EXISTS `count_item`;

CREATE TABLE `count_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '计算项目ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '计算项目名称',
  `effect` tinyint(4) unsigned NOT NULL COMMENT '增减项',
  `number` tinyint(4) unsigned NOT NULL COMMENT '工资条中的序号',
  `is_display` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '在工资条中是否显示',
  `first_operand` varchar(50) NOT NULL COMMENT '第一个运算数',
  `operator` varchar(1) NOT NULL COMMENT '运算符',
  `secent_operand` decimal(10,4) NOT NULL COMMENT '第二个运算数',
  `version` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '版本信息',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `count_item` */

insert  into `count_item`(`id`,`name`,`effect`,`number`,`is_display`,`first_operand`,`operator`,`secent_operand`,`version`,`gmt_create`,`gmt_modified`) values 
(1,'养老保险',0,1,1,'基本工资','*',0.0900,1,'2020-07-21 15:33:38','2020-07-22 15:01:36'),
(2,'养老保险（公司）',1,0,0,'基本工资','*',0.0800,1,'2020-07-22 15:37:52','2020-07-22 15:38:08');

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `province` varchar(20) NOT NULL COMMENT '部门所在的省份',
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `version` int(20) unsigned NOT NULL DEFAULT '1' COMMENT '版本信息',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `department` */

insert  into `department`(`id`,`province`,`name`,`version`,`gmt_create`,`gmt_modified`) values 
(1,'北京','财务部',1,'2020-07-17 11:14:16','2020-07-17 11:14:19'),
(2,'南京','董事会',1,'2020-07-17 15:41:37','2020-07-17 15:49:52'),
(3,'南京','采购部',1,'2020-07-17 15:41:51','2020-07-19 22:22:23'),
(5,'天津','商务部',1,'2020-07-23 09:24:51','2020-07-23 09:24:59');

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '员工工号',
  `name` varchar(50) NOT NULL COMMENT '员工姓名',
  `sex` varchar(4) NOT NULL COMMENT '员工性别',
  `department` bigint(20) NOT NULL COMMENT '员工部门',
  `position` bigint(20) NOT NULL COMMENT '员工职位',
  `email` varchar(255) NOT NULL COMMENT '员工联系方式',
  `entry_time` datetime NOT NULL COMMENT '入职时间',
  `version` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '版本信息',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000007 DEFAULT CHARSET=utf8;

/*Data for the table `employee` */

insert  into `employee`(`id`,`name`,`sex`,`department`,`position`,`email`,`entry_time`,`version`,`gmt_create`,`gmt_modified`) values 
(10000001,'张三','男',1,1,'117317175@qq.com','2020-07-20 08:00:00',1,'2020-07-17 11:13:22','2020-07-23 00:16:16'),
(10000002,'李四','男',1,1,'2698391541@qq.com','2020-07-17 13:29:17',1,'2020-07-17 13:29:19','2020-07-19 23:07:13'),
(10000003,'王五','男',1,1,'111','2020-07-18 22:44:53',1,'2020-07-18 22:44:55','2020-07-18 22:44:58'),
(10000006,'侯七','男',5,5,'2433574457@qq.com','2020-07-23 08:00:00',1,'2020-07-23 09:26:18','2020-07-23 09:26:18');

/*Table structure for table `fixed_item` */

DROP TABLE IF EXISTS `fixed_item`;

CREATE TABLE `fixed_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '固定项目ID',
  `name` varchar(50) NOT NULL COMMENT '固定项目名称',
  `effect` tinyint(4) unsigned NOT NULL COMMENT '增减项，表明该项目是增加、减少、还是无影响',
  `number` tinyint(3) unsigned NOT NULL COMMENT '表明该项目在生成工资条中的序号',
  `is_display` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否显示',
  `version` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '版本信息',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `fixed_item` */

insert  into `fixed_item`(`id`,`name`,`effect`,`number`,`is_display`,`version`,`gmt_create`,`gmt_modified`) values 
(1,'基本工资',2,1,1,1,'2020-07-20 10:50:53','2020-07-21 22:20:49'),
(5,'采暖补贴',2,3,1,1,'2020-07-21 22:56:58','2020-07-21 22:56:58'),
(8,'高温补贴',0,2,1,1,'2020-07-22 11:52:04','2020-07-22 11:52:04');

/*Table structure for table `fixed_item_data` */

DROP TABLE IF EXISTS `fixed_item_data`;

CREATE TABLE `fixed_item_data` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `name` varchar(255) NOT NULL COMMENT '项目名称',
  `item_id` bigint(20) unsigned NOT NULL COMMENT '项目id',
  `dept_name` varchar(255) NOT NULL COMMENT '部门名称',
  `dept_id` bigint(20) unsigned NOT NULL COMMENT '部门id',
  `employee_name` varchar(255) NOT NULL COMMENT '员工名称',
  `employee_id` bigint(20) unsigned NOT NULL COMMENT '员工id',
  `salary` decimal(40,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '金额',
  `version` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '版本信息',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

/*Data for the table `fixed_item_data` */

insert  into `fixed_item_data`(`id`,`name`,`item_id`,`dept_name`,`dept_id`,`employee_name`,`employee_id`,`salary`,`version`,`gmt_create`,`gmt_modified`,`is_deleted`) values 
(38,'基本工资',1,'财务部',1,'张三',10000001,5000.00,1,'2020-07-22 11:51:06','2020-07-22 11:51:06',0),
(39,'基本工资',1,'财务部',1,'李四',10000002,5000.00,1,'2020-07-22 11:51:06','2020-07-22 11:51:06',0),
(40,'基本工资',1,'财务部',1,'王五',10000003,5000.00,1,'2020-07-22 11:51:06','2020-07-22 11:51:06',0),
(41,'采暖补贴',5,'财务部',1,'张三',10000001,500.00,1,'2020-07-22 11:51:20','2020-07-22 11:51:20',0),
(42,'采暖补贴',5,'财务部',1,'李四',10000002,500.00,1,'2020-07-22 11:51:20','2020-07-22 11:51:20',0),
(43,'采暖补贴',5,'财务部',1,'王五',10000003,500.00,1,'2020-07-22 11:51:20','2020-07-22 11:51:20',0),
(44,'高温补贴',8,'财务部',1,'张三',10000001,100.00,1,'2020-07-22 11:52:12','2020-07-22 11:52:12',0),
(45,'高温补贴',8,'财务部',1,'李四',10000002,100.00,1,'2020-07-22 11:52:12','2020-07-22 11:52:12',0),
(46,'高温补贴',8,'财务部',1,'王五',10000003,100.00,1,'2020-07-22 11:52:12','2020-07-22 11:52:12',0);

/*Table structure for table `import_item` */

DROP TABLE IF EXISTS `import_item`;

CREATE TABLE `import_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '导入项目ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '导入项目名称',
  `effect` tinyint(4) unsigned NOT NULL COMMENT '增减项',
  `number` tinyint(4) unsigned NOT NULL COMMENT '在工资表中的显示序号',
  `is_display` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '是否在工资条中显示',
  `version` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '版本信息',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `import_item` */

insert  into `import_item`(`id`,`name`,`effect`,`number`,`is_display`,`version`,`gmt_create`,`gmt_modified`) values 
(1,'出勤',1,1,1,1,'2020-07-21 11:25:57','2020-07-21 15:54:43'),
(2,'病假',1,2,1,1,'2020-07-22 16:15:48','2020-07-22 16:15:48');

/*Table structure for table `import_item_data` */

DROP TABLE IF EXISTS `import_item_data`;

CREATE TABLE `import_item_data` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `item_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目名称',
  `item_id` bigint(20) unsigned NOT NULL COMMENT '项目id',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `dept_id` bigint(20) unsigned NOT NULL COMMENT '部门id',
  `employee_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工姓名',
  `employee_id` bigint(20) unsigned NOT NULL COMMENT '员工id',
  `data` decimal(10,2) unsigned NOT NULL COMMENT '数据',
  `month` varchar(7) NOT NULL COMMENT '年月',
  `version` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '版本信息',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `import_item_data` */

insert  into `import_item_data`(`id`,`item_name`,`item_id`,`dept_name`,`dept_id`,`employee_name`,`employee_id`,`data`,`month`,`version`,`gmt_create`,`gmt_modified`) values 
(10,'出勤',1,'财务部',1,'张三',10000001,29.00,'2020-06',1,'2020-07-22 19:26:00','2020-07-22 21:10:16'),
(11,'出勤',1,'财务部',1,'李四',10000002,30.00,'2020-06',1,'2020-07-22 19:26:00','2020-07-22 19:26:00'),
(12,'出勤',1,'财务部',1,'王五',10000003,30.00,'2020-06',1,'2020-07-22 19:26:00','2020-07-22 19:26:00'),
(13,'病假',2,'财务部',1,'张三',10000001,0.00,'2020-06',1,'2020-07-22 19:26:07','2020-07-22 19:26:07'),
(14,'病假',2,'财务部',1,'李四',10000002,0.00,'2020-06',1,'2020-07-22 19:26:07','2020-07-22 19:26:07'),
(15,'病假',2,'财务部',1,'王五',10000003,0.00,'2020-06',1,'2020-07-22 19:26:07','2020-07-22 19:26:07');

/*Table structure for table `operation` */

DROP TABLE IF EXISTS `operation`;

CREATE TABLE `operation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `user` bigint(20) unsigned NOT NULL COMMENT '操作用户工号',
  `operation_time` datetime NOT NULL COMMENT '操作时间',
  `opetation_type` varchar(255) NOT NULL COMMENT '操作类型',
  `version` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '版本信息',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `operation` */

/*Table structure for table `position` */

DROP TABLE IF EXISTS `position`;

CREATE TABLE `position` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门',
  `version` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '版本信息',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `department_id` bigint(20) unsigned NOT NULL COMMENT '部门id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `position` */

insert  into `position`(`id`,`name`,`department`,`version`,`gmt_create`,`gmt_modified`,`department_id`) values 
(1,'副部长','财务部',1,'2020-07-17 11:15:08','2020-07-23 00:04:20',1),
(2,'部长','采购部',1,'2020-07-17 16:23:34','2020-07-17 16:23:36',2),
(3,'部长','统战部',1,'2020-07-17 16:28:41','2020-07-19 22:37:16',3),
(5,'部长','商务部',1,'2020-07-23 09:25:44','2020-07-23 09:25:44',5);

/*Table structure for table `statement` */

DROP TABLE IF EXISTS `statement`;

CREATE TABLE `statement` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '项目ID',
  `name` varchar(255) NOT NULL COMMENT '项目名称',
  `number` bigint(50) unsigned NOT NULL COMMENT '项目在工资条中的序号',
  `employee_name` varchar(255) DEFAULT NULL,
  `employee_id` bigint(20) unsigned NOT NULL COMMENT '员工工号',
  `department_id` bigint(20) unsigned NOT NULL,
  `department_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工所在部门',
  `effect` tinyint(4) unsigned NOT NULL COMMENT '增减项',
  `data` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '项目数据',
  `is_provided` tinyint(4) unsigned NOT NULL COMMENT '是否已经发放',
  `month` varchar(7) NOT NULL COMMENT '年月信息',
  `is_display` tinyint(4) unsigned NOT NULL COMMENT '是否在工资条中显示',
  `version` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '版本信息',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8;

/*Data for the table `statement` */

insert  into `statement`(`id`,`name`,`number`,`employee_name`,`employee_id`,`department_id`,`department_name`,`effect`,`data`,`is_provided`,`month`,`is_display`,`version`,`gmt_create`,`gmt_modified`) values 
(141,'基本工资',1,'张三',10000001,1,'财务部',2,5000.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(142,'采暖补贴',3,'张三',10000001,1,'财务部',2,500.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(143,'高温补贴',2,'张三',10000001,1,'财务部',0,100.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(144,'出勤',1,'张三',10000001,1,'财务部',1,30.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(145,'病假',2,'张三',10000001,1,'财务部',1,0.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(146,'养老保险',1,'张三',10000001,1,'财务部',0,450.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(147,'养老保险（公司）',0,'张三',10000001,1,'财务部',1,400.00,1,'2020-06',0,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(148,'实发工资',100,'张三',10000001,1,'财务部',0,4950.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(149,'基本工资',1,'李四',10000002,1,'财务部',2,5000.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(150,'采暖补贴',3,'李四',10000002,1,'财务部',2,500.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(151,'高温补贴',2,'李四',10000002,1,'财务部',0,100.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(152,'出勤',1,'李四',10000002,1,'财务部',1,30.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(153,'病假',2,'李四',10000002,1,'财务部',1,0.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(154,'养老保险',1,'李四',10000002,1,'财务部',0,450.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(155,'养老保险（公司）',0,'李四',10000002,1,'财务部',1,400.00,1,'2020-06',0,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(156,'实发工资',100,'李四',10000002,1,'财务部',0,9900.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(157,'基本工资',1,'王五',10000003,1,'财务部',2,5000.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(158,'采暖补贴',3,'王五',10000003,1,'财务部',2,500.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(159,'高温补贴',2,'王五',10000003,1,'财务部',0,100.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(160,'出勤',1,'王五',10000003,1,'财务部',1,30.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(161,'病假',2,'王五',10000003,1,'财务部',1,0.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(162,'养老保险',1,'王五',10000003,1,'财务部',0,450.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(163,'养老保险（公司）',0,'王五',10000003,1,'财务部',1,400.00,1,'2020-06',0,1,'2020-07-22 23:21:03','2020-07-22 23:41:55'),
(164,'实发工资',100,'王五',10000003,1,'财务部',0,14850.00,1,'2020-06',1,1,'2020-07-22 23:21:03','2020-07-22 23:41:55');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL COMMENT '员工工号',
  `name` varchar(50) NOT NULL COMMENT '员工姓名',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `version` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '版本信息',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`password`,`version`,`gmt_create`,`gmt_modified`) values 
(10000000,'admin','25f9e794323b453885f5181f1b624d0b',1,'2020-07-17 09:02:20','2020-07-19 13:10:54'),
(10000001,'张三','e10adc3949ba59abbe56e057f20f883e',1,'2020-07-17 10:24:18','2020-07-17 13:28:36'),
(10000002,'李四','e10adc3949ba59abbe56e057f20f883e',1,'2020-07-19 20:48:34','2020-07-19 20:48:34'),
(10000003,'王五','e10adc3949ba59abbe56e057f20f883e',1,'2020-07-19 22:54:07','2020-07-19 22:55:57'),
(10000006,'侯七','e10adc3949ba59abbe56e057f20f883e',1,'2020-07-23 09:27:05','2020-07-23 09:27:11');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
