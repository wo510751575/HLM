/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/3/21 星期四 19:17:33                       */
/*==============================================================*/


drop table if exists hx.guid_schedule;

drop table if exists hx.guid_schedule_log;

drop table if exists hx.hx_clue;

drop table if exists hx.hx_clue_order;

drop table if exists hx.hx_patient;

drop table if exists hx.patient_medical;

drop table if exists hx.patient_service;

drop table if exists hx.patient_service_advisory;

drop table if exists hx.patient_service_choose;

drop table if exists hx.server_detail;

drop table if exists hx.server_info;

drop table if exists hx.shop_config;

drop table if exists hx.shop_order;

drop table if exists hx.shop_schedule;

drop table if exists hx.shop_server;

drop table if exists hx.shop_server_detail;

/*==============================================================*/
/* Table: guid_schedule                                         */
/*==============================================================*/
create table hx.guid_schedule
(
   CODE                 varchar(40) not null comment 'CODE',
   MEMBER_NO_GUID       varchar(40) comment '员工编号',
   MEMBER_NAME_GUID     varchar(100) comment '员工姓名',
   SHOP_NO              varchar(40) comment '门诊编号',
   SHOP_NAME            varchar(100) comment '门诊名称',
   MERCHANT_NO          varchar(40) comment '商户编号',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   TYPE                 varchar(6) comment '类型(当周WEEK、固定FIXED)',
   DAY_NUM              int comment '星期几(1是星期一，依次类推7是星期日)',
   SCHEDULE_CODE        varchar(40) comment '班次code',
   CREATE_ID            varchar(40) comment '创建人',
   CREATE_DATE          datetime comment '创建时间',
   primary key (CODE)
);

alter table hx.guid_schedule comment '员工班';

/*==============================================================*/
/* Table: guid_schedule_log                                     */
/*==============================================================*/
create table hx.guid_schedule_log
(
   CODE                 varchar(60) not null comment 'CODE',
   MEMBER_NO_GUID       varchar(40) comment '员工编号',
   MEMBER_NAME_GUID     varchar(100) comment '员工姓名',
   SHOP_NO              varchar(40) comment '门诊编号',
   SHOP_NAME            varchar(100) comment '门诊名称',
   MERCHANT_NO          varchar(40) comment '商户编号',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   WORK_DATE            datetime comment '日期',
   DAY_NUM              int comment '星期几(1是星期一，依次类推7是星期日)',
   SCHEDULE_CODE        varchar(40) comment '班次code',
   SCHEDULE_NAME        varchar(64) comment '班次名',
   WORK_TIME            time comment '上班时间',
   OFF_TIME             time comment '下班时间',
   APT_FLAG             varchar(6) comment '是否可约诊appointment（N否 Y是)',
   CREATE_ID            varchar(40) comment '创建人',
   CREATE_DATE          datetime comment '创建时间',
   primary key (CODE)
);

alter table hx.guid_schedule_log comment '员工历史排班';

/*==============================================================*/
/* Table: hx_clue                                               */
/*==============================================================*/
create table hx.hx_clue
(
   CODE                 varchar(40) not null comment '线索号',
   NAME                 varchar(20) comment '客户姓名',
   PHONE                varchar(11) comment '电话号码',
   SEX                  varchar(10) comment '性别',
   AGE                  int comment '年龄',
   PROVINCE             varchar(40) comment '省名称',
   CITY                 varchar(40) comment '市名称',
   AREA                 varchar(100) comment '区名称',
   ADDR_INFO            varchar(255) comment '完整地址（省市区详细地址全部包含）',
   SOURCE               varchar(20) comment '来源',
   WECHAT_NO            varchar(40) comment '微信号',
   WECHAT_NAME          varchar(40) comment '微信昵称',
   USER_TYPE            varchar(40) comment '客户类型',
   USER_PRICE           bigint comment '客户单价',
   STATUS               varchar(10) comment '状态(FREEZE冻结中，CANG可抢，HASG已被抢）)',
   VALID_STATUS         varchar(8) comment '是否有效(VALID:有效，INVALID：无效)',
   CREATE_TIME          datetime comment '添加日期',
   ORDER_NO             varchar(40) comment '工单号',
   PROJECT              varchar(20) comment '预约项目',
   RESERVE_DATE         date comment '预约日期',
   RESERVE_TIME         varchar(20) comment '预约时间',
   FOLLOW_NAME          varchar(10) comment '跟进人员',
   WISH_LEVEL           varchar(1) comment '意愿等级',
   COMPLAINT            varchar(200) comment '主诉',
   REMARK               varchar(200) comment '备注',
   primary key (CODE)
);

