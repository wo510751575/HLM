# 新增了功能：收费细致到收费项目；按日统计收费。
ALTER TABLE hx.`bill_detail`
ADD COLUMN `PAY_AMOUNT`  bigint NULL COMMENT '实收(分为单位）' AFTER `REMARK4`,
ADD COLUMN `REALLY_AMOUNT`  bigint NULL COMMENT '应收(分为单位）' AFTER `PAY_AMOUNT`,
ADD COLUMN `DEBT_AMOUNT`  bigint NULL COMMENT '欠费(分为单位）' AFTER `REALLY_AMOUNT`;

# 支付方式每日收支统计任务 (每天凌晨2点01分跑定时任务)
INSERT INTO confcenter.`cc_job_center` (`JOB_ENGLISH_NAME`, `JOB_NAME`, `SYSTEM_ALIAS_NAME`, `JOB_INTF`, `JOB_PARAM`, `IS_ENABLE`, `JOB_CALENDER`) VALUES ('stDailyPayJob', '支付方式每日收支统计任务', 'hx', 'http://127.0.0.1:8080/hx/hessian/stDailyPayJob', NULL, '1', '0 1 2 * * ? *');

# 财务报表菜单
INSERT INTO  oms.sys_menu(`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('26bc2080b5b04966a4471f3c033a7527', 'ebba8b7c400d46f78c220f702a17ba99', '0,1,ebba8b7c400d46f78c220f702a17ba99,', '财务报表', 230, '', '', '', '1', 'FinancialStatements', '1', '2019-6-19 10:45:30', '1', '2019-6-19 10:45:50', '', '0');
INSERT INTO  oms.sys_menu(`id`, `parent_id`, `parent_ids`, `name`, `sort`, `href`, `target`, `icon`, `is_show`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ('6014138419f348eeb716ff3366386067', '26bc2080b5b04966a4471f3c033a7527', '0,1,ebba8b7c400d46f78c220f702a17ba99,26bc2080b5b04966a4471f3c033a7527,', '收费报表', 30, '', '', '', '1', 'BillSt', '1', '2019-6-19 10:49:35', '1', '2019-6-19 10:49:35', '', '0');

# drop table if exists hx.st_daily_pay;
# drop table if exists hx.bill_pay_detail;
# drop table if exists hx.bill_pay_type_detail;

 
/*==============================================================*/
/* Table: st_daily_pay                                          */
/*==============================================================*/
create table hx.st_daily_pay
(
   CODE                 varchar(60) not null comment 'CODE',
   SHOP_NO              varchar(40) comment '门诊编号',
   SHOP_NAME            varchar(100) comment '门诊名称',
   MERCHANT_NO          varchar(40) comment '商户编号',
   MERCHANT_NAME        varchar(100) comment '商户名称',
   ST_DATE              date comment '统计日期',
   PAY_TYPE             varchar(40) comment '支付方式code',
   PAY_TYPE_NAME        varchar(60) comment '支付方式名称',
   PAY_AMOUNT           bigint comment '金额（分为单位）',
   PAY_MODE             varchar(8) comment '支付类型（ADD入账，SUB：出账）',
   CREATE_DATE          datetime comment '创建时间',
   primary key (CODE)
);

alter table hx.st_daily_pay comment '每日收费统计';


/*==============================================================*/
/* Table: bill_pay_detail                                       */
/*==============================================================*/
create table hx.bill_pay_detail
(
   CODE                 varchar(40) not null comment 'CODE',
   BILL_CODE            varchar(40) comment '账单code',
   OPERATE_CODE         varchar(40) comment '操作code',
   PROJECT_CODE         varchar(40) comment '项目code',
   PROJECT_NAME         varchar(100) comment '项目名称',
   PAY_AMOUNT           bigint comment '本次收费实收费用金额（分为单位）',
   REALLY_AMOUNT        bigint comment '本次收费应收金额（分为单位）',
   DEBT_AMOUNT          bigint comment '本次收费后欠费总金额（分为单位）',
   ORIGINAL_PAY_AMOUNT  bigint comment '项目原实收(分为单位）',
   ORIGINAL_REALLY_AMOUNT bigint comment '项目原应收(分为单位）',
   ORIGINAL_DEBT_AMOUNT bigint comment '项目原欠费(分为单位）',
   INDEX_NO             int comment '序号',
   primary key (CODE)
);

alter table hx.bill_pay_detail comment '收费项目详细';



/*==============================================================*/
/* Table: bill_pay_type_detail                                  */
/*==============================================================*/
create table hx.bill_pay_type_detail
(
   CODE                 varchar(40) not null comment 'CODE',
   BILL_CODE            varchar(40) comment '账单code',
   OPERATE_CODE         varchar(40) comment '操作code',
   PAY_TYPE             varchar(40) comment '支付方式',
   PAY_TYPE_NAME        varchar(64) comment '支付方式描述',
   PAY_AMOUNT           bigint comment '费用金额（分为单位）',
   INDEX_NO             int comment '序号',
   primary key (CODE)
);

alter table hx.bill_pay_type_detail comment '支付方式详细';

