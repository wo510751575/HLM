# drop table if exists hx.pt_treatment_record;

/*==============================================================*/
/* Table: pt_treatment_record                                   */
/*==============================================================*/
create table hx.pt_treatment_record
(
   CODE                 varchar(40) not null comment 'CODE',
   MEMBER_NO            varchar(40) comment '客户编号',
   PATIENT_NO           varchar(40) comment '患者编号',
   PATIENT_NAME         varchar(100) comment '患者名称',
   MEDICAL_NO           varchar(40) comment '患者病历号',
   SHOP_NO              varchar(40) comment '门诊编号',
   SHOP_NAME            varchar(100) comment '门诊名称',
   MERCHANT_NO          varchar(40) comment '商户编号',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   CLINIC_TIME          datetime comment '就诊时间',
   PROJECT_CODE         varchar(400) comment '项目code，多个则英文逗号分隔',
   PROJECT_NAME         varchar(1200) comment '项目名称，多个则英文逗号分隔',
   IMG                  varchar(1280) comment '就诊照片，多个则英文逗号分隔',
   UPDATE_ID            varchar(40) comment '更新人',
   UPDATE_DATE          datetime comment '更新时间',
   CREATE_ID            varchar(40) comment '创建人',
   CREATE_DATE          datetime comment '创建时间',
   REMARK               varchar(200) comment '备注',
   REMARK1              varchar(200) comment '备注1',
   REMARK2              varchar(200) comment '备注2',
   REMARK3              varchar(200) comment '备注3',
   REMARK4              varchar(200) comment '备注4',
   primary key (CODE)
);

alter table hx.pt_treatment_record comment '患者就诊记录';


# APP端预约需要用户确认
ALTER TABLE  hx.`patient_service` ADD COLUMN `STATUS`  varchar(12) NULL COMMENT '客户确认状态：UNCONFIRM 未确认，CONFIRM 确认 CANCEL取消' AFTER `UPDATE_DATE`;

# 患者关联直通车客户编号
ALTER TABLE hx.`hx_patient` ADD COLUMN `MEMBER_NO`  varchar(40) NULL COMMENT '直通车客户编号' AFTER `UPDATE_DATE`;
# 患者来自哪个线索
ALTER TABLE hx.`hx_patient` ADD COLUMN `CLUE_CODE`  varchar(40) NULL COMMENT '线索号(fk:CLUE_CODE)' AFTER `MEMBER_NO`;
# 线索创建了哪个患者 
ALTER TABLE hx.`hx_clue` ADD COLUMN `PATIENT_NO`  varchar(40) NULL COMMENT '患者编号' AFTER `REMARK`;

# 修改旧数据患者来源为相关线索
UPDATE  hx.hx_patient p, hx.hx_clue t , hx.hx_clue_order o SET p.CLUE_CODE=o.CLUE_CODE  WHERE  t.`CODE`=o.CLUE_CODE  AND t.`NAME` =p.`NAME` and t.PHONE=p.PHONE  AND o.MERCHANT_NO=p.MERCHANT_NO ;

# 修改旧预约数据 客户确认状态为已确认
UPDATE  hx.patient_service SET `STATUS`='CONFIRM' where `STATUS` is null;

# SELECT t.`CODE`,p.`NAME`,p.PHONE,p.`CODE` from hx_clue t  
# join hx_clue_order o on t.`CODE`=o.CLUE_CODE 
# JOIN hx_patient p on t.`NAME` =p.`NAME` and t.PHONE=p.PHONE  AND o.MERCHANT_NO=p.MERCHANT_NO ;

# 好乐美系统变量 2019.07.17
INSERT INTO confcenter.`cc_system_params` (`SYS_PARAM_NAME`, `GROUP_NAME`, `SYS_PARAM_VALUE`, `REMARK`, `SYSTEM_ALIAS_NAME`, `ONLY_ADMIN_MODIFY`) 
VALUES ('psConfirmUrl', 'ps_url', 'http://121.201.65.181/hlm-reservation/reservationConfirm.html', '好乐美预约管理_客户确认URL', 'hx', '1');
# 好乐美系统变量 2019.07.17
INSERT INTO confcenter.`cc_system_params` (`SYS_PARAM_NAME`, `GROUP_NAME`, `SYS_PARAM_VALUE`, `REMARK`, `SYSTEM_ALIAS_NAME`, `ONLY_ADMIN_MODIFY`) 
VALUES ('psSelfUrl', 'ps_url', 'http://121.201.65.181/hlm-reservation/reservationIndex.html', '好乐美预约管理_自助预约URL', 'hx', '1');