alter table hx.hx_clue comment '线索表(hx_clue)';

/*==============================================================*/
/* Table: hx_clue_order                                         */
/*==============================================================*/
create table hx.hx_clue_order
(
   CODE                 varchar(40) not null comment '线索订单号',
   CLUE_CODE            varchar(40) comment '线索号(fk:CLUE_CODE)',
   MEMBER_NO            varchar(40) comment '导购编号',
   MEMBER_NAME          varchar(100) comment '导购名称',
   SHOP_NO              varchar(40) comment '分店编号',
   SHOP_NAME            varchar(100) comment '分店名称',
   MERCHANT_NO          varchar(40) comment '商户编号',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   SHOP_SERVER_CODE     varchar(40) comment '门诊服务code(fk:shop_server.code)',
   SERVER_NAME          varchar(100) comment '服务名称',
   SERVER_CODE          varchar(40) comment '服务code（fk:server_icode）',
   USER_TYPE            varchar(40) comment '客户类型',
   USER_PRICE           bigint comment '客户单价',
   STATUS               varchar(6) comment '状态（FREEZE冻结中，OK已使用，CANCEL已取消）',
   CREATE_ID            varchar(40) comment '创建人',
   CREATE_DATE          datetime comment '创建日期',
   UPDATE_ID            varchar(40) comment '更新人',
   UPDATE_DATE          datetime comment '更新日期',
   REMARK               varchar(200) comment '备注',
   TYPE                 varchar(10),
   primary key (CODE)
);

alter table hx.hx_clue_order comment '线索订单表(hx_clue_order)';

/*==============================================================*/
/* Table: hx_patient                                            */
/*==============================================================*/
create table hx.hx_patient
(
   CODE                 varchar(40) not null comment '患者code',
   SHOP_NO              varchar(40) comment '分店编号',
   SHOP_NAME            varchar(100) comment '分店名称',
   MERCHANT_NO          varchar(40) comment '商户编号',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   NAME                 varchar(20) comment '姓名',
   SEX                  varchar(10) comment '性别',
   TYPE                 varchar(6) comment '患者类型(PT普通LS临时)',
   CASE_NO              varchar(40) comment '病历号',
   BIRTH_DATE           date comment '出生年月日',
   AGE                  int comment '年龄',
   NATIONALITY          varchar(20) comment '国籍',
   IDNO                 varchar(20) comment '身份证',
   PHONE                varchar(20) comment '手机号',
   PHONE_REMARK         varchar(20) comment '手机号备注',
   PHONE_NO             varchar(20) comment '电话号',
   PHONE_NO_REMARK      varchar(20) comment '电话号备注',
   WECHAT               varchar(40) comment '微信',
   QQ_NO                varchar(20) comment 'QQ号',
   MAIL                 varchar(40) comment '邮箱',
   PROVINCE_CODE        varchar(40) comment '省code',
   PROVINCE             varchar(100) comment '省',
   CITY_CODE            varchar(40) comment '市code',
   CITY                 varchar(100) comment '市',
   AREA_CODE            varchar(40) comment '区code',
   AREA                 varchar(100) comment '区',
   ADDR_DETAIL          varchar(100) comment '详细地址',
   ADDR_INFO            varchar(255) comment '完整地址（省市区详细地址全部包含）',
   SOURCE1_CODE         varchar(40) comment '来源1code',
   SOURCE1              varchar(64) comment '来源1',
   SOURCE2_CODE         varchar(40) comment '来源2code',
   SOURCE2              varchar(64) comment '来源2',
   SOURCE3_CODE         varchar(40) comment '来源3code',
   SOURCE3              varchar(64) comment '来源3',
   REMARK               varchar(400) comment '患者备注',
   PAST_HISTORY         varchar(400) comment '既往史',
   ALLERGY_HISTORY      varchar(400) comment '过敏史',
   MEDICATION_HISTORY   varchar(400) comment '用药史',
   CREATE_TIME          datetime comment '初诊时间',
   FIRST_MEMBER_NO      varchar(40) comment '初诊医生code',
   FIRST_MEMBER_NAME    varchar(100) comment '初诊医生name',
   DUTY_MEMBER_NO       varchar(40) comment '责任医生code',
   DUTY_MEMBER_NAME     varchar(100) comment '责任医生name',
   CONS_MEMBER_NO       varchar(40) comment '咨询师code',
   CONS_MEMBER_NAME     varchar(100) comment '咨询师name',
   CREATE_ID            varchar(40) comment '创建人',
   CREATE_DATE          datetime comment '创建日期',
   UPDATE_ID            varchar(40) comment '更新人',
   UPDATE_DATE          datetime comment '更新日期',
   primary key (CODE)
);

