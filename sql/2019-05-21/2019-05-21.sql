#门店最新版升级 脚本 2019.05.20
UPDATE oms.sys_menu  SET `href` = '	/im/listHtml5' where href = '/im/list'; 

#BUG修改- 长度和工单不一致
ALTER TABLE hx.`hx_patient`
MODIFY COLUMN `NAME`  varchar(64)  NULL DEFAULT NULL COMMENT '姓名'  ;

#BUG修改 -长度和工单不一致
ALTER TABLE hx.`hx_clue`
MODIFY COLUMN `NAME`  varchar(64)  NULL DEFAULT NULL COMMENT '姓名'  ;