# APP菜单 2019.07.17
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('08130991fa7a480387bb0d8da10f7dd8', '18fa21dd311f4f42a723566358f61693', '0,1,18fa21dd311f4f42a723566358f61693,', '抢客户', 30, '', '', '', '0', 'app:hlm:qkh', '1', '2019-7-16 17:11:41', '1', '2019-7-16 17:11:41', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('0861446b35e3408cb66dc4fc11980735', '1124c796aad14989acc05c4e550bc5de', '0,1,18fa21dd311f4f42a723566358f61693,8cb4eb5af30a40cdbc020873fb44f89d,1124c796aad14989acc05c4e550bc5de,', '热文分享', 30, '', '', '', '0', 'app:fx:rw:rwfx', '1', '2019-7-16 17:38:55', '1', '2019-7-16 17:38:55', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('1da62c8a612b421ca049cde5df4ca989', 'e02360cb3cb54835a2d3e45826c747d6', '0,1,18fa21dd311f4f42a723566358f61693,e02360cb3cb54835a2d3e45826c747d6,', '会话-预约管理', 30, '', '', '', '0', 'app:hh:hlm:yygl', '1', '2019-7-16 17:18:05', '1', '2019-7-16 17:19:53', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('20a238eb35bb49cea83048ef5d566731', '1da62c8a612b421ca049cde5df4ca989', '0,1,18fa21dd311f4f42a723566358f61693,e02360cb3cb54835a2d3e45826c747d6,1da62c8a612b421ca049cde5df4ca989,', '新增预约', 30, '', '', '', '0', 'app:hh:hlm:yygl:xzyy', '1', '2019-7-16 17:21:51', '1', '2019-7-16 17:21:51', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('3d45eea791c746018a3195f76ad1afd9', 'cfd8c532429141a584293447375dffc2', '0,1,18fa21dd311f4f42a723566358f61693,8cb4eb5af30a40cdbc020873fb44f89d,cfd8c532429141a584293447375dffc2,', '生成海报', 30, '', '', '', '0', 'app:fx:hlm:hb:schb', '1', '2019-7-16 17:39:44', '1', '2019-7-16 17:39:44', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('81328c4667d2468c8a9c79215da06329', '8e367933711c430a84f2bc3769759956', '0,1,18fa21dd311f4f42a723566358f61693,8cb4eb5af30a40cdbc020873fb44f89d,8e367933711c430a84f2bc3769759956,', '查看', 30, '', '', '', '0', 'app:fx:hlm:fw:ck', '1', '2019-7-16 17:34:14', '1', '2019-7-16 17:34:14', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('8e367933711c430a84f2bc3769759956', '8cb4eb5af30a40cdbc020873fb44f89d', '0,1,18fa21dd311f4f42a723566358f61693,8cb4eb5af30a40cdbc020873fb44f89d,', '服务介绍', 30, '', '', '', '0', 'app:fx:hlm:fw', '1', '2019-7-16 17:30:43', '1', '2019-7-16 17:30:43', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('abc409e2ab6d4788a459517a3df7e4c1', '2fc59df7dc2c4a68a90dde8b77489d76', '0,1,18fa21dd311f4f42a723566358f61693,2fc59df7dc2c4a68a90dde8b77489d76,', '通讯录-im', 30, '', '', '', '0', 'app:txl:im', '1', '2019-7-16 17:28:58', '1', '2019-7-16 17:28:58', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('bea863b876c24bef9f065fcebf445064', '08130991fa7a480387bb0d8da10f7dd8', '0,1,18fa21dd311f4f42a723566358f61693,08130991fa7a480387bb0d8da10f7dd8,', '接诊', 30, '', '', '', '0', 'app:hlm:qkh:jz', '1', '2019-7-16 17:15:29', '1', '2019-7-16 17:15:29', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('bf05d9517b0e4a79ad46c0a38aa370fe', '881478351d91428187a606bfadf5a55c', '0,1,18fa21dd311f4f42a723566358f61693,e02360cb3cb54835a2d3e45826c747d6,881478351d91428187a606bfadf5a55c,', '生成海报', 30, '', '', '', '0', 'app:hh:hlm:hb:schb', '1', '2019-7-16 17:25:00', '1', '2019-7-16 17:52:57', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('c9667a2e32fa426db14c2c698cea4b63', 'e850ad90fbf74eb9a2ef0b5989c7ef2d', '0,1,18fa21dd311f4f42a723566358f61693,8cb4eb5af30a40cdbc020873fb44f89d,e850ad90fbf74eb9a2ef0b5989c7ef2d,', '预约查询', 30, '', '', '', '0', 'app:fx:hlm:yygl:yycx', '1', '2019-7-16 17:35:47', '1', '2019-7-16 17:35:47', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('f8bc7f9928534d13ad402c2415fe3ad1', 'e9ea86861f544227a8b8eef787901b73', '0,1,18fa21dd311f4f42a723566358f61693,e9ea86861f544227a8b8eef787901b73,', '我的订单', 30, '', '', '', '0', 'app:wo:hlm:wddd', '1', '2019-7-16 17:41:16', '1', '2019-7-16 17:41:16', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('222a904ea4454beeb91a8ea6d02a1aba', '2fc59df7dc2c4a68a90dde8b77489d76', '0,1,18fa21dd311f4f42a723566358f61693,2fc59df7dc2c4a68a90dde8b77489d76,', '通讯录-就诊记录', 60, '', '', '', '0', 'app:txl:hlm:jzjl', '1', '2019-7-16 17:29:20', '1', '2019-7-16 17:29:35', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('42c1703a34174942b6ddbb1c006793f3', '08130991fa7a480387bb0d8da10f7dd8', '0,1,18fa21dd311f4f42a723566358f61693,08130991fa7a480387bb0d8da10f7dd8,', '确认到店', 60, '', '', '', '0', 'app:hlm:qkh:qrdd', '1', '2019-7-16 17:15:56', '1', '2019-7-16 17:16:05', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('881478351d91428187a606bfadf5a55c', 'e02360cb3cb54835a2d3e45826c747d6', '0,1,18fa21dd311f4f42a723566358f61693,e02360cb3cb54835a2d3e45826c747d6,', '会话-节日问候', 60, '', '', '', '0', 'app:hh:hlm:hb', '1', '2019-7-16 17:19:19', '1', '2019-7-16 17:19:19', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('d57172f853254be1adb152c4342dfebf', '1da62c8a612b421ca049cde5df4ca989', '0,1,18fa21dd311f4f42a723566358f61693,e02360cb3cb54835a2d3e45826c747d6,1da62c8a612b421ca049cde5df4ca989,', '发送确认单', 60, '', '', '', '0', 'app:hh:hlm:yygl:fsqrd', '1', '2019-7-16 17:22:17', '1', '2019-7-16 17:22:27', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('e02360cb3cb54835a2d3e45826c747d6', '18fa21dd311f4f42a723566358f61693', '0,1,18fa21dd311f4f42a723566358f61693,', '消息', 60, '', '', '', '0', 'app:hh', '1', '2019-7-16 17:12:18', '1', '2019-7-16 17:12:53', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('e850ad90fbf74eb9a2ef0b5989c7ef2d', '8cb4eb5af30a40cdbc020873fb44f89d', '0,1,18fa21dd311f4f42a723566358f61693,8cb4eb5af30a40cdbc020873fb44f89d,', '预约管理', 60, '', '', '', '0', 'app:fx:hlm:yygl', '1', '2019-7-16 17:31:20', '1', '2019-7-16 17:31:20', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('1124c796aad14989acc05c4e550bc5de', '8cb4eb5af30a40cdbc020873fb44f89d', '0,1,18fa21dd311f4f42a723566358f61693,8cb4eb5af30a40cdbc020873fb44f89d,', '热文分享', 90, '', '', '', '0', 'app:fx:rw', '1', '2019-7-16 17:32:20', '1', '2019-7-16 17:32:20', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('1915308ccc19434da7f94b90b7abe40b', '1da62c8a612b421ca049cde5df4ca989', '0,1,18fa21dd311f4f42a723566358f61693,e02360cb3cb54835a2d3e45826c747d6,1da62c8a612b421ca049cde5df4ca989,', '取消预约', 90, '', '', '', '0', 'app:hh:hlm:yygl:qxyy', '1', '2019-7-16 17:23:06', '1', '2019-7-16 17:23:06', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('2fc59df7dc2c4a68a90dde8b77489d76', '18fa21dd311f4f42a723566358f61693', '0,1,18fa21dd311f4f42a723566358f61693,', '通讯录', 90, '', '', '', '0', 'app:txl', '1', '2019-7-16 17:13:20', '1', '2019-7-16 17:14:21', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('e82ea0932eb94ce7af480d4098b4e791', 'e02360cb3cb54835a2d3e45826c747d6', '0,1,18fa21dd311f4f42a723566358f61693,e02360cb3cb54835a2d3e45826c747d6,', '会话-其它', 90, '', '', '', '0', 'app:hh:qt', '1', '2019-7-16 17:19:44', '1', '2019-7-16 17:20:02', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('65e7faa08b534c66ae918e52b890acc7', '1da62c8a612b421ca049cde5df4ca989', '0,1,18fa21dd311f4f42a723566358f61693,e02360cb3cb54835a2d3e45826c747d6,1da62c8a612b421ca049cde5df4ca989,', '重新预约', 120, '', '', '', '0', 'app:hh:hlm:yygl:cxyy', '1', '2019-7-16 17:23:41', '1', '2019-7-16 17:23:41', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('8cb4eb5af30a40cdbc020873fb44f89d', '18fa21dd311f4f42a723566358f61693', '0,1,18fa21dd311f4f42a723566358f61693,', '发现', 120, '', '', '', '0', 'app:fx', '1', '2019-7-16 17:13:44', '1', '2019-7-16 17:14:30', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('cfd8c532429141a584293447375dffc2', '8cb4eb5af30a40cdbc020873fb44f89d', '0,1,18fa21dd311f4f42a723566358f61693,8cb4eb5af30a40cdbc020873fb44f89d,', '节日问候', 120, '', '', '', '0', 'app:fx:hlm:hb', '1', '2019-7-16 17:32:56', '1', '2019-7-16 17:32:56', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('762259d640904e57b2253088db140629', '8cb4eb5af30a40cdbc020873fb44f89d', '0,1,18fa21dd311f4f42a723566358f61693,8cb4eb5af30a40cdbc020873fb44f89d,', '其它', 150, '', '', '', '0', 'app:fx:qt', '1', '2019-7-16 17:33:21', '1', '2019-7-16 17:33:21', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('e9ea86861f544227a8b8eef787901b73', '18fa21dd311f4f42a723566358f61693', '0,1,18fa21dd311f4f42a723566358f61693,', '我', 150, '', '', '', '0', 'app:wo', '1', '2019-7-16 17:14:15', '1', '2019-7-16 17:14:36', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('2998298603dd4c7c82123aec7ccf66c0', '1da62c8a612b421ca049cde5df4ca989', '0,1,18fa21dd311f4f42a723566358f61693,e02360cb3cb54835a2d3e45826c747d6,1da62c8a612b421ca049cde5df4ca989,', '自助预约', 180, '', '', '', '0', 'app:hh:hlm:yygl:zzyy', '1', '2019-7-16 17:24:19', '1', '2019-7-16 17:24:19', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('3b1022edc2ae425094a08df16e858ab5', '08130991fa7a480387bb0d8da10f7dd8', '0,1,18fa21dd311f4f42a723566358f61693,08130991fa7a480387bb0d8da10f7dd8,', '加微信', 180, '', '', '', '0', 'app:hlm:qkh:jwx', '1', '2019-7-16 17:16:37', '1', '2019-7-16 17:16:56', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('28e990facf7148f9ac7bf78afb302652', '08130991fa7a480387bb0d8da10f7dd8', '0,1,18fa21dd311f4f42a723566358f61693,08130991fa7a480387bb0d8da10f7dd8,', '拨打电话', 210, '', '', '', '0', 'app:hlm:qkh:bddh', '1', '2019-7-16 17:17:24', '1', '2019-7-16 17:17:24', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('18fa21dd311f4f42a723566358f61693', '1', '0,1,', 'APP', 8240, '', '', '', '0', '', '1', '2019-7-16 16:32:21', '1', '2019-7-16 16:34:56', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('0c6bdbbcc6de49928833957598856206', 'cfd8c532429141a584293447375dffc2', '0,1,18fa21dd311f4f42a723566358f61693,8cb4eb5af30a40cdbc020873fb44f89d,cfd8c532429141a584293447375dffc2,', '查看海报', 8270, '', '', '', '0', 'app:fx:hlm:hb:ckhb', '1', '2019-7-16 17:40:07', '1', '2019-7-16 17:40:07', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('1790598ed49448e48cbc4fbf37e6a376', 'e9ea86861f544227a8b8eef787901b73', '0,1,18fa21dd311f4f42a723566358f61693,e9ea86861f544227a8b8eef787901b73,', '我的-其它', 8270, '', '', '', '0', 'app:wo:qt', '1', '2019-7-16 17:41:48', '1', '2019-7-16 17:41:48', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('6f8e73e5d02046328925fb2d74917a5b', 'e850ad90fbf74eb9a2ef0b5989c7ef2d', '0,1,18fa21dd311f4f42a723566358f61693,8cb4eb5af30a40cdbc020873fb44f89d,e850ad90fbf74eb9a2ef0b5989c7ef2d,', '发送确认单', 8270, '', '', '', '0', 'app:fx:hlm:yygl:fsqrd', '1', '2019-7-16 17:36:35', '1', '2019-7-16 17:37:36', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('72942b7a50944c47b38eea1b07741273', '8e367933711c430a84f2bc3769759956', '0,1,18fa21dd311f4f42a723566358f61693,8cb4eb5af30a40cdbc020873fb44f89d,8e367933711c430a84f2bc3769759956,', '购买', 8270, '', '', '', '0', 'app:fx:hlm:fw:gm', '1', '2019-7-16 17:34:50', '1', '2019-7-16 17:34:50', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('d8f1df76f46c42a58c184e3acd57abae', 'e850ad90fbf74eb9a2ef0b5989c7ef2d', '0,1,18fa21dd311f4f42a723566358f61693,8cb4eb5af30a40cdbc020873fb44f89d,e850ad90fbf74eb9a2ef0b5989c7ef2d,', '新增预约', 8270, '', '', '', '0', 'app:fx:hlm:yygl:xzyy', '1', '2019-7-16 17:36:13', '1', '2019-7-16 17:36:13', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('e4c1e6878d434eb3a0bef6d4e3c0146c', 'cfd8c532429141a584293447375dffc2', '0,1,18fa21dd311f4f42a723566358f61693,8cb4eb5af30a40cdbc020873fb44f89d,cfd8c532429141a584293447375dffc2,', '分享海报', 8270, '', '', '', '0', 'app:fx:hlm:hb:fxhb', '1', '2019-7-16 17:40:29', '1', '2019-7-16 17:40:29', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('fc7fac3d9eac455ab2e14dd3e80cfd90', 'e9ea86861f544227a8b8eef787901b73', '0,1,18fa21dd311f4f42a723566358f61693,e9ea86861f544227a8b8eef787901b73,', '我的服务', 8270, '', '', '', '0', 'app:wo:hlm:wdfw', '1', '2019-7-16 17:41:32', '1', '2019-7-16 17:41:32', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('ebaaddf0cdcc4187b54bfad134bf53a6', 'e850ad90fbf74eb9a2ef0b5989c7ef2d', '0,1,18fa21dd311f4f42a723566358f61693,8cb4eb5af30a40cdbc020873fb44f89d,e850ad90fbf74eb9a2ef0b5989c7ef2d,', '取消预约', 8300, '', '', '', '0', 'app:fx:hlm:yygl:qxyy', '1', '2019-7-16 17:37:09', '1', '2019-7-16 17:37:09', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('0f6728dde4f24abb83b95ba3d064a435', 'e850ad90fbf74eb9a2ef0b5989c7ef2d', '0,1,18fa21dd311f4f42a723566358f61693,8cb4eb5af30a40cdbc020873fb44f89d,e850ad90fbf74eb9a2ef0b5989c7ef2d,', '重新预约', 8330, '', '', '', '0', 'app:fx:hlm:yygl:cxyy', '1', '2019-7-16 17:38:14', '1', '2019-7-16 17:38:14', '', '0');


# 节日海报 2019.07.22
# drop table if exists hx.festival_poster;
# drop table if exists hx.shop_festival_poster;
/*==============================================================*/
/* Table: festival_poster                                       */
/*==============================================================*/
create table hx.festival_poster
(
   CODE                 varchar(40) not null comment 'CODE',
   TYPE_NAME            varchar(100) comment '节日类型',
   IMGS                 varchar(1280) comment '图片（多张英文逗号分割）',
   UPDATE_ID            varchar(40) comment '更新人',
   UPDATE_DATE          datetime comment '更新时间',
   CREATE_ID            varchar(40) comment '创建人',
   CREATE_DATE          datetime comment '创建时间',
   REMARK               varchar(200) comment '备注',
   REMARK1              varchar(200) comment '备注1',
   REMARK2              varchar(200) comment '备注2',
   REMARK3              varchar(200) comment '备注3',
   REMARK4              varchar(200) comment '备注4',
   primary key (CODE)
);

alter table hx.festival_poster comment '节日问候海报';


/*==============================================================*/
/* Table: shop_festival_poster                                  */
/*==============================================================*/
create table hx.shop_festival_poster
(
   CODE                 varchar(40) not null comment 'CODE',
   FP_CODE              varchar(40) comment '节日问候海报code',
   SHOP_NO              varchar(40) comment '门诊编号',
   SHOP_NAME            varchar(100) comment '门诊名称',
   MERCHANT_NO          varchar(40) comment '商户编号',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   SHOP_WX              varchar(50) comment '终端微信号',
   TYPE_NAME            varchar(100) comment '节日类型',
   TEMPLATE_IMG         varchar(128) comment '原图',
   QCORD_IMG            varchar(128) comment '二维码图片',
   UPDATE_ID            varchar(40) comment '更新人',
   UPDATE_DATE          datetime comment '更新时间',
   CREATE_ID            varchar(40) comment '创建人',
   CREATE_DATE          datetime comment '创建时间',
   REMARK               varchar(200) comment '备注',
   REMARK1              varchar(200) comment '备注1',
   REMARK2              varchar(200) comment '备注2',
   REMARK3              varchar(200) comment '备注3',
   REMARK4              varchar(200) comment '备注4',
   primary key (CODE)
);

alter table hx.shop_festival_poster comment '门诊节日问候海报';

# 节日问候菜单 2019.07.22
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('b1a323ea9f224c068b4a282bf07f317f', 'dc3b682c369c4c879fc3112aaafd526f', '0,1,b2d63cdffa4e4d74b6e1e5def6137b59,dc3b682c369c4c879fc3112aaafd526f,', '节日问候', 30, '/hx/fp/list', '', '', '1', '', '1', '2019-7-22 10:54:57', '1', '2019-7-22 10:54:57', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('dc3b682c369c4c879fc3112aaafd526f', 'b2d63cdffa4e4d74b6e1e5def6137b59', '0,1,b2d63cdffa4e4d74b6e1e5def6137b59,', '节日问候', 1350, '', '', '', '1', '', '1', '2019-7-22 10:54:35', '1', '2019-7-22 10:54:35', '', '0');