alter table hx.hx_patient comment '患者表(hx_patient)';

/*==============================================================*/
/* Table: patient_medical                                       */
/*==============================================================*/
create table hx.patient_medical
(
   CODE                 varchar(40) not null comment 'CODE',
   PATIENT_RESERVATION_CODE varchar(40) comment '患者服务CODE：FK',
   PATIENT_NO           varchar(40) comment '患者编号：FK',
   PATIENT_NAME         varchar(100) comment '患者名称',
   DOCTOR_NO            varchar(40) comment '医生编号：FK',
   DOCTOR_NAME          varchar(100) comment '医生名称',
   ASSISTANT_NO         varchar(40) comment '助手编号：FK',
   ASSISTANT_NAME       varchar(100) comment '助手名称',
   VISITING_DATE        datetime comment '接诊时间',
   VISITING_TYPE        varchar(20) comment '就诊类型：  初诊：NEWDIAGNOSIS；复诊：REVIEW',
   MAIN_REMARK          varchar(500) comment '主诉及病史：主诉',
   MAIN_CURRENT_REMARK  varchar(500) comment '主诉及病史：现病史',
   MAIN_PAST_REMARK     varchar(500) comment '主诉及病史：既往史',
   CHECK_ORAL_REMARK    varchar(500) comment '口腔检查：口腔检查',
   CHECK_AUXILIARY_REMARK varchar(500) comment '口腔检查：辅助检查',
   PLAN_DIAGNOSIS_REMARK varchar(500) comment '诊断与治疗计划：诊断',
   PLAN_TREATMENT_REMARK varchar(500) comment '诊断与治疗计划：治疗计划',
   DM_DISPOSAL_REMARK   varchar(500) comment '处置与医嘱：处置',
   DM_MEDICAL_REMARK    varchar(500) comment '处置与医嘱：医嘱',
   OTHER_LABEL_REMARK   varchar(500) comment '其他：标签',
   OTHER_REMARK         varchar(500) comment '其他：备注',
   CREATE_DATE          datetime comment '创建时间',
   CREATE_ID            varchar(40) comment '创建人',
   CREATE_NAME          varchar(100) comment '创建人',
   REMARK               varchar(500) comment '备注',
   REMARK2              varchar(500) comment '备注',
   REMARK3              varchar(500) comment '备注',
   REMARK4              varchar(500) comment '备注',
   UPDATE_ID            varchar(40) comment '更新人',
   UPDATE_NAME          varchar(100) comment '更新人',
   UPDATE_DATE          datetime comment '更新时间',
   primary key (CODE)
);

alter table hx.patient_medical comment '患者病历';

