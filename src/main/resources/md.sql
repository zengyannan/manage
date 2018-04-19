/*
Navicat MySQL Data Transfer

Source Server         : 1
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : md

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-04-19 18:17:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `doctor`
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `phone` char(11) DEFAULT NULL COMMENT '手机号码',
  `address` varchar(64) DEFAULT NULL COMMENT '联系地址',
  `status` tinyint(1) DEFAULT '1' COMMENT '可用状态 1/2 1为可用 2为不可用',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名 唯一',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `remark` varchar(255) DEFAULT NULL COMMENT '介绍',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '修改注释',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_username_key` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of doctor
-- ----------------------------
INSERT INTO `doctor` VALUES ('1', '赖一申', '13726213333', '南村医院', '1', 'lys', '123456', null, null, null);
INSERT INTO `doctor` VALUES ('2', '李医生', '13726215333', '广东省广州市番禺区南村镇樟边村', '1', 'ldmys11', '123456', '', '2018-04-11 14:35:52', '2018-04-11 15:05:29');
INSERT INTO `doctor` VALUES ('4', '李医生', '13726215333', '广东省广州市番禺区南村镇樟边村', '2', 'ldmys1', '123456', null, '2018-04-11 15:18:49', '2018-04-11 15:20:15');

-- ----------------------------
-- Table structure for `doctor_role`
-- ----------------------------
DROP TABLE IF EXISTS `doctor_role`;
CREATE TABLE `doctor_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rid` (`rid`),
  KEY `doctor_id_role_ibfk_1` (`doctor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of doctor_role
-- ----------------------------
INSERT INTO `doctor_role` VALUES ('1', '1', '16');

-- ----------------------------
-- Table structure for `hr`
-- ----------------------------
DROP TABLE IF EXISTS `hr`;
CREATE TABLE `hr` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id hrID',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `phone` char(11) DEFAULT NULL COMMENT '手机号码',
  `address` varchar(64) DEFAULT NULL COMMENT '联系地址',
  `status` tinyint(1) DEFAULT '1' COMMENT '是否可用',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名 唯一',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `remark` varchar(255) DEFAULT NULL COMMENT '介绍',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_username_key` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hr
-- ----------------------------
INSERT INTO `hr` VALUES ('17', '猫逼', '13726215333', '广东省广州市番禺区', '1', 'maobi1', '123456', null, '2018-04-08 00:00:00', '2018-04-10 16:20:40');
INSERT INTO `hr` VALUES ('18', '小赖', '13726215333', '广东省广州市番禺区南村镇樟边村', '2', 'maobi', '123456', '', '2018-04-08 00:00:00', '2018-04-10 15:54:20');
INSERT INTO `hr` VALUES ('20', '小赖xin', '13726215333', '广东省广州市番禺区南村镇樟边村', '1', 'maobi3', '123456', null, '2018-04-10 15:32:39', '2018-04-10 15:32:39');

-- ----------------------------
-- Table structure for `hr_role`
-- ----------------------------
DROP TABLE IF EXISTS `hr_role`;
CREATE TABLE `hr_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hrid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rid` (`rid`),
  KEY `hr_role_ibfk_1` (`hrid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hr_role
-- ----------------------------
INSERT INTO `hr_role` VALUES ('1', '17', '6');
INSERT INTO `hr_role` VALUES ('2', '17', '7');

-- ----------------------------
-- Table structure for `inspection`
-- ----------------------------
DROP TABLE IF EXISTS `inspection`;
CREATE TABLE `inspection` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) DEFAULT NULL COMMENT '英文名称 或 英文简写',
  `zh_name` varchar(64) NOT NULL COMMENT '中文名称',
  `organ_id` int(11) NOT NULL COMMENT '所关联的组织器官',
  `check_address` varchar(64) NOT NULL COMMENT '检验科室',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspection
-- ----------------------------

-- ----------------------------
-- Table structure for `laboratory_sheet`
-- ----------------------------
DROP TABLE IF EXISTS `laboratory_sheet`;
CREATE TABLE `laboratory_sheet` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `patient_id` int(11) NOT NULL COMMENT '病人ID',
  `doctor_id` int(11) NOT NULL COMMENT '医生ID',
  `status` tinyint(1) NOT NULL COMMENT '状态 1医生没给建议的状态 2 医生给了建议的状态',
  `suggest` varchar(512) DEFAULT NULL COMMENT '医生建议',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of laboratory_sheet
-- ----------------------------

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) DEFAULT NULL,
  `path` varchar(64) DEFAULT NULL,
  `component` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '1',
  `order_path` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parentId` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '/', '/', 'Home', '主界面', '0', '1', '0-1');
INSERT INTO `menu` VALUES ('2', '/', '/user', 'User', '人员管理', '1', '1', '1-2');
INSERT INTO `menu` VALUES ('3', '/api/hr/**', '/hr', 'Hr', '管理员管理', '2', '1', '2-3');
INSERT INTO `menu` VALUES ('4', '/api/doctor/**', '/doctor', 'Doctor', '医生管理', '2', '1', '2-4');
INSERT INTO `menu` VALUES ('5', '/api/patient/**', '/patient', 'Patient', '病人管理', '2', '1', '2-5');
INSERT INTO `menu` VALUES ('6', '/api/organ/**', '/organ', 'Organ', '身体器官', '10', '1', '10-6');
INSERT INTO `menu` VALUES ('7', 'api/inspection/**', '/inspection', 'Inspection', '检验科目', '10', '1', '10-7');
INSERT INTO `menu` VALUES ('8', 'api/specific', '/specific', 'Specific', '具体检验指标', '10', '1', '10-8');
INSERT INTO `menu` VALUES ('9', 'api/specific/item', '/specificItem', 'SpecificItem', '病人检验数值', '10', '1', '10-9');
INSERT INTO `menu` VALUES ('10', '/', '/disease', 'Disease', '病理管理', '1', '1', '1-10');
INSERT INTO `menu` VALUES ('11', '/api/laboratorySheet', '/laboratorySheet', 'LaboratorySheet', '检验单', '1', '1', '1-11');
INSERT INTO `menu` VALUES ('12', null, '/main', 'Main', '主页', '1', '1', '1-12');
INSERT INTO `menu` VALUES ('13', null, '/test', 'Test', '测试页面', '1', '1', '1-13');

-- ----------------------------
-- Table structure for `organ`
-- ----------------------------
DROP TABLE IF EXISTS `organ`;
CREATE TABLE `organ` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `zh_name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organ
-- ----------------------------

-- ----------------------------
-- Table structure for `patient`
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '病人ID',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `phone` char(11) DEFAULT NULL COMMENT '手机号码',
  `address` varchar(64) DEFAULT NULL COMMENT '联系地址',
  `status` tinyint(1) DEFAULT '1' COMMENT '可用状态 默认为1可用 2为不可用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `id_card` varchar(32) NOT NULL COMMENT '身份证',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_id_card_key` (`id_card`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patient
-- ----------------------------

-- ----------------------------
-- Table structure for `patient_role`
-- ----------------------------
DROP TABLE IF EXISTS `patient_role`;
CREATE TABLE `patient_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rid` (`rid`),
  KEY `patient_id_role_ibfk_1` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patient_role
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色主键ID',
  `name` varchar(64) DEFAULT NULL COMMENT '角色英文名',
  `nameZh` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 1为可用 2为不可用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('6', 'ROLE_admin', '系统管理员', '1', null, null);
INSERT INTO `role` VALUES ('7', 'ROLE_common_admin', '普通管理员', '1', null, null);
INSERT INTO `role` VALUES ('15', 'ROLE_ad_manager', '低级管理员', '1', '2018-04-10 15:01:34', '2018-04-10 15:01:34');
INSERT INTO `role` VALUES ('16', 'ROLE_common_doctor', '医师', '1', '2018-04-11 14:12:26', '2018-04-11 14:12:32');

-- ----------------------------
-- Table structure for `role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mid` (`mid`),
  KEY `rid` (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1', '6');
INSERT INTO `role_menu` VALUES ('2', '2', '6');
INSERT INTO `role_menu` VALUES ('3', '3', '6');
INSERT INTO `role_menu` VALUES ('4', '4', '6');
INSERT INTO `role_menu` VALUES ('5', '5', '7');
INSERT INTO `role_menu` VALUES ('6', '10', '7');
INSERT INTO `role_menu` VALUES ('7', '1', '7');
INSERT INTO `role_menu` VALUES ('8', '12', '6');
INSERT INTO `role_menu` VALUES ('9', '12', '7');
INSERT INTO `role_menu` VALUES ('10', '13', '6');
INSERT INTO `role_menu` VALUES ('11', '13', '7');

-- ----------------------------
-- Table structure for `specific`
-- ----------------------------
DROP TABLE IF EXISTS `specific`;
CREATE TABLE `specific` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(64) DEFAULT NULL,
  `zh_name` varchar(64) NOT NULL COMMENT '中文名称',
  `inspection_id` int(11) NOT NULL COMMENT '所属化验项目ID',
  `unit` varchar(64) NOT NULL COMMENT '单位',
  `min` double(10,3) DEFAULT NULL COMMENT '最小浮动值',
  `max` double(10,3) DEFAULT NULL COMMENT '最大浮动值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of specific
-- ----------------------------

-- ----------------------------
-- Table structure for `specific_item`
-- ----------------------------
DROP TABLE IF EXISTS `specific_item`;
CREATE TABLE `specific_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `result` double(10,3) NOT NULL COMMENT '检验结果值',
  `specific_id` int(11) NOT NULL COMMENT '所属的检验指标ID',
  `tips` tinyint(1) DEFAULT NULL COMMENT '提示 1为平均值内 2为偏高 或 3为偏低',
  `ls_id` int(11) NOT NULL COMMENT '所属检验单的ID',
  `checker` varchar(64) NOT NULL COMMENT '检验者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of specific_item
-- ----------------------------
