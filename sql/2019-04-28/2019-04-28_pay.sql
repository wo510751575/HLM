/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/4/23 星期二 16:12:27                       */
/*==============================================================*/


drop table if exists hx.bill;

drop table if exists hx.bill_detail;

drop table if exists hx.bill_operate;

drop table if exists hx.bill_payment;

drop table if exists hx.bill_refund;

drop table if exists hx.bill_refund_detail;

drop table if exists hx.bill_snapshot;

drop table if exists hx.project_price;


/*==============================================================*/
/* Table: bill                                                  */
/*==============================================================*/
create table hx.bill
(
   CODE                 varchar(40) not null comment 'CODE',
   BILL_NO              varchar(40) comment '账单编号',
   PATIENT_NO           varchar(40) comment '患者编号',
   PATIENT_NAME         varchar(100) comment '患者名称',
   MEDICAL_NO           varchar(40) comment '患者病历号',
   SHOP_NO              varchar(40) comment '门诊编号',
   SHOP_NAME            varchar(100) comment '门诊名称',
   MERCHANT_NO          varchar(40) comment '商户编号',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   BILL_TYPE            varchar(16) comment '账单类型(MX:明细，JY:简易）',
   ORIGINAL_AMOUNT      bigint comment '账单总金额（分为单位）',
   REALLY_AMOUNT        bigint comment '账单折扣后实际应付金额（分为单位）',
   DISCOUNT_NUM         int comment '账单折扣(万分比，如8折则值为8000）',
   PAY_AMOUNT           bigint comment '已付总金额（分为单位）',
   DEBT_AMOUNT          bigint comment '欠费总金额（分为单位）',
   RT_AMOUNT            bigint comment '退款总金额(分为单位）',
   PAY_STATUS           varchar(16) comment '收费状态（UNPAY:待收费,FINISH已结清，ARREARAGE未结清）',
   RT_STATUS            varchar(6) comment '退费状态（RT:有退款，NO：无退款）',
   STATUS               varchar(8) comment '有效状态(NORMAL 正常，CANCEL：作废）',
   CLINIC_TIME          datetime comment '就诊时间',
   PAY_TIME             datetime comment '首次收费时间',
   UPDATE_ID            varchar(40) comment '更新人',
   UPDATE_DATE          datetime comment '更新时间',
   CREATE_ID            varchar(40) comment '创建人',
   CREATE_NAME          varchar(100) comment '建单人名称',
   CREATE_DATE          datetime comment '创建时间',
   REMARK               varchar(200) comment '备注',
   REMARK1              varchar(200) comment '备注1',
   REMARK2              varchar(200) comment '备注2',
   REMARK3              varchar(200) comment '备注3',
   REMARK4              varchar(200) comment '备注4',
   primary key (CODE)
);

alter table hx.bill comment '账单';