/*==============================================================*/
/* Table: patient_service                                       */
/*==============================================================*/
create table hx.patient_service
(
   CODE                 varchar(40) not null comment 'CODE',
   PATIENT_NO           varchar(40) comment '患者编号：FK-患者记录code',
   PATIENT_NAME         varchar(100) comment '患者名称',
   MOBILE               varchar(15) comment '手机号',
   PATIENT_TYPE         varchar(15) comment '患者类型：普通：ORDINARY；待定；UNCONFIRMED',
   MEDICAL_NO           varchar(40) comment '病历编号',
   ADVISORY_DATE        datetime comment '咨询时间',
   ADVISORY_NO          varchar(40) comment '咨询师编号：FK',
   ADVISORY_NAME        varchar(100) comment '咨询师名称',
   SHOP_NO              varchar(40) comment '门诊编号：FK',
   SHOP_NAME            varchar(100) comment '门诊名称',
   MERCHANT_NO          varchar(40) comment '商户编号：FK',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   RESERVATION_DATE     datetime comment '预约时间',
   RESERVATION_DATE_LEN int comment '预约时长（分钟）：如15分钟',
   RESERVATION_DOCTOR_NO varchar(40) comment '预约医生编号：FK',
   RESERVATION_DOCTOR_NAME varchar(100) comment '预约医生名称',
   RESERVATION_TYPE     varchar(15) comment '预约类型：预约：RESERVATION；挂号：REGISTERED',
   REGISTERED_DATE      datetime comment '挂号时间',
   REGISTERED_DOCTOR_NO varchar(10) comment '挂号医生编号：FK',
   REGISTERED_DOCTOR_NAME varchar(100) comment '挂号医生名称',
   ASSISTANT_NO         varchar(40) comment '助手编号：FK',
   ASSISTANT_NAME       varchar(100) comment '助手名称',
   VISTITING_STATUS     varchar(15) comment '就诊状态：未确认：UNCONFIRMED；治疗中：TREATMENT；取消：CANCEL；治疗完成：FINISHED',
   VISITING_TYPE        varchar(20) comment '就诊类型：  初诊：NEWDIAGNOSIS；复诊：REVIEW',
   VISITING_DATE        datetime comment '接诊时间',
   TRIAGE_DATE          datetime comment '分诊时间',
   REVIEW_RESERVATION_DATE datetime comment '复诊预约时间',
   FINISHED_DATE        datetime comment '治疗完成时间',
   MEDICAL_DATE         datetime comment '编辑病历时间',
   CREATE_DATE          datetime comment '创建时间',
   CREATE_ID            varchar(40) comment '创建人',
   CREATE_NAME          varchar(100) comment '创建人名称',
   REMARK               varchar(500) comment '备注',
   REMARK2              varchar(500) comment '备注',
   REMARK3              varchar(500) comment '备注',
   REMARK4              varchar(500) comment '备注',
   UPDATE_ID            varchar(40) comment '更新人',
   UPDATE_NAME          varchar(100) comment '更新人',
   UPDATE_DATE          datetime comment '更新时间',
   primary key (CODE)
);

alter table hx.patient_service comment '患者服务（预约/挂号/就诊）';

/*==============================================================*/
/* Table: patient_service_advisory                              */
/*==============================================================*/
create table hx.patient_service_advisory
(
   CODE                 varchar(40) not null comment 'CODE',
   PATIENT_RESERVATION_CODE varchar(40) comment '患者服务CODE：FK',
   PATIENT_NO           varchar(40) comment '患者编号：FK',
   PATIENT_NAME         varchar(100) comment '患者名称',
   PROJECT_CODES        varchar(1000) comment '项目CODE用逗号分隔',
   PROJECT_NAMES        varchar(2000) comment '项目服务名称，多个用逗号分隔',
   ADVISORY_CONTENT     varchar(1000) comment '咨询内容',
   EXPLAIN_REMARK       varchar(500) comment '意向说明',
   WANT                 varchar(500) comment '成交意愿：高：HIGH；中：CENTRAL；低：LOW',
   CREATE_DATE          datetime comment '创建时间',
   CREATE_ID            varchar(40) comment '创建人',
   REMARK               varchar(500) comment '备注',
   REMARK2              varchar(500) comment '备注',
   REMARK3              varchar(500) comment '备注',
   REMARK4              varchar(500) comment '备注',
   primary key (CODE)
);

alter table hx.patient_service_advisory comment '患者服务咨询';

/*==============================================================*/
/* Table: patient_service_choose                                */
/*==============================================================*/
create table hx.patient_service_choose
(
   CODE                 varchar(40) not null comment 'CODE',
   PATIENT_RESERVATION_CODE varchar(40) comment '患者服务CODE',
   PROJECT_CODE         varchar(40) comment '项目CODE',
   PROJECT_NAME         varchar(100) comment '项目名称',
   PROJECT_PROPERTY_CODE varchar(40) comment '服务项目属性CODE',
   PROJECT_PROPERTY_NAME varchar(100) comment '服务项目属性名称',
   CREATE_DATE          datetime comment '创建时间',
   CREATE_ID            varchar(40) comment '创建人',
   REMARK               varchar(500) comment '备注',
   REMARK2              varchar(500) comment '备注',
   REMARK3              varchar(500) comment '备注',
   REMARK4              varchar(500) comment '备注',
   primary key (CODE)
);

