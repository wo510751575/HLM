/*
Navicat MySQL Data Transfer

Source Server         : .4
Source Server Version : 50642
Source Host           : 192.168.1.4:3306
Source Database       : hx

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2019-10-09 16:45:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for patient_medical_template
-- ----------------------------
DROP TABLE IF EXISTS `patient_medical_template`;
CREATE TABLE `patient_medical_template` (
  `CODE` varchar(40) NOT NULL COMMENT 'CODE',
  `NAME` varchar(255) DEFAULT NULL COMMENT '模版名称',
  `MAIN_REMARK` varchar(500) DEFAULT NULL COMMENT '主诉及病史：主诉',
  `MAIN_CURRENT_REMARK` varchar(500) DEFAULT NULL COMMENT '主诉及病史：现病史',
  `MAIN_PAST_REMARK` varchar(500) DEFAULT NULL COMMENT '主诉及病史：既往史',
  `CHECK_ORAL_REMARK` varchar(500) DEFAULT NULL COMMENT '口腔检查：口腔检查',
  `CHECK_AUXILIARY_REMARK` varchar(500) DEFAULT NULL COMMENT '口腔检查：辅助检查',
  `PLAN_DIAGNOSIS_REMARK` varchar(500) DEFAULT NULL COMMENT '诊断与治疗计划：诊断',
  `PLAN_TREATMENT_REMARK` varchar(500) DEFAULT NULL COMMENT '诊断与治疗计划：治疗计划',
  `DM_DISPOSAL_REMARK` varchar(500) DEFAULT NULL COMMENT '处置与医嘱：处置',
  `DM_MEDICAL_REMARK` varchar(500) DEFAULT NULL COMMENT '处置与医嘱：医嘱',
  `OTHER_LABEL_REMARK` varchar(500) DEFAULT NULL COMMENT '其他：标签',
  `OTHER_REMARK` varchar(500) DEFAULT NULL COMMENT '其他：备注',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATE_ID` varchar(40) DEFAULT NULL COMMENT '创建人',
  `CREATE_NAME` varchar(100) DEFAULT NULL COMMENT '创建人',
  `REMARK` varchar(500) DEFAULT NULL COMMENT '备注',
  `REMARK2` varchar(500) DEFAULT NULL COMMENT '备注',
  `REMARK3` varchar(500) DEFAULT NULL COMMENT '备注',
  `REMARK4` varchar(500) DEFAULT NULL COMMENT '备注',
  `UPDATE_ID` varchar(40) DEFAULT NULL COMMENT '更新人',
  `UPDATE_NAME` varchar(100) DEFAULT NULL COMMENT '更新人',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='患者病历-模版';