/*==============================================================*/
/* Table: bill_detail                                           */
/*==============================================================*/
create table hx.bill_detail
(
   CODE                 varchar(40) not null comment 'CODE',
   BILL_CODE            varchar(40) comment '账单code',
   SHOP_NO              varchar(40) comment '门诊编号',
   SHOP_NAME            varchar(100) comment '门诊名称',
   MERCHANT_NO          varchar(40) comment '商户编号',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   PROJECT_CODE         varchar(40) comment '项目code',
   PROJECT_NAME         varchar(100) comment '项目名称',
   PROJECT_UNIT         varchar(40) comment '单位',
   UNIT_PRICE           bigint comment '未折扣的单价',
   ITEM_DIS_UNIT_PRICE  bigint comment '单项折扣后的单价(分为单位）',
   ITEM_NUM             int comment '数量',
   ORIGINAL_AMOUNT      bigint comment '未折扣的总原价(分为单位）',
   ITEM_DISCOUNT_AMOUNT bigint comment '单项折扣总价格(分为单位）',
   DISCOUNT_ITEM        int comment '单项折扣万分比',
   RT_NUM               int comment '已退款数量',
   RT_AMOUNT            bigint comment '已退款总金额(分为单位）',
   DISCOUNT_ORDER_STATUS varchar(8) comment '是否参与整单折扣（Y:是，N:否）',
   ADVISORY_NO          varchar(40) comment '咨询师编号',
   ADVISORY_NAME        varchar(100) comment '咨询师名称',
   DOCTOR_NO            varchar(40) comment '医生编号',
   DOCTOR_NAME          varchar(100) comment '医生名称',
   ASSISTANT_NO         varchar(40) comment '助手编号',
   ASSISTANT_NAME       varchar(100) comment '助手名称',
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

alter table hx.bill_detail comment '账单明细';

/*==============================================================*/
/* Table: bill_operate                                          */
/*==============================================================*/
create table hx.bill_operate
(
   CODE                 varchar(40) not null comment 'CODE',
   SHOP_NO              varchar(40) comment '门诊编号',
   SHOP_NAME            varchar(100) comment '门诊名称',
   MERCHANT_NO          varchar(40) comment '商户编号',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   BILL_CODE            varchar(40) comment '账单code',
   OPERATE_TYPE         varchar(8) comment '操作类型（PAY收费，DEBT：收欠费，REFUND退款）',
   STATUS               varchar(16) comment '状态（INIT待处理，FINISH已处理，CANCEL取消）',
   PROCESS              varchar(16) comment '进度（UNCHECK待审核，UNPAY待收费，UNREFUND待退费，FINISH已处理）',
   CHECK_STATUS         varchar(16) comment '审核状态（UNCHECK待审核，PASS已批准，UNPASS已拒绝）',
   MEMBER_NO_GUID       varchar(40) comment '操作人编号',
   MEMBER_NAME_GUID     varchar(100) comment '操作人姓名',
   CHECKER_NO_GUID      varchar(40) comment '审核人编号',
   CHECKER_NAME_GUID    varchar(100) comment '审核人姓名',
   APPLY_TIME           datetime comment '申请时间',
   CHECK_TIME           datetime comment '审批时间',
   PAY_TYPE             varchar(400) comment '支付方式',
   PAY_TYPE_NAME        varchar(640) comment '支付方式描述',
   PAY_REMARK           varchar(1000) comment '支付方式及其金额（格式：PAY_TYPE1:支付金额2,PAY_TYPE1:支付金额2）',
   PAY_AMOUNT           bigint comment '费用金额（分为单位）',
   PAY_TIME             datetime comment '收费时间',
   RECIEVER_NO          varchar(40) comment '收费人编号',
   RECIEVER_NAME        varchar(100) comment '收费人姓名',
   ORIGINAL_AMOUNT      bigint comment '账单原欠费总金额（分为单位）',
   REALLY_AMOUNT        bigint comment '还款折扣后应收金额（分为单位）',
   DISCOUNT_NUM         int comment '还款折扣(万分比，如8折则值为8000）',
   DEBT_AMOUNT          bigint comment '还款后账单剩余欠费总金额（分为单位）',
   REFUND_TYPE          varchar(8) comment '退款类型（ALL:整单退，ITEM：按项目数量退，PART:退一部分金额）',
   BILL_PAY_AMOUNT      bigint comment '账单已收总额(账单收费总额-账单已退总额）',
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

alter table hx.bill_operate comment '账单处理';

/*==============================================================*/
/* Table: bill_payment                                          */
/*==============================================================*/
create table hx.bill_payment
(
   CODE                 varchar(40) not null comment 'CODE',
   PAY_NO               varchar(40) comment '支付编号',
   PATIENT_NO           varchar(40) comment '患者编号',
   PATIENT_NAME         varchar(100) comment '患者名称',
   MEDICAL_NO           varchar(40) comment '患者病历号',
   BILL_CODE            varchar(40) comment '账单code',
   OPERATE_CODE         varchar(40) comment '操作code',
   SHOP_NO              varchar(40) comment '门诊编号',
   SHOP_NAME            varchar(100) comment '门诊名称',
   MERCHANT_NO          varchar(40) comment '商户编号',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   PAY_TYPE             varchar(400) comment '支付方式',
   PAY_TYPE_NAME        varchar(640) comment '支付方式描述',
   PAY_REMARK           varchar(1000) comment '支付方式及其金额（格式：PAY_TYPE1:支付金额2,PAY_TYPE1:支付金额2）',
   PAY_AMOUNT           bigint comment '实际收款金额(分为单位）',
   PAY_TIME             datetime comment '收费时间',
   RECIEVER_NO          varchar(40) comment '收费人编号',
   RECIEVER_NAME        varchar(100) comment '收费人姓名',
   BIZ_TYPE             varchar(8) comment '业务类型（PAY收费，DEBT：收欠费，REFUND退款）',
   STATUS               varchar(8) comment '有效状态(NORMAL 正常，CANCEL：作废）',
   RECEIVABLE_AMT       bigint comment '应收款金额(分为单位）',
   DEBT_AMT             bigint comment '欠收款金额(分为单位）',
   PAY_MODE             varchar(8) comment '支付类型（ADD入账，SUB：出账）',
   BILL_PAY_AMOUNT      bigint comment '账单已收总额(账单收费总额-账单已退总额）',
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

alter table hx.bill_payment comment '账单支付明细';

/*==============================================================*/
/* Table: bill_refund                                           */
/*==============================================================*/
create table hx.bill_refund
(
   CODE                 varchar(40) not null comment 'CODE',
   BILL_CODE            varchar(40) comment '账单code',
   OPERATE_CODE         varchar(40) comment '操作code',
   SHOP_NO              varchar(40) comment '门诊编号',
   SHOP_NAME            varchar(100) comment '门诊名称',
   MERCHANT_NO          varchar(40) comment '商户编号',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   PATIENT_NO           varchar(40) comment '患者编号',
   PATIENT_NAME         varchar(100) comment '患者名称',
   MEDICAL_NO           varchar(40) comment '患者病历号',
   PAY_TYPE             varchar(40) comment '支付方式',
   PAY_TYPE_NAME        varchar(64) comment '支付方式描述',
   MEMBER_NO_GUID       varchar(40) comment '操作人编号',
   MEMBER_NAME_GUID     varchar(100) comment '操作人姓名',
   RT_AMOUNT            bigint comment '退款金额(分为单位）',
   REFUND_GD_NO         varchar(40) comment '退款人编号',
   REFUND_GD_NAME       varchar(100) comment '退款人姓名',
   REFUND_TIME          datetime comment '退费时间',
   REFUND_STATUS        varchar(16) comment '状态（UNCHECK待审批，UNPAY待退费，CHKREFUS:已拒绝，FINISH已退费）',
   STATUS               varchar(8) comment '有效状态(NORMAL 正常，CANCEL：作废）',
   REFUND_TYPE          varchar(8) comment '退款类型（ALL:整单退，ITEM：按项目数量退，PART:退一部分金额）',
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

alter table hx.bill_refund comment '账单退款信息';

/*==============================================================*/
/* Table: bill_refund_detail                                    */
/*==============================================================*/
create table hx.bill_refund_detail
(
   CODE                 varchar(40) not null comment 'CODE',
   BILL_CODE            varchar(40) comment '账单code',
   REFUND_CODE          varchar(40) comment '退款 CODE',
   OPERATE_CODE         varchar(40) comment '操作code',
   SHOP_NO              varchar(40) comment '门诊编号',
   SHOP_NAME            varchar(100) comment '门诊名称',
   MERCHANT_NO          varchar(40) comment '商户编号',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   PROJECT_CODE         varchar(40) comment '项目code',
   PROJECT_NAME         varchar(100) comment '项目名称',
   ITEM_NUM             int comment '退款数量',
   RETURN_AMOUNT        bigint comment '退款金额（分为单位）',
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

alter table hx.bill_refund_detail comment '退款明细';

/*==============================================================*/
/* Table: bill_snapshot                                         */
/*==============================================================*/
create table hx.bill_snapshot
(
   CODE                 varchar(40) not null comment 'CODE',
   BILL_CODE            varchar(40) comment '账单code',
   OPERATE_CODE         varchar(40) comment '操作code',
   PATIENT_NO           varchar(40) comment '患者编号',
   PATIENT_NAME         varchar(100) comment '患者名称',
   MEDICAL_NO           varchar(40) comment '患者病历号',
   SHOP_NO              varchar(40) comment '门诊编号',
   SHOP_NAME            varchar(100) comment '门诊名称',
   MERCHANT_NO          varchar(40) comment '商户编号',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   ORIGINAL_AMOUNT      bigint comment '账单总金额（分为单位）',
   REALLY_AMOUNT        bigint comment '账单折扣后实际应付金额（分为单位）',
   DISCOUNT_NUM         int comment '账单折扣(万分比，如8折则值为8000）',
   PAY_AMOUNT           bigint comment '已付总金额（分为单位）',
   DEBT_AMOUNT          bigint comment '欠费总金额（分为单位）',
   RT_AMOUNT            bigint comment '退款总金额(分为单位）',
   PAY_STATUS           varchar(16) comment '收费状态（UNPAY:待收费,FINISH已结清，ARREARAGE未结清）',
   RT_STATUS            varchar(6) comment '退费状态（RT:有退款，NO：无退款）',
   STATUS               varchar(8) comment '有效状态(NORMAL 正常，CANCEL：作废）',
   CLINIC_TIME          datetime comment '就诊时间',
   UPDATE_ID            varchar(40) comment '更新人',
   UPDATE_DATE          datetime comment '更新时间',
   CREATE_ID            varchar(40) comment '创建人',
   CREATE_NAME          varchar(100) comment '建单人名称',
   CREATE_DATE          datetime comment '创建时间',
   REMARK               varchar(200) comment '备注',
   REMARK1              varchar(200) comment '备注1',
   REMARK2              varchar(200) comment '备注2',
   REMARK3              varchar(200) comment '备注3',
   REMARK4              varchar(200) comment '备注4',
   SNAPSHOT_TIME        datetime comment '快照时间',
   primary key (CODE)
);

alter table hx.bill_snapshot comment '账单快照';

/*==============================================================*/
/* Table: project_price                                         */
/*==============================================================*/
create table hx.project_price
(
   CODE                 varchar(40) not null comment 'CODE',
   SHOP_NO              varchar(40) comment '门诊编号',
   SHOP_NAME            varchar(100) comment '门诊名称',
   MERCHANT_NO          varchar(40) comment '商户编号',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   TYPE1_NAME           varchar(100) comment '大类别名称',
   TYPE1_CODE           varchar(40) comment '大类别code',
   TYPE2_NAME           varchar(100) comment '小类别名称2',
   TYPE2_CODE           varchar(40) comment '小类别code2',
   PROJECT_NAME         varchar(100) comment '项目名称',
   PROJECT_NO           varchar(40) comment '项目编码',
   PINYIN               varchar(100) comment '项目拼音（用于检索）',
   PROJECT_UNIT         varchar(40) comment '单位',
   PRICE                bigint comment '单价（分为单位）',
   ALLOW_ITEM_DISCOUNT  varchar(6) comment '单项允许打折否（Y:是，N:否）',
   ALLOW_ORDER_DISCOUNT varchar(6) comment '整单允许打折否（Y:是，N:否）',
   INDEX_NO             int comment '排序',
   ENNAME               varchar(100) comment '英文名称',
   MIN_PRICE            bigint comment '最小单价（分为单位）',
   MAX_PRICE            bigint comment '最大单价（分为单位）',
   MIN_DISCOUNT         int comment '单项折扣下限（万分比）',
   STATUS               varchar(8) comment '是否启用use("启用"), unuse("禁用")',
   ALLOW_DEAL           varchar(8) comment '是否成交（Y:是，N:否）',
   UPDATE_ID            varchar(40) comment '更新人',
   UPDATE_DATE          datetime comment '更新时间',
   CREATE_ID            varchar(40) comment '创建人',
   CREATE_DATE          datetime comment '创建时间',
   primary key (CODE)
);

alter table hx.project_price comment '项目单价';

 
ALTER TABLE hx.`shop_config`
ADD COLUMN `REMARK`  varchar(200) NULL COMMENT '备注' AFTER `INDEX_NO`;
 
ALTER TABLE hx.`shop_config`
ADD COLUMN `ENNAME`  varchar(100) NULL COMMENT '英文名称' ;


INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('d4644e7f4a5249ba9aa7f064dc665741', 'ebba8b7c400d46f78c220f702a17ba99', '0,1,ebba8b7c400d46f78c220f702a17ba99,', '系统配置', 240, '', '', '', '1', 'HlmSysConfig', '1', '2019-4-28 10:16:33', '1', '2019-4-28 10:16:33', '', '0');
INSERT INTO oms.`sys_menu` (`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('841fd87d1f4344c783c14fbe0fa177ad', '775c014c3ca24cd9b7f0506e5837304a', '0,1,ebba8b7c400d46f78c220f702a17ba99,775c014c3ca24cd9b7f0506e5837304a,', '费用审批', 8180, '', '', '', '1', 'HlmBillCheck', '1', '2019-4-28 10:15:04', '1', '2019-4-28 10:18:57', '', '0');

INSERT INTO hx.`shop_config`  (`CODE`, `SHOP_NO`, `SHOP_NAME`, `MERCHANT_NO`, `MERCHANT_NAME`, `LABLE_NAME`, `LABLE_NO`, `PARENT_CODE`, `UPDATE_ID`, `UPDATE_DATE`, `CREATE_ID`, `CREATE_DATE`, `INDEX_NO`, `REMARK`) VALUES ('LJ_8ebd5e566b124ad2b2f8e876d5873b82', NULL, NULL, '1', NULL, '支付类型', 'pay_type', NULL, NULL, '2019-4-16 11:40:12', NULL, '2019-4-16 11:40:12', 3, NULL);
INSERT INTO hx.`shop_config`  (`CODE`, `SHOP_NO`, `SHOP_NAME`, `MERCHANT_NO`, `MERCHANT_NAME`, `LABLE_NAME`, `LABLE_NO`, `PARENT_CODE`, `UPDATE_ID`, `UPDATE_DATE`, `CREATE_ID`, `CREATE_DATE`, `INDEX_NO`, `REMARK`) VALUES ('LJ_b9f73f3f4d2f4989ab1a9315223a8732', NULL, NULL, '1', NULL, '收费项目', 'project_price', NULL, NULL, '2019-4-16 11:36:01', NULL, '2019-4-16 11:36:01', 2, NULL);
