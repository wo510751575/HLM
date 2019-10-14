/*
Navicat MySQL Data Transfer

Source Server         : .4
Source Server Version : 50642
Source Host           : 192.168.1.4:3306
Source Database       : hx

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2019-10-09 16:45:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for patient_medical_template_list
-- ----------------------------
DROP TABLE IF EXISTS `patient_medical_template_list`;
CREATE TABLE `patient_medical_template_list` (
  `code` varchar(40) NOT NULL,
  `name` char(50) DEFAULT NULL COMMENT '名称',
  `parent_code` varchar(255) DEFAULT NULL COMMENT '父',
  `parent_name` char(50) DEFAULT NULL COMMENT '父名称',
  `order_no` int(11) DEFAULT NULL COMMENT '排序（数值越大越后）',
  `creater` varchar(40) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`code`),
  UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='病历-模版目录';