alter table hx.patient_service_choose comment '患者服务选择';

/*==============================================================*/
/* Table: server_detail                                         */
/*==============================================================*/
create table hx.server_detail
(
   CODE                 varchar(40) not null comment 'CODE',
   SERVER_CODE          varchar(40) comment '服务CODE（fk:server_info.code）',
   USER_TYPE            varchar(40) comment '客户类型',
   SERVER_NUM           int comment '数量',
   PRICE                bigint comment '单价（分为单位）',
   IS_SHOP              varchar(10) comment '是否到店 (YES 是 NO 否)',
   SERVER_DESC          varchar(200) comment '描述',
   CREATE_DATE          datetime comment '创建时间',
   primary key (CODE)
);

alter table hx.server_detail comment '系统服务项';

/*==============================================================*/
/* Table: server_info                                           */
/*==============================================================*/
create table hx.server_info
(
   CODE                 varchar(40) not null comment 'CODE',
   MERCHANT_NO          varchar(40) comment '商户编号',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   SERVER_NAME          varchar(128) comment '产品名称',
   PRICE                bigint comment '价格（分为单位）',
   ORIGINAL_PRICE       bigint comment '原价（分为单位）',
   CTX                  text comment '产品说明',
   STATUS               varchar(6) comment '状态（use 启用 unuse 禁用）',
   UPDATE_ID            varchar(40) comment '更新人',
   UPDATE_DATE          datetime comment '更新时间',
   CREATE_ID            varchar(40) comment '创建人',
   CREATE_DATE          datetime comment '创建时间',
   primary key (CODE)
);

alter table hx.server_info comment '系统服务';

/*==============================================================*/
/* Table: shop_config                                           */
/*==============================================================*/
create table hx.shop_config
(
   CODE                 varchar(40) not null comment 'CODE',
   SHOP_NO              varchar(40) comment '门诊编号',
   SHOP_NAME            varchar(100) comment '门诊名称',
   MERCHANT_NO          varchar(40) comment '商户编号',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   LABLE_NAME           varchar(64) comment '标签名',
   LABLE_NO             varchar(40) comment '标签NO',
   PARENT_CODE          varchar(40) comment '父CODE',
   UPDATE_ID            varchar(40) comment '更新人',
   UPDATE_DATE          datetime comment '更新时间',
   CREATE_ID            varchar(40) comment '创建人',
   CREATE_DATE          datetime comment '创建时间',
   INDEX_NO             int comment '排序',
   primary key (CODE)
);

alter table hx.shop_config comment '门店配置';

/*==============================================================*/
/* Table: shop_order                                            */
/*==============================================================*/
create table hx.shop_order
(
   CODE                 varchar(40) not null comment 'CODE',
   SHOP_NO              varchar(40) comment '门诊编号',
   SHOP_NAME            varchar(100) comment '门诊名称',
   MERCHANT_NO          varchar(40) comment '商户编号',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   ORDER_NO             varchar(40) comment '订单编号',
   ORDER_TYPE           varchar(6) comment '订单类型（SERVER:服务）',
   SERVE_CODE           varchar(40) comment '服务CODE',
   SERVE_NAME           varchar(128) comment '服务名称',
   MEMBER_NO_GUID       varchar(40) comment '下单人编号',
   MEMBER_NAME_GUID     varchar(100) comment '下单人姓名',
   MOBILE               varchar(16) comment '下单人电话',
   SERIAL_NUM           varchar(64) comment '支付流水编号',
   PAY_TYPE             varchar(6) comment '付款方式(WX:微信，ALI:支付宝，BANK:银行转账)',
   AMOUNT               bigint comment '付款金额（分为单位）',
   PAY_TIME             datetime comment '付款时间',
   PAY_CERT             varchar(1280) comment '支付凭证,多张逗号分隔',
   STATUS               varchar(6) comment '审核状态(WAIT:待审，PASS:通过，UNPASS：拒绝)',
   UPDATE_ID            varchar(40) comment '更新人',
   UPDATE_DATE          datetime comment '更新时间',
   CREATE_ID            varchar(40) comment '创建人',
   CREATE_DATE          datetime comment '创建时间',
   REMARK               varchar(200) comment '备注',
   REMARK2              varchar(200) comment '备注1',
   REMARK3              varchar(200) comment '备注2',
   primary key (CODE)
);

alter table hx.shop_order comment '门店订单';

/*==============================================================*/
/* Table: shop_schedule                                         */
/*==============================================================*/
create table hx.shop_schedule
(
   CODE                 varchar(40) not null comment 'CODE',
   SHOP_NO              varchar(40) comment '门诊编号',
   SHOP_NAME            varchar(100) comment '门诊名称',
   MERCHANT_NO          varchar(40) comment '商户编号',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   SCHEDULE_NAME        varchar(64) comment '班次名',
   WORK_TIME            time comment '上班时间',
   OFF_TIME             time comment '下班时间',
   APT_FLAG             varchar(6) comment '是否可约诊appointment（N否 Y是)',
   DEL_FLAG             varchar(6) comment '是否删除（N未删除 Y删除）',
   UPDATE_ID            varchar(40) comment '更新人',
   UPDATE_DATE          datetime comment '更新时间',
   CREATE_ID            varchar(40) comment '创建人',
   CREATE_DATE          datetime comment '创建时间',
   primary key (CODE)
);

alter table hx.shop_schedule comment '门店班次';

/*==============================================================*/
/* Table: shop_server                                           */
/*==============================================================*/
create table hx.shop_server
(
   CODE                 varchar(40) not null comment 'CODE',
   SHOP_NO              varchar(40) comment '门诊编号',
   SHOP_NAME            varchar(100) comment '门诊名称',
   MERCHANT_NO          varchar(40) comment '商户编号',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   SERVER_CODE          varchar(40) comment '服务code',
   SERVER_NAME          varchar(128) comment '服务名称',
   PRICE                bigint comment '价格',
   ORIGINAL_PRICE       bigint comment '原价（分为单位）',
   ORDER_NO             varchar(40) comment '订单号',
   UPDATE_ID            varchar(40) comment '更新人',
   UPDATE_DATE          datetime comment '更新时间',
   CREATE_ID            varchar(40) comment '创建人',
   CREATE_DATE          datetime comment '创建时间',
   primary key (CODE)
);

alter table hx.shop_server comment '门诊服务';

/*==============================================================*/
/* Table: shop_server_detail                                    */
/*==============================================================*/
create table hx.shop_server_detail
(
   CODE                 varchar(40) not null comment 'CODE',
   SHOP_SERVER_CODE     varchar(40) comment '门诊服务code(fk:shop_server.code)',
   SERVER_CODE          varchar(40) comment '服务code(fk:server_info.code)',
   SERVER_NAME          varchar(128) comment '服务名称',
   USER_TYPE            varchar(40) comment '客户类型',
   PRICE                bigint comment '单价（分为单位）',
   SERVER_NUM           int comment '总数量',
   USE_NUM              int comment '已使用数量',
   UNUSE_NUM            int comment '剩余数量',
   FREEZE_NUM           int comment '冻结数量',
   UPDATE_ID            varchar(40) comment '更新人',
   UPDATE_DATE          datetime comment '更新时间',
   CREATE_ID            varchar(40) comment '创建人',
   CREATE_DATE          datetime comment '创建时间',
   IS_SHOP              varchar(10) comment '是否到店 (YES 是 NO 否)',
   SERVER_DESC          varchar(200) comment '描述',
   primary key (CODE)
);

alter table hx.shop_server_detail comment '门诊服务项';



ALTER TABLE `hx`.`shop_config`
ADD INDEX `HX_INDEX_CFG_PCODE` (`PARENT_CODE`) ;

ALTER TABLE `hx`.`patient_service_advisory` 
ADD COLUMN `VISITING_DATE` datetime(0) NULL COMMENT '接诊时间' AFTER `PATIENT_NAME`;

ALTER TABLE `hx`.`patient_service`
MODIFY COLUMN `REGISTERED_DOCTOR_NO`  varchar(40)   NULL DEFAULT NULL COMMENT '挂号医生编号：FK'  ;



ALTER TABLE `hx`.`patient_service` 
ADD COLUMN `VISTITING_ADVISORY_DATE` DATETIME NULL COMMENT '咨询师接诊时间' AFTER `VISITING_DATE`;