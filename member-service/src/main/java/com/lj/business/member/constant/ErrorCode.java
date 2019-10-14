package com.lj.business.member.constant;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

/**
 * 类说明：会员错误代码定义类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 * CreateDate: 2017-5-28
 */
public class ErrorCode {

	/** 新增预报名信息错误. */
	public static final String FORECAST_NAME_ADD_ERROR = "forecast_name_add_error";
	/** 删除预报名信息错误. */
	public static final String FORECAST_NAME_DEL_ERROR = "forecast_name_del_error";
	/** 预报名信息更新错误. */
	public static final String FORECAST_NAME_UPDATE_ERROR = "forecast_name_update_error";
	/** 查找预报名信息错误. */
	public static final String FORECAST_NAME_FIND_ERROR = "forecast_name_find_error";
	/** 预报名信息分页查询错误. */
	public static final String FORECAST_NAME_FIND_PAGE_ERROR = "forecast_name_find_page_error";
	/** 预报名信息不存在错误. */
	public static final String FORECAST_NAME_NOT_EXIST_ERROR = "forecast_name_not_exist_error";
	/** 重复预报名错误. */
	public static final String FORECAST_NAME_REPEAT_ERROR = "forecast_name_repeat_error";

	/** 新增终端日志文件信息错误. */
	public static final String TERMINAL_LOG_FILES_ADD_ERROR = "terminal_log_files_add_error";
	/** 删除终端日志文件信息错误. */
	public static final String TERMINAL_LOG_FILES_DEL_ERROR = "terminal_log_files_del_error";
	/** 终端日志文件信息更新错误. */
	public static final String TERMINAL_LOG_FILES_UPDATE_ERROR = "terminal_log_files_update_error";
	/** 查找终端日志文件信息错误. */
	public static final String TERMINAL_LOG_FILES_FIND_ERROR = "terminal_log_files_find_error";
	/** 终端日志文件信息分页查询错误. */
	public static final String TERMINAL_LOG_FILES_FIND_PAGE_ERROR = "terminal_log_files_find_page_error";
	/** 终端日志文件信息不存在错误. */
	public static final String TERMINAL_LOG_FILES_NOT_EXIST_ERROR = "terminal_log_files_not_exist_error";

    /** 新增导购助手管理表信息错误. */
    public static final String GM_ASSISTANT_SHOP_ADD_ERROR = "gm_assistant_shop_add_error";
    /** 删除导购助手管理表信息错误. */
    public static final String GM_ASSISTANT_SHOP_DEL_ERROR = "gm_assistant_shop_del_error";
    /** 导购助手管理表信息更新错误. */
    public static final String GM_ASSISTANT_SHOP_UPDATE_ERROR = "gm_assistant_shop_update_error";
    /** 查找导购助手管理表信息错误. */
    public static final String GM_ASSISTANT_SHOP_FIND_ERROR = "gm_assistant_shop_find_error";
    /** 导购助手管理表信息分页查询错误. */
    public static final String GM_ASSISTANT_SHOP_FIND_PAGE_ERROR = "gm_assistant_shop_find_page_error";
    /** 导购助手管理表信息不存在错误. */
    public static final String GM_ASSISTANT_SHOP_NOT_EXIST_ERROR = "gm_assistant_shop_not_exist_error";

    /** 终端IM登录错误. */
	public static final String TERMINAL_LOGIN_ERROR = "terminal_login_error";
	/** 终端IM登出错误. */
	public static final String TERMINAL_LOGOUT_ERROR = "terminal_logout_error";

	/** 新增终端IM状态信息错误. */
	public static final String TERMINAL_IM_STATUS_ADD_ERROR = "terminal_im_status_add_error";
	/** 删除终端IM状态信息错误. */
	public static final String TERMINAL_IM_STATUS_DEL_ERROR = "terminal_im_status_del_error";
	/** 终端IM状态信息更新错误. */
	public static final String TERMINAL_IM_STATUS_UPDATE_ERROR = "terminal_im_status_update_error";
	/** 查找终端IM状态信息错误. */
	public static final String TERMINAL_IM_STATUS_FIND_ERROR = "terminal_im_status_find_error";
	/** 终端IM状态信息分页查询错误. */
	public static final String TERMINAL_IM_STATUS_FIND_PAGE_ERROR = "terminal_im_status_find_page_error";
	/** 终端IM状态信息不存在错误. */
	public static final String TERMINAL_IM_STATUS_NOT_EXIST_ERROR = "terminal_im_status_not_exist_error";

	/** 新增终端终端信息错误. */
	public static final String SHOP_TERMINAL_ADD_ERROR = "shop_terminal_add_error";
	/** 删除终端终端信息错误. */
	public static final String SHOP_TERMINAL_DEL_ERROR = "shop_terminal_del_error";
	/** 终端终端信息更新错误. */
	public static final String SHOP_TERMINAL_UPDATE_ERROR = "shop_terminal_update_error";
	/** 查找终端终端信息错误. */
	public static final String SHOP_TERMINAL_FIND_ERROR = "shop_terminal_find_error";
	/** 终端终端信息分页查询错误. */
	public static final String SHOP_TERMINAL_FIND_PAGE_ERROR = "shop_terminal_find_page_error";
	/** 终端终端信息不存在错误. */
	public static final String SHOP_TERMINAL_NOT_EXIST_ERROR = "shop_terminal_not_exist_error";
	/** 终端终端不是正常状态. */
	public static final String SHOP_TERMINAL_NOT_NORMAL_ERROR = "shop_terminal_not_normal_error";
	/** 终端终端没有绑定微信. */
	public static final String SHOP_TERMINAL_NOT_BIND_WX = "shop_terminal_not_bind_wx";
	/** 导购绑定微信必须与终端绑定微信一致. */
	public static final String SHOP_TERMINAL_NOT_SAME_WX = "shop_terminal_not_same_wx";
	/** 终端版本更新失败 */
	public static final String SHOP_TERMINAL_UPDATE_VERSION_ERROR = "shop_terminal_update_version_error";
	/** 工作微信号不是终端有效微信号 */
	public static final String SHOP_TERMINAL_NOWX_NOT_VALID_ERROR = "shop_terminal_nowx_not_valid_error";
	/** 错误的工作密钥 */
	public static final String SHOP_TERMINAL_WORK_KEY_ERROR = "shop_terminal_work_key_error";
	/** 下发签到命令失败 */
	public static final String SHOP_TERMINAL_SIGN_ERROR = "shop_terminal_sign_error";
	/** 签到失败 */
	public static final String SHOP_TERMINAL_SEND_SIGN_ERROR = "shop_terminal_send_sign_error";
	/** 没有配置微信支付密码 */
	public static final String SHOP_TERMINAL_NOT_CONFIG_PWD = "shop_terminal_not_config_pwd";
	/** 终端微信密码更新失败 */
	public static final String SHOP_TERMINAL_UPDATE_PWD_ERROR = "shop_terminal_update_pwd_error";
	/** 终端串号重复 */
	public static final String SHOP_TERMINAL_DUPLICATE_IMEI_ERROR = "shop_terminal_duplicate_imei_error";
	/** 没有绑定导购助手. */
	public static final String GM_ASSIS_NOT_EXIXS_ERROR = "gm_assis_not_exixs_error";

	/** 新增添加微信好友信息错误. */
	public static final String ADD_FRIENDS_ADD_ERROR = "add_friends_add_error";
	/** 取消绑定导购信息错误. */
	public static final String ADD_FRIENDS_CANCLE_ERROR = "add_friends_cancle_error";
	/** 删除添加微信好友信息错误. */
	public static final String ADD_FRIENDS_DEL_ERROR = "add_friends_del_error";
	/** 添加微信好友信息更新错误. */
	public static final String ADD_FRIENDS_UPDATE_ERROR = "add_friends_update_error";
	/** 查找添加微信好友信息错误. */
	public static final String ADD_FRIENDS_FIND_ERROR = "add_friends_find_error";
	/** 添加微信好友信息分页查询错误. */
	public static final String ADD_FRIENDS_FIND_PAGE_ERROR = "add_friends_find_page_error";
	/** 添加微信好友信息不存在错误. */
	public static final String ADD_FRIENDS_NOT_EXIST_ERROR = "add_friends_not_exist_error";
	/** 重复添加微信好友错误. */
	public static final String ADD_FRIENDS_REPEATEDLY_ERROR = "add_friends_repeatedly_error";
	/** 所属终端其他导购已添加该客户. */
	public static final String ADD_FRIENDS_REPEATEDLY_OTHER_ERROR = "add_friends_repeatedly_other_error";
	/** 不能添加同事微信. */
	public static final String ADD_FRIENDS_COLLEAGUE_ERROR = "add_friends_colleague_error";
	/** 已分配新增客户所属导购错误. */
	public static final String ADD_FRIENDS_HAND_DISTRIBUTION_ERROR = "add_friends_hand_distribution_error";
	/** 分配新增客户错误. */
	public static final String ADD_FRIENDS_DISTRIBUTION_ERROR = "add_friends_distribution_error";
	/** 转移分配客户 **/
	public static final String ADD_FRIENDS_TRANSFER_ERROR = "add_friends_transfer_error";
	/** 已是终端微信好友，但未认领. */
	public static final String ADD_FRIENDS_NOT_CLAIMED_ERROR = "add_friends_not_claimed_error";
	/** 终端微信正在同步通讯录. */
	public static final String FRIENDS_SYNCING_ERROR = "friends_syncing_error";

	/** 上传凭证文件为空. */
	public static final String CRED_IMAGE_IS_EMPTY = "cred_image_is_empty";

	/** 新增服务类型选择表信息错误. */
	public static final String SERVICE_TYPE_CHOOSE_ADD_ERROR = "service_type_choose_add_error";
	/** 删除服务类型选择表信息错误. */
	public static final String SERVICE_TYPE_CHOOSE_DEL_ERROR = "service_type_choose_del_error";
	/** 服务类型选择表信息更新错误. */
	public static final String SERVICE_TYPE_CHOOSE_UPDATE_ERROR = "service_type_choose_update_error";
	/** 查找服务类型选择表信息错误. */
	public static final String SERVICE_TYPE_CHOOSE_FIND_ERROR = "service_type_choose_find_error";
	/** 服务类型选择表信息分页查询错误. */
	public static final String SERVICE_TYPE_CHOOSE_FIND_PAGE_ERROR = "service_type_choose_find_page_error";
	/** 服务类型选择表信息不存在错误. */
	public static final String SERVICE_TYPE_CHOOSE_NOT_EXIST_ERROR = "service_type_choose_not_exist_error";

	/** 新增服务人员作品表信息错误. */
	public static final String SERVICE_PRODUCT_ADD_ERROR = "service_product_add_error";
	/** 删除服务人员作品表信息错误. */
	public static final String SERVICE_PRODUCT_DEL_ERROR = "service_product_del_error";
	/** 服务人员作品表信息更新错误. */
	public static final String SERVICE_PRODUCT_UPDATE_ERROR = "service_product_update_error";
	/** 查找服务人员作品表信息错误. */
	public static final String SERVICE_PRODUCT_FIND_ERROR = "service_product_find_error";
	/** 服务人员作品表信息分页查询错误. */
	public static final String SERVICE_PRODUCT_FIND_PAGE_ERROR = "service_product_find_page_error";
	/** 服务人员作品表信息不存在错误. */
	public static final String SERVICE_PRODUCT_NOT_EXIST_ERROR = "service_product_not_exist_error";

	/** 新增服务项目属性取值表信息错误. */
	public static final String PROJECT_VALUE_ADD_ERROR = "project_value_add_error";
	/** 删除服务项目属性取值表信息错误. */
	public static final String PROJECT_VALUE_DEL_ERROR = "project_value_del_error";
	/** 服务项目属性取值表信息更新错误. */
	public static final String PROJECT_VALUE_UPDATE_ERROR = "project_value_update_error";
	/** 查找服务项目属性取值表信息错误. */
	public static final String PROJECT_VALUE_FIND_ERROR = "project_value_find_error";
	/** 服务项目属性取值表信息分页查询错误. */
	public static final String PROJECT_VALUE_FIND_PAGE_ERROR = "project_value_find_page_error";
	/** 服务项目属性取值表信息不存在错误. */
	public static final String PROJECT_VALUE_NOT_EXIST_ERROR = "project_value_not_exist_error";

	/** 新增服务项目属性表. */
	public static final String PROJECT_PROPERTY_ADD_ERROR = "project_property_add_error";
	/** 删除服务项目表信息错误. */
	public static final String PROJECT_PROPERTY_DEL_ERROR = "project_property_del_error";
	/** 服务项目属性表信息更新错误. */
	public static final String PROJECT_PROPERTY_UPDATE_ERROR = "project_property_update_error";
	/** 查找服务项目属性表信息错误. */
	public static final String PROJECT_PROPERTY_FIND_ERROR = "project_property_find_error";
	/** 服务项目属性表信息分页查询错误. */
	public static final String PROJECT_PROPERTY_FIND_PAGE_ERROR = "project_property_find_page_error";
	/** 服务项目属性表信息不存在错误. */
	public static final String PROJECT_PROPERTY_NOT_EXIST_ERROR = "project_property_not_exist_error";

	/** 新增服务项目表信息错误. */
	public static final String SERVICE_PROJECT_ADD_ERROR = "service_project_add_error";
	/** 删除服务项目表信息错误. */
	public static final String SERVICE_PROJECT_DEL_ERROR = "service_project_del_error";
	/** 服务项目表信息更新错误. */
	public static final String SERVICE_PROJECT_UPDATE_ERROR = "service_project_update_error";
	/** 查找服务项目表信息错误. */
	public static final String SERVICE_PROJECT_FIND_ERROR = "service_project_find_error";
	/** 服务项目表信息分页查询错误. */
	public static final String SERVICE_PROJECT_FIND_PAGE_ERROR = "service_project_find_page_error";
	/** 服务项目表信息不存在错误. */
	public static final String SERVICE_PROJECT_NOT_EXIST_ERROR = "service_project_not_exist_error";

	/** 上传会员头像文件为空. */
	public static final String HEAD_IMAGE_IS_EMPTY = "head_image_is_empty";

	/** 上传文件为空. */
	public static final String FILE_IS_EMPTY = "file_is_empty";

	/** 新增客户兴趣指数表信息错误. */
	public static final String INTERESTPM_ADD_ERROR = "interestpm_add_error";
	/** 编辑客户兴趣指数表信息错误. */
	public static final String INTERESTPM_EDIT_ERROR = "interestpm_edit_error";
	/** 查找客户兴趣指数表信息错误. */
	public static final String INTERESTPM_FIND_ERROR = "interestpm_find_error";
	/** 客户兴趣指数表表信息分页查询错误. */
	public static final String INTERESTPM_FIND_PAGE_ERROR = "interestpm_find_page_error";

	/** 新增商户服务协议表信息错误. */
	public static final String AGREEMENTMERCHANT_ADD_ERROR = "agreementmerchant_add_error";
	/** 编辑商户服务协议表信息错误. */
	public static final String AGREEMENTMERCHANT_EDIT_ERROR = "agreementmerchant_edit_error";
	/** 查找商户服务协议表信息错误. */
	public static final String AGREEMENTMERCHANT_FIND_ERROR = "agreementmerchant_find_error";
	/** 商户服务协议表信息分页查询错误. */
	public static final String AGREEMENTMERCHANT_FIND_PAGE_ERROR = "agreementmerchant_find_page_error";
	/** 商户服务协议信息不存在. */
	public static final String AGREEMENTMERCHANT_NOT_EXIST_ERROR = "agreementmerchant_not_exist_error";

	/** 新增优秀员工表信息错误. */
	public static final String EXGUIDER_ADD_ERROR = "exguider_add_error";
	/** 编辑优秀员工表信息错误. */
	public static final String EXGUIDER_EDIT_ERROR = "exguider_edit_error";
	/** 查找优秀员工表信息错误. */
	public static final String EXGUIDER_FIND_ERROR = "exguider_find_error";

	/** 新增商户表信息错误. */
	public static final String MERCHANT_ADD_ERROR = "merchant_add_error";
	/** 删除商户表信息错误. */
	public static final String MERCHANT_DEL_ERROR = "merchant_del_error";
	/** 商户表信息更新错误. */
	public static final String MERCHANT_UPDATE_ERROR = "merchant_update_error";
	/** 查找商户表信息错误. */
	public static final String MERCHANT_FIND_ERROR = "merchant_find_error";
	/** 商户表信息分页查询错误. */
	public static final String MERCHANT_FIND_PAGE_ERROR = "merchant_find_page_error";
	/** 商户表信息不存在错误. */
	public static final String MERCHANT_NOT_EXIST_ERROR = "merchant_not_exist_error";

	/** 新增导购表信息错误. */
	public static final String GUID_MEMBER_ADD_ERROR = "guid_member_add_error";
	/** 删除导购表信息错误. */
	public static final String GUID_MEMBER_DEL_ERROR = "guid_member_del_error";
	/** 导购表信息更新错误. */
	public static final String GUID_MEMBER_UPDATE_ERROR = "guid_member_update_error";
	/** 查找导购表信息错误. */
	public static final String GUID_MEMBER_FIND_ERROR = "guid_member_find_error";
	/** 导购表信息分页查询错误. */
	public static final String GUID_MEMBER_FIND_PAGE_ERROR = "guid_member_find_page_error";
	/** 导购表信息不存在错误. */
	public static final String GUID_MEMBER_NOT_EXIST_ERROR = "guid_member_not_exist_error";

	/** 新增商户表信息错误. */
	public static final String LOGIN_CHECK_ADD_ERROR = "login_check_add_error";
	/** 删除商户表信息错误. */
	public static final String LOGIN_CHECK_DEL_ERROR = "login_check_del_error";
	/** 商户表信息更新错误. */
	public static final String LOGIN_CHECK_UPDATE_ERROR = "login_check_update_error";
	/** 查找商户表信息错误. */
	public static final String LOGIN_CHECK_FIND_ERROR = "login_check_find_error";
	/** 商户表信息分页查询错误. */
	public static final String LOGIN_CHECK_FIND_PAGE_ERROR = "login_check_find_page_error";
	/** 商户表信息不存在错误. */
	public static final String LOGIN_CHECK_NOT_EXIST_ERROR = "login_check_not_exist_error";

	/** 新增管理人员表信息错误. */
	public static final String MANAGER_MEMBER_ADD_ERROR = "manager_member_add_error";
	/** 删除管理人员表信息错误. */
	public static final String MANAGER_MEMBER_DEL_ERROR = "manager_member_del_error";
	/** 管理人员表信息更新错误. */
	public static final String MANAGER_MEMBER_UPDATE_ERROR = "manager_member_update_error";
	/** 查找管理人员表信息错误. */
	public static final String MANAGER_MEMBER_FIND_ERROR = "manager_member_find_error";
	/** 管理人员表信息分页查询错误. */
	public static final String MANAGER_MEMBER_FIND_PAGE_ERROR = "manager_member_find_page_error";
	/** 管理人员表信息不存在错误. */
	public static final String MANAGER_MEMBER_NOT_EXIST_ERROR = "manager_member_not_exist_error";

	/** 管理人员降职错误. */
	public static final String MANAGER_MEMBER_DEMOTION_ERROR = "manager_member_demotion_error";
	/** 管理人员离职错误. */
	public static final String MANAGER_MEMBER_DIMISSION_ERROR = "manager_member_dimission_error";
	/** 管理人员升职错误. */
	public static final String MANAGER_MEMBER_PROMOTION_ERROR = "manager_member_promotion_error";

	/** 新增登录信息记录表信息错误. */
	public static final String MEMBER_LOGIN_INFO_ADD_ERROR = "member_login_info_add_error";
	/** 删除登录信息记录表信息错误. */
	public static final String MEMBER_LOGIN_INFO_DEL_ERROR = "member_login_info_del_error";
	/** 登录信息记录表信息更新错误. */
	public static final String MEMBER_LOGIN_INFO_UPDATE_ERROR = "member_login_info_update_error";
	/** 查找登录信息记录表信息错误. */
	public static final String MEMBER_LOGIN_INFO_FIND_ERROR = "member_login_info_find_error";
	/** 登录信息记录表信息分页查询错误. */
	public static final String MEMBER_LOGIN_INFO_FIND_PAGE_ERROR = "member_login_info_find_page_error";
	/** 登录信息记录表不存在错误. */
	public static final String MEMBER_LOGIN_INFO_NOT_EXIST_ERROR = "member_login_info_not_exist_error";

	/** 新增客户会员信息错误. */
	public static final String PERSON_MEMBER_ADD_ERROR = "person_member_add_error";
	/** 删除客户会员信息错误. */
	public static final String PERSON_MEMBER_DEL_ERROR = "person_member_del_error";
	/** 客户会员信息更新错误. */
	public static final String PERSON_MEMBER_UPDATE_ERROR = "person_member_update_error";
	/** 查找客户会员信息错误. */
	public static final String PERSON_MEMBER_FIND_ERROR = "person_member_find_error";
	/** 查找客户车辆信息错误. */
	public static final String PERSON_CAR_INFO_FIND_ERROR = "person_car_info_find_error";
	/** 客户会员信息分页查询错误. */
	public static final String PERSON_MEMBER_FIND_PAGE_ERROR = "person_member_find_page_error";
	/** 客户会员信息不存在错误. */
	public static final String PERSON_MEMBER_NOT_EXIST_ERROR = "person_member_not_exist_error";
	/** 查找客户微信会员信息错误. */
	public static final String FIND_PMWXBP_INFO = "find_pmwxbp_info";
	/** 缓存导购客户关联信息错误. */
	public static final String CACHE_GM_MEMBER_RELATE_INFO_ERROR = "cache_gm_member_relate_info_error";
	/** 从缓存中获取导购客户关联信息错误. */
	public static final String GET_GM_MEMBER_RELATE_INFO_FROM_REDIS_ERROR = "get_gm_member_relate_info_from_redis_error";

	/** 新增客户会员基础表信息错误. */
	public static final String PERSON_MEMBER_BASE_ADD_ERROR = "person_member_base_add_error";
	/** 删除客户会员基础表信息错误. */
	public static final String PERSON_MEMBER_BASE_DEL_ERROR = "person_member_base_del_error";
	/** 客户会员基础表信息更新错误. */
	public static final String PERSON_MEMBER_BASE_UPDATE_ERROR = "person_member_base_update_error";
	/** 查找客户会员基础表信息错误. */
	public static final String PERSON_MEMBER_BASE_FIND_ERROR = "person_member_base_find_error";
	/** 客户会员基础表信息分页查询错误. */
	public static final String PERSON_MEMBER_BASE_FIND_PAGE_ERROR = "person_member_base_find_page_error";
	/** 客户会员基础表信息不存在错误. */
	public static final String PERSON_MEMBER_BASE_NOT_EXIST_ERROR = "person_member_base_not_exist_error";
	/** 客户会员基础表信息已存在错误. */
	public static final String PERSON_MEMBER_BASE_EXIST_ERROR = "person_member_base_exist_error";

	/** 新增客户标签表信息错误. */
	public static final String PM_LABEL_ADD_ERROR = "pm_label_add_error";
	/** 删除客户标签表信息错误. */
	public static final String PM_LABEL_DEL_ERROR = "pm_label_del_error";
	/** 客户标签表信息更新错误. */
	public static final String PM_LABEL_UPDATE_ERROR = "pm_label_update_error";
	/** 查找客户标签表信息错误. */
	public static final String PM_LABEL_FIND_ERROR = "pm_label_find_error";
	/** 客户标签表信息分页查询错误. */
	public static final String PM_LABEL_FIND_PAGE_ERROR = "pm_label_find_page_error";
	/** 客户标签表信息不存在. */
	public static final String PM_LABEL_NOT_EXIST_ERROR = "pm_label_not_exist_error";

	/** 新增客户分类表信息错误. */
	public static final String PM_TYPE_ADD_ERROR = "pm_type_add_error";
	/** 删除客户分类表信息错误. */
	public static final String PM_TYPE_DEL_ERROR = "pm_type_del_error";
	/** 客户分类表信息更新错误. */
	public static final String PM_TYPE_UPDATE_ERROR = "pm_type_update_error";
	/** 查找客户分类表信息错误. */
	public static final String PM_TYPE_FIND_ERROR = "pm_type_find_error";
	/** 客户分类表信息分页查询错误. */
	public static final String PM_TYPE_FIND_PAGE_ERROR = "pm_type_find_page_error";
	/** 客户分类表信息不存在. */
	public static final String PM_TYPE_NOT_EXIST_ERROR = "pm_type_not_exist_error";

	/** 新增终端表信息错误. */
	public static final String SHOP_ADD_ERROR = "shop_add_error";
	/** 删除终端表信息错误. */
	public static final String SHOP_DEL_ERROR = "shop_del_error";
	/** 终端表信息更新错误. */
	public static final String SHOP_UPDATE_ERROR = "shop_update_error";
	/** 查找终端表信息错误. */
	public static final String SHOP_FIND_ERROR = "shop_find_error";
	/** 终端表信息分页查询错误. */
	public static final String SHOP_FIND_PAGE_ERROR = "shop_find_page_error";
	/** 终端表信息不存在错误. */
	public static final String SHOP_NOT_EXIST_ERROR = "shop_not_exist_error";

	/** 终端代码（商户自定义）已存在错误. */
	public static final String SHOP_NO_MERCHANT_EXIST_ERROR  = "shop_no_merchant_exist_error";

	/** 会员未审核. */
	public static final String MBR_MEMBER_STATUS_IS_INIT = "mbr_member_status_is_init";

	/** 会员审核未通过. */
	public static final String MBR_MEMBER_STATUS_IS_UNPASS = "mbr_member_status_is_unpass";

	/** 会员状态为正常. */
	public static final String MBR_MEMBER_STATUS_IS_NORMAL = "mbr_member_status_is_normal";

	/** 会员已冻结. */
	public static final String MBR_MEMBER_STATUS_IS_FREEZE = "mbr_member_status_is_freeze";

	/** 会员已注销. */
	public static final String MBR_MEMBER_STATUS_IS_CANCEL = "mbr_member_status_is_cancel";

	/** 会员已离职. */
	public static final String MBR_MEMBER_STATUS_IS_LEAVE = "mbr_member_status_is_leave";
	/** 商户会员未审核. */
	public static final String MBR_MERCHANT_MEMBER_STATUS_IS_INIT = "mbr_merchant_member_status_is_init";

	/** 商户会员审核未通过. */
	public static final String MBR_MERCHANT_MEMBER_STATUS_IS_UNPASS = "mbr_merchant_member_status_is_unpass";

	/** 商户会员状态为正常. */
	public static final String MBR_MERCHANT_MEMBER_STATUS_IS_NORMAL = "mbr_merchant_member_status_is_normal";

	/** 商户会员已冻结. */
	public static final String MBR_MERCHANT_MEMBER_STATUS_IS_FREEZE = "mbr_merchant_member_status_is_freeze";

	/** 商户会员已注销. */
	public static final String MBR_MERCHANT_MEMBER_STATUS_IS_CANCEL = "mbr_merchant_member_status_is_cancel";

	/** 会员未实名. */
	public static final String MBR_MEMBER_NOT_BEEN_NAME_AUTHENTICATION = "mbr_member_not_been_name_authentication";

	/** 会员已实名. */
	public static final String MBR_MEMBER_HAVE_BEEN_NAME_AUTHENTICATION = "mbr_member_have_been_name_authentication";

	/** 个人会员登录错误. */
	public static final String PERSON_LOGIN_ERROR = "person_login_error";
	/** 添加会员登录信息错误. */
	public static final String PERSON_ADD_LOGIN_INFO_ERROR = "person_add_login_info_error";
	/** 会员不存在. */
	public static final String PERSON_ERROR_NOT_EXIST = "person_error_not_exist";
	/** 店长没有导购信息错误. */
	public static final String MANAGER_NOT_HAVE_GM_ERROR = "manager_not_have_gm_error";

	/** 个人会员登录错误：会员被冻结. */
	public static final String PERSON_LOGIN_ERROR_FREEZE = "person_login_error_freeze";
	/** 个人会员登录错误：登录失败次数过多，会员被登录锁定. */
	public static final String PERSON_LOGIN_ERROR_AUTOLOCK = "person_login_error_autolock";
	/** 个人会员登录错误：登录密码错误，错误次数已超过限制，账户暂时冻结. */
	public static final String PERSON_LOGIN_ERROR_PSW_FREEZE = "person_login_error_psw_freeze";
	/** 个人会员登录错误：登录密码错误. */
	public static final String PERSON_LOGIN_ERROR_PSW = "person_login_error_psw";
	/** 个人会员登录错误：不是本人手机. */
	public static final String PERSON_LOGIN_ERROR_MOBILE = "person_login_error_mobile";
	/** 个人会员登录错误：会员被冻结. */
	public static final String PERSON_LOGIN_ERROR_CANCEL = "person_login_error_cancel";
	/** 个人会员登录错误：需要密码登录. */
	public static final String PERSON_LOGIN_ERROR_PSW_LOGIN = "person_login_error_psw_login";
	/** 个人会员登录错误：商户试用期结束. */
	public static final String MERCHANT_PROBATION_ERROR_END = "merchant_probation_error_end";

	/** 查询参数必须填写 */
	public static final String QUERY_CONDITION_EMPTY = "query_condition_empty";

	/** 参数必须填写. */
	public static final String FLAG_EMPTY = "flag_empty";

	/** 新增客户动态详情信息错误. */
	public static final String BEHAVIOR_INFO_PM_ADD_ERROR = "behavior_info_pm_add_error";
	/** 删除客户动态详情信息错误. */
	public static final String BEHAVIOR_INFO_PM_DEL_ERROR = "behavior_info_pm_del_error";
	/** 客户动态详情信息更新错误. */
	public static final String BEHAVIOR_INFO_PM_UPDATE_ERROR = "behavior_info_pm_update_error";
	/** 查找客户动态详情信息错误. */
	public static final String BEHAVIOR_INFO_PM_FIND_ERROR = "behavior_info_pm_find_error";
	/** 客户动态详情信息分页查询错误. */
	public static final String BEHAVIOR_INFO_PM_FIND_PAGE_ERROR = "behavior_info_pm_find_page_error";
	/** 客户动态详情信息不存在错误. */
	public static final String BEHAVIOR_INFO_PM_NOT_EXIST_ERROR = "behavior_info_pm_not_exist_error";

	/** 新增客户最近动态信息错误. */
	public static final String BEHAVIOR_PM_ADD_ERROR = "behavior_pm_add_error";
	/** 删除客户最近动态信息错误. */
	public static final String BEHAVIOR_PM_DEL_ERROR = "behavior_pm_del_error";
	/** 客户最近动态信息更新错误. */
	public static final String BEHAVIOR_PM_UPDATE_ERROR = "behavior_pm_update_error";
	/** 查找客户最近动态信息错误. */
	public static final String BEHAVIOR_PM_FIND_ERROR = "behavior_pm_find_error";
	/** 客户最近动态信息分页查询错误. */
	public static final String BEHAVIOR_PM_FIND_PAGE_ERROR = "behavior_pm_find_page_error";
	/** 客户最近动态信息不存在错误. */
	public static final String BEHAVIOR_PM_NOT_EXIST_ERROR = "behavior_pm_not_exist_error";

	/** 新增客户关注操作信息错误. */
	public static final String CUST_KEEP_ADD_ERROR = "cust_keep_add_error";
	/** 删除客户关注操作信息错误. */
	public static final String CUST_KEEP_DEL_ERROR = "cust_keep_del_error";
	/** 客户关注操作信息更新错误. */
	public static final String CUST_KEEP_UPDATE_ERROR = "cust_keep_update_error";
	/** 查找客户关注操作信息错误. */
	public static final String CUST_KEEP_FIND_ERROR = "cust_keep_find_error";
	/** 客户关注操作信息分页查询错误. */
	public static final String CUST_KEEP_FIND_PAGE_ERROR = "cust_keep_find_page_error";
	/** 客户关注操作信息不存在错误. */
	public static final String CUST_KEEP_NOT_EXIST_ERROR = "cust_keep_not_exist_error";

	/** 新增设备信息错误. */
	public static final String PHONE_INFO_ADD_ERROR = "phone_info_add_error";
	/** 删除设备信息错误. */
	public static final String PHONE_INFO_DEL_ERROR = "phone_info_del_error";
	/** 设备信息更新错误. */
	public static final String PHONE_INFO_UPDATE_ERROR = "phone_info_update_error";
	/** 查找设备信息错误. */
	public static final String PHONE_INFO_FIND_ERROR = "phone_info_find_error";
	/** 设备信息分页查询错误. */
	public static final String PHONE_INFO_FIND_PAGE_ERROR = "phone_info_find_page_error";
	/** 设备信息不存在错误. */
	public static final String PHONE_INFO_NOT_EXIST_ERROR = "phone_info_not_exist_error";

	/** 客户管理首页分页查询错误. */
	public static final String FIND_PMTYPE_INDEX_PAGE_ERROR = "find_pmtype_index_page_error";

	/** 时间转换错误. */
	public static final String DATA_TRANS_ERROR = "data_trans_error";

	/** 客户已经存在. */
	public static final String MEMBER_REPERAT_ERROR = "data_trans_error";

	/** 手机号已存在. */
	public static final String PERSON_MEMBER_MOBILE_EXIST = "mobile_exist_error";

	/** 客户已经关联过导购. */
	public static final String MEMBER_GM_REPERAT_ERROR = "member_gm_reperat_error";

	/** 新增积分设置表信息错误. */
	public static final String INTEGRAL_SET_ADD_ERROR = "integral_set_add_error";
	/** 删除积分设置表信息错误. */
	public static final String INTEGRAL_SET_DEL_ERROR = "integral_set_del_error";
	/** 积分设置表信息更新错误. */
	public static final String INTEGRAL_SET_UPDATE_ERROR = "integral_set_update_error";
	/** 查找积分设置表信息错误. */
	public static final String INTEGRAL_SET_FIND_ERROR = "integral_set_find_error";
	/** 积分设置表信息分页查询错误. */
	public static final String INTEGRAL_SET_FIND_PAGE_ERROR = "integral_set_find_page_error";
	/** 积分设置表信息不存在错误. */
	public static final String INTEGRAL_SET_NOT_EXIST_ERROR = "integral_set_not_exist_error";

	/** 新增导购积分明细表信息错误. */
	public static final String GUID_MEMBER_INTEGRAL_ADD_ERROR = "guid_member_integral_add_error";
	/** 删除导购积分明细表信息错误. */
	public static final String GUID_MEMBER_INTEGRAL_DEL_ERROR = "guid_member_integral_del_error";
	/** 导购积分明细表信息更新错误. */
	public static final String GUID_MEMBER_INTEGRAL_UPDATE_ERROR = "guid_member_integral_update_error";
	/** 查找导购积分明细表信息错误. */
	public static final String GUID_MEMBER_INTEGRAL_FIND_ERROR = "guid_member_integral_find_error";
	/** 导购积分明细表信息分页查询错误. */
	public static final String GUID_MEMBER_INTEGRAL_FIND_PAGE_ERROR = "guid_member_integral_find_page_error";
	/** 导购积分明细表信息不存在错误. */
	public static final String GUID_MEMBER_INTEGRAL_NOT_EXIST_ERROR = "guid_member_integral_not_exist_error";

	/** 新增导购积分日总表信息错误. */
	public static final String GUID_MEMBER_INTEGRAL_DAY_ADD_ERROR = "guid_member_integral_day_add_error";
	/** 删除导购积分日总表信息错误. */
	public static final String GUID_MEMBER_INTEGRAL_DAY_DEL_ERROR = "guid_member_integral_day_del_error";
	/** 导购积分日总表信息更新错误. */
	public static final String GUID_MEMBER_INTEGRAL_DAY_UPDATE_ERROR = "guid_member_integral_day_update_error";
	/** 查找导购积分日总表信息错误. */
	public static final String GUID_MEMBER_INTEGRAL_DAY_FIND_ERROR = "guid_member_integral_day_find_error";
	/** 导购积分日总表信息分页查询错误. */
	public static final String GUID_MEMBER_INTEGRAL_DAY_FIND_PAGE_ERROR = "guid_member_integral_day_find_page_error";
	/** 导购积分日总表信息不存在错误. */
	public static final String GUID_MEMBER_INTEGRAL_DAY_NOT_EXIST_ERROR = "guid_member_integral_day_not_exist_error";

	/** 新增导购行为信息记录表信息错误. */
	public static final String GUIDMEMBER_ACTION_INFO_ADD_ERROR = "guidmember_action_info_add_error";
	/** 删除导购行为信息记录表信息错误. */
	public static final String GUIDMEMBER_ACTION_INFO_DEL_ERROR = "guidmember_action_info_del_error";
	/** 导购行为信息记录表信息更新错误. */
	public static final String GUIDMEMBER_ACTION_INFO_UPDATE_ERROR = "guidmember_action_info_update_error";
	/** 查找导购行为信息记录表信息错误. */
	public static final String GUIDMEMBER_ACTION_INFO_FIND_ERROR = "guidmember_action_info_find_error";
	/** 导购行为信息记录表信息分页查询错误. */
	public static final String GUIDMEMBER_ACTION_INFO_FIND_PAGE_ERROR = "guidmember_action_info_find_page_error";
	/** 导购行为信息记录表信息不存在错误. */
	public static final String GUIDMEMBER_ACTION_INFO_NOT_EXIST_ERROR = "guidmember_action_info_not_exist_error";

	/** 新增服务人员表信息错误. */
	public static final String SERVICE_PERSON_ADD_ERROR = "service_person_add_error";
	/** 删除服务人员表信息错误. */
	public static final String SERVICE_PERSON_DEL_ERROR = "service_person_del_error";
	/** 服务人员表信息更新错误. */
	public static final String SERVICE_PERSON_UPDATE_ERROR = "service_person_update_error";
	/** 查找服务人员表信息错误. */
	public static final String SERVICE_PERSON_FIND_ERROR = "service_person_find_error";
	/** 服务人员表信息分页查询错误. */
	public static final String SERVICE_PERSON_FIND_PAGE_ERROR = "service_person_find_page_error";
	/** 服务人员表信息不存在错误. */
	public static final String SERVICE_PERSON_NOT_EXIST_ERROR = "service_person_not_exist_error";

	/** 新增服务人员作品表信息错误. */
	public static final String SERVICE_PERSON_PRODUCT_ADD_ERROR = "service_person_product_add_error";
	/** 删除服务人员作品表信息错误. */
	public static final String SERVICE_PERSON_PRODUCT_DEL_ERROR = "service_person_product_del_error";
	/** 服务人员作品表信息更新错误. */
	public static final String SERVICE_PERSON_PRODUCT_UPDATE_ERROR = "service_person_product_update_error";
	/** 查找服务人员作品表信息错误. */
	public static final String SERVICE_PERSON_PRODUCT_FIND_ERROR = "service_person_product_find_error";
	/** 服务人员作品表信息分页查询错误. */
	public static final String SERVICE_PERSON_PRODUCT_FIND_PAGE_ERROR = "service_person_product_find_page_error";
	/** 服务人员作品表信息不存在错误. */
	public static final String SERVICE_PERSON_PRODUCT_NOT_EXIST_ERROR = "service_person_product_not_exist_error";

	/** 新增服务类型信息错误. */
	public static final String SERVICE_TYPE_ADD_ERROR = "service_type_add_error";
	/** 删除服务类型信息错误. */
	public static final String SERVICE_TYPE_DEL_ERROR = "service_type_del_error";
	/** 服务类型信息更新错误. */
	public static final String SERVICE_TYPE_UPDATE_ERROR = "service_type_update_error";
	/** 查找服务类型信息错误. */
	public static final String SERVICE_TYPE_FIND_ERROR = "service_type_find_error";
	/** 服务类型信息分页查询错误. */
	public static final String SERVICE_TYPE_FIND_PAGE_ERROR = "service_type_find_page_error";
	/** 服务类型信息不存在错误. */
	public static final String SERVICE_TYPE_NOT_EXIST_ERROR = "service_type_not_exist_error";

	/** 查询客户最大版本号错误. */
	public static final String FIND_MAX_PM_VERSION = "find_max_pm_version";

	/** 分页查询IM-微信好友失败. */
	public static final String FIND_IM_FRIENDS_ERROR = "find_im_friends_error";

	/** 新增导购名片表信息错误. */
	public static final String GUID_CARD_ADD_ERROR = "guid_card_add_error";
	/** 删除导购名片表信息错误. */
	public static final String GUID_CARD_DEL_ERROR = "guid_card_del_error";
	/** 导购名片表信息更新错误. */
	public static final String GUID_CARD_UPDATE_ERROR = "guid_card_update_error";
	/** 查找导购名片表信息错误. */
	public static final String GUID_CARD_FIND_ERROR = "guid_card_find_error";
	/** 导购名片表信息分页查询错误. */
	public static final String GUID_CARD_FIND_PAGE_ERROR = "guid_card_find_page_error";
	/** 导购名片表信息不存在错误. */
	public static final String GUID_CARD_NOT_EXIST_ERROR = "guid_card_not_exist_error";
	/** 查找openId信息错误. */
	public static final String GUID_CARD_FIND_OPENID_ERROR = "guid_card_find_openid_error";

	/** 新增导购名片与客户关联表信息错误. */
	public static final String GUID_CARD_CUSTOMER_ADD_ERROR = "guid_card_customer_add_error";
	/** 删除导购名片与客户关联表信息错误. */
	public static final String GUID_CARD_CUSTOMER_DEL_ERROR = "guid_card_customer_del_error";
	/** 导购名片与客户关联表信息更新错误. */
	public static final String GUID_CARD_CUSTOMER_UPDATE_ERROR = "guid_card_customer_update_error";
	/** 查找导购名片与客户关联表信息错误. */
	public static final String GUID_CARD_CUSTOMER_FIND_ERROR = "guid_card_customer_find_error";
	/** 导购名片与客户关联表信息分页查询错误. */
	public static final String GUID_CARD_CUSTOMER_FIND_PAGE_ERROR = "guid_card_customer_find_page_error";
	/** 导购名片与客户关联表信息不存在错误. */
	public static final String GUID_CARD_CUSTOMER_NOT_EXIST_ERROR = "guid_card_customer_not_exist_error";

	/** 导购名片与客户关联表信息不存在错误. */
	public static final String GUID_CARD_TYPE_ERROR = "guid_card_type_error";
	/** 导购没有绑定微信. */
	public static final String GM_NOT_BIND_WX = "gm_not_bind_wx";
	/** 客户分组错误（成单客户和暂停客户禁止分组）. */
    public static final String PERSON_MEMBER_GROUP_ERROR = "person_member_group_error";
	/** 查询未联系顾客分组信息错误. */
    public static final String UNCONTACT_PM_TYPE_ERROR = "uncontact_pm_type_error";
    /** 查询分组的未联系客户错误  */
    public static final String GROUPED_UNCONTACT_MEMBER_ERROR = "grouped_uncontact_member_error";
    /** 根据商户号查询标签列表错误  */
    public static final String FIND_PMLABEL_BY_MERCHANTNO_ERROR = "find_pmlabel_by_merchantno_error";
    /** 更新客户标签错误  */
    public static final String CHANGE_PMLABEL_ERROR = "change_pmlabel_error";
    /** 根据客户号和商户号查询客户标签错误.  */
    public static final String FIND_PMLABEL_BY_MEMBERNO_AND_MERCHANTNO_ERROR = "find_pmlabel_by_memberno_and_merchantno_error";
    /** 客户已经关联过该终端下的导购错误.  */
    public static final String MEMBER_REPERAT_UNDER_SHOP_ERROR = "member_reperat_under_shop_error";

	/** 新增导购开放平台扩展信息错误. */
	public static final String GUID_MEMBER_EXT_ADD_ERROR = "guid_member_ext_add_error";
	/** 导购开放平台扩展信息更新错误. */
	public static final String GUID_MEMBER_EXT_UPDATE_ERROR = "guid_member_ext_update_error";
	/** 查找导购开放平台扩展信息错误. */
	public static final String GUID_MEMBER_EXT_FIND_ERROR = "guid_member_ext_find_error";
	/** 导购开放平台扩展信息分页查询错误. */
	public static final String GUID_MEMBER_EXT_FIND_PAGE_ERROR = "guid_member_ext_find_page_error";
	/** 导购开放平台扩展信息不存在错误. */
	public static final String GUID_MEMBER_EXT_NOT_EXIST_ERROR = "guid_member_ext_not_exist_error";

	/** 新增分店开放平台扩展信息错误. */
	public static final String SHOP_EXT_ADD_ERROR = "shop_ext_add_error";
	/** 分店开放平台扩展信息更新错误. */
	public static final String SHOP_EXT_UPDATE_ERROR = "shop_ext_update_error";
	/** 查找分店开放平台扩展信息错误. */
	public static final String SHOP_EXT_FIND_ERROR = "shop_ext_find_error";
	/** 分店开放平台扩展信息分页查询错误. */
	public static final String SHOP_EXT_FIND_PAGE_ERROR = "shop_ext_find_page_error";
	/** 分店开放平台扩展信息不存在错误. */
	public static final String SHOP_EXT_NOT_EXIST_ERROR = "shop_ext_not_exist_error";

	/** 新增积分商城-会员信息信息错误. */
	public static final String IEM_PERSON_MEMBER_EXT_ADD_ERROR = "iem_person_member_ext_add_error";
	/** 删除积分商城-会员信息信息错误. */
	public static final String IEM_PERSON_MEMBER_EXT_DEL_ERROR = "iem_person_member_ext_del_error";
	/** 积分商城-会员信息信息更新错误. */
	public static final String IEM_PERSON_MEMBER_EXT_UPDATE_ERROR = "iem_person_member_ext_update_error";
	/** 查找积分商城-会员信息信息错误. */
	public static final String IEM_PERSON_MEMBER_EXT_FIND_ERROR = "iem_person_member_ext_find_error";
	/** 积分商城-会员信息信息分页查询错误. */
	public static final String IEM_PERSON_MEMBER_EXT_FIND_PAGE_ERROR = "iem_person_member_ext_find_page_error";
	/** 积分商城-会员信息信息不存在错误. */
	public static final String IEM_PERSON_MEMBER_EXT_NOT_EXIST_ERROR = "iem_person_member_ext_not_exist_error";
	/** 积分商城-会员注册错误. */
	public static final String IEM_PERSON_MEMBER_REGISTER_ERROR = "iem_person_member_register_error";
	/** 新增积分商城-会员信息已存在错误. */
	public static final String IEM_PERSON_MEMBER_EXIST_ERROR = "iem_person_member_exist_error";

	/** 新增装修需求信息表信息错误. */
	public static final String FIT_UP_INFO_ADD_ERROR = "fit_up_info_add_error";
	/** 删除装修需求信息表信息错误. */
	public static final String FIT_UP_INFO_DEL_ERROR = "fit_up_info_del_error";
	/** 装修需求信息表信息更新错误. */
	public static final String FIT_UP_INFO_UPDATE_ERROR = "fit_up_info_update_error";
	/** 查找装修需求信息表信息错误. */
	public static final String FIT_UP_INFO_FIND_ERROR = "fit_up_info_find_error";
	/** 装修需求信息表信息分页查询错误. */
	public static final String FIT_UP_INFO_FIND_PAGE_ERROR = "fit_up_info_find_page_error";
	/** 装修需求信息表信息不存在错误. */
	public static final String FIT_UP_INFO_NOT_EXIST_ERROR = "fit_up_info_not_exist_error";

	/** 装修需求图片为空. */
    public static final String FIT_UP_IMAGE_IS_EMPTY = "fit_up_image_is_empty";

	/** 新增积分商城-收货地址信息错误. */
	public static final String IEM_MEMBER_ADDRESS_ADD_ERROR = "iem_member_address_add_error";
	/** 删除积分商城-收货地址信息错误. */
	public static final String IEM_MEMBER_ADDRESS_DEL_ERROR = "iem_member_address_del_error";
	/** 积分商城-收货地址信息更新错误. */
	public static final String IEM_MEMBER_ADDRESS_UPDATE_ERROR = "iem_member_address_update_error";
	/** 查找积分商城-收货地址信息错误. */
	public static final String IEM_MEMBER_ADDRESS_FIND_ERROR = "iem_member_address_find_error";
	/** 积分商城-收货地址信息分页查询错误. */
	public static final String IEM_MEMBER_ADDRESS_FIND_PAGE_ERROR = "iem_member_address_find_page_error";
	/** 积分商城-收货地址信息不存在错误. */
	public static final String IEM_MEMBER_ADDRESS_NOT_EXIST_ERROR = "iem_member_address_not_exist_error";

	/** 新增积分商城-会员邀请信息错误. */
	public static final String IEM_INVITE_MEMBER_ADD_ERROR = "iem_invite_member_add_error";
	/** 删除积分商城-会员邀请信息错误. */
	public static final String IEM_INVITE_MEMBER_DEL_ERROR = "iem_invite_member_del_error";
	/** 积分商城-会员邀请信息更新错误. */
	public static final String IEM_INVITE_MEMBER_UPDATE_ERROR = "iem_invite_member_update_error";
	/** 查找积分商城-会员邀请信息错误. */
	public static final String IEM_INVITE_MEMBER_FIND_ERROR = "iem_invite_member_find_error";
	/** 积分商城-会员邀请信息分页查询错误. */
	public static final String IEM_INVITE_MEMBER_FIND_PAGE_ERROR = "iem_invite_member_find_page_error";
	/** 积分商城-会员邀请信息不存在错误. */
	public static final String IEM_INVITE_MEMBER_NOT_EXIST_ERROR = "iem_invite_member_not_exist_error";
	/** 积分商城-会员绑定公众号openId错误. */
    public static final String IEM_PERSON_MEMBER_EXT_BINDING_GZHOPENID_ERROR = "iem_person_member_ext_binding_gzhopenid_error";
    /** 积分商城-CRM-获取会员信息错误. */
    public static final String IEM_CRM_MEMBER_FIND_ERROR = "iem_crm_member_find_error";
    /** 积分商城-CRM-获取会员积分信息错误. */
    public static final String IEM_CRM_MEMBER_INTEGRAL_FIND_ERROR = "iem_crm_member_integral_find_error";
    /** 积分商城-CRM-更新会员积分信息错误. */
    public static final String IEM_CRM_MEMBER_INTEGRAL_UPDATE_ERROR = "iem_crm_member_integral_update_error";

    /** 新增分公司银行账户已存在错误. */
    public static final String COMPANY_BANK_ACCOUNT_ADD_EXIST_ERROR = "company_bank_account_add_exist_error";
    /** 新增分公司银行账户信息错误. */
    public static final String COMPANY_BANK_ACCOUNT_ADD_ERROR = "company_bank_account_add_error";
    /** 删除分公司银行账户信息错误. */
    public static final String COMPANY_BANK_ACCOUNT_DEL_ERROR = "company_bank_account_del_error";
    /** 分公司银行账户信息更新错误. */
    public static final String COMPANY_BANK_ACCOUNT_UPDATE_ERROR = "company_bank_account_update_error";
    /** 查找分公司银行账户信息错误. */
    public static final String COMPANY_BANK_ACCOUNT_FIND_ERROR = "company_bank_account_find_error";
    /** 分公司银行账户信息分页查询错误. */
    public static final String COMPANY_BANK_ACCOUNT_FIND_PAGE_ERROR = "company_bank_account_find_page_error";
    /** 分公司银行账户信息不存在错误. */
    public static final String COMPANY_BANK_ACCOUNT_NOT_EXIST_ERROR = "company_bank_account_not_exist_error";

    /** 新增分公司信息错误. */
    public static final String BRANCH_COMPANY_ADD_ERROR = "branch_company_add_error";
    /** 删除分公司信息错误. */
    public static final String BRANCH_COMPANY_DEL_ERROR = "branch_company_del_error";
    /** 分公司信息更新错误. */
    public static final String BRANCH_COMPANY_UPDATE_ERROR = "branch_company_update_error";
    /** 查找分公司信息错误. */
    public static final String BRANCH_COMPANY_FIND_ERROR = "branch_company_find_error";
    /** 分公司信息分页查询错误. */
    public static final String BRANCH_COMPANY_FIND_PAGE_ERROR = "branch_company_find_page_error";
    /** 分公司信息不存在错误. */
    public static final String BRANCH_COMPANY_NOT_EXIST_ERROR = "branch_company_not_exist_error";
    /** 分公司信息已存在错误. */
    public static final String BRANCH_COMPANY_HAS_EXIST_ERROR = "branch_company_has_exist_error";

    /** 新增经销商申请信息错误. */
    public static final String DEALER_APPLY_ADD_ERROR = "dealer_apply_add_error";
    /** 删除经销商申请信息错误. */
    public static final String DEALER_APPLY_DEL_ERROR = "dealer_apply_del_error";
    /** 经销商申请信息更新错误. */
    public static final String DEALER_APPLY_UPDATE_ERROR = "dealer_apply_update_error";
    /** 查找经销商申请信息错误. */
    public static final String DEALER_APPLY_FIND_ERROR = "dealer_apply_find_error";
    /** 经销商申请信息分页查询错误. */
    public static final String DEALER_APPLY_FIND_PAGE_ERROR = "dealer_apply_find_page_error";
    /** 经销商申请信息不存在错误. */
    public static final String DEALER_APPLY_NOT_EXIST_ERROR = "dealer_apply_not_exist_error";
    /** 经销商申请审核错误. */
    public static final String DEALER_APPLY_AUDIT_ERROR = "dealer_apply_audit_error";

    /** 新增经销商推送配置信息错误. */
    public static final String COMANPY_PUSH_CONFIG_ADD_ERROR = "comanpy_push_config_add_error";
    /** 删除经销商推送配置信息错误. */
    public static final String COMANPY_PUSH_CONFIG_DEL_ERROR = "comanpy_push_config_del_error";
    /** 经销商推送配置信息更新错误. */
    public static final String COMANPY_PUSH_CONFIG_UPDATE_ERROR = "comanpy_push_config_update_error";
    /** 查找经销商推送配置信息错误. */
    public static final String COMANPY_PUSH_CONFIG_FIND_ERROR = "comanpy_push_config_find_error";
    /** 经销商推送配置信息分页查询错误. */
    public static final String COMANPY_PUSH_CONFIG_FIND_PAGE_ERROR = "comanpy_push_config_find_page_error";
    /** 经销商推送配置信息不存在错误. */
    public static final String COMANPY_PUSH_CONFIG_NOT_EXIST_ERROR = "comanpy_push_config_not_exist_error";

	/** 新增微信推送配置信息错误. */
	public static final String WX_PUSH_CONFIG_ADD_ERROR = "wx_push_config_add_error";
	/** 删除微信推送配置信息错误. */
	public static final String WX_PUSH_CONFIG_DEL_ERROR = "wx_push_config_del_error";
	/** 微信推送配置信息更新错误. */
	public static final String WX_PUSH_CONFIG_UPDATE_ERROR = "wx_push_config_update_error";
	/** 查找微信推送配置信息错误. */
	public static final String WX_PUSH_CONFIG_FIND_ERROR = "wx_push_config_find_error";
	/** 微信推送配置信息分页查询错误. */
	public static final String WX_PUSH_CONFIG_FIND_PAGE_ERROR = "wx_push_config_find_page_error";
	/** 微信推送配置信息不存在错误. */
	public static final String WX_PUSH_CONFIG_NOT_EXIST_ERROR = "wx_push_config_not_exist_error";

	/** 新增群信息表信息错误. */
	public static final String CHAT_ROOM_ADD_ERROR = "chat_room_add_error";
	/** 删除群信息表信息错误. */
	public static final String CHAT_ROOM_DEL_ERROR = "chat_room_del_error";
	/** 群信息表信息更新错误. */
	public static final String CHAT_ROOM_UPDATE_ERROR = "chat_room_update_error";
	/** 查找群信息表信息错误. */
	public static final String CHAT_ROOM_FIND_ERROR = "chat_room_find_error";
	/** 群信息表信息分页查询错误. */
	public static final String CHAT_ROOM_FIND_PAGE_ERROR = "chat_room_find_page_error";
	/** 群信息表信息不存在错误. */
	public static final String CHAT_ROOM_NOT_EXIST_ERROR = "chat_room_not_exist_error";
	/** 群成员超过39人错误. */
	public static final String CHAT_ROOM_CHAOYUAN_ERROR = "chat_room_chaoyuan_error";

	/** 新增群成员表信息错误. */
	public static final String CHAT_ROOM_MEMBER_ADD_ERROR = "chat_room_member_add_error";
	/** 删除群成员表信息错误. */
	public static final String CHAT_ROOM_MEMBER_DEL_ERROR = "chat_room_member_del_error";
	/** 群成员表信息更新错误. */
	public static final String CHAT_ROOM_MEMBER_UPDATE_ERROR = "chat_room_member_update_error";
	/** 查找群成员表信息错误. */
	public static final String CHAT_ROOM_MEMBER_FIND_ERROR = "chat_room_member_find_error";
	/** 群成员表信息分页查询错误. */
	public static final String CHAT_ROOM_MEMBER_FIND_PAGE_ERROR = "chat_room_member_find_page_error";
	/** 群成员表信息不存在错误. */
	public static final String CHAT_ROOM_MEMBER_NOT_EXIST_ERROR = "chat_room_member_not_exist_error";

	/** 新增天猫订单红包配置信息错误. */
	public static final String TMALL_BONUS_CONFIG_ADD_ERROR = "tmall_bonus_config_add_error";
	/** 天猫订单红包配置信息更新错误. */
	public static final String TMALL_BONUS_CONFIG_UPDATE_ERROR = "tmall_bonus_config_update_error";
	/** 查找天猫订单红包配置信息错误. */
	public static final String TMALL_BONUS_CONFIG_FIND_ERROR = "tmall_bonus_config_find_error";
	/** 天猫订单红包配置信息分页查询错误. */
	public static final String TMALL_BONUS_CONFIG_FIND_PAGE_ERROR = "tmall_bonus_config_find_page_error";
	/** 天猫订单红包配置信息不存在错误. */
	public static final String TMALL_BONUS_CONFIG_NOT_EXIST_ERROR = "tmall_bonus_config_not_exist_error";

	/** 新增天猫订单红包记录信息错误. */
	public static final String TMALL_BONUS_RECORD_ADD_ERROR = "tmall_bonus_record_add_error";
	/** 天猫订单红包记录信息更新错误. */
	public static final String TMALL_BONUS_RECORD_UPDATE_ERROR = "tmall_bonus_record_update_error";
	/** 查找天猫订单红包记录信息错误. */
	public static final String TMALL_BONUS_RECORD_FIND_ERROR = "tmall_bonus_record_find_error";
	/** 天猫订单红包记录信息分页查询错误. */
	public static final String TMALL_BONUS_RECORD_FIND_PAGE_ERROR = "tmall_bonus_record_find_page_error";
	/** 天猫订单红包记录信息不存在错误. */
	public static final String TMALL_BONUS_RECORD_NOT_EXIST_ERROR = "tmall_bonus_record_not_exist_error";

	/** 新增天猫订单信息错误. */
	public static final String TMALL_ORDER_ADD_ERROR = "tmall_order_add_error";
	/** 天猫订单信息更新错误. */
	public static final String TMALL_ORDER_UPDATE_ERROR = "tmall_order_update_error";
	/** 查找天猫订单信息错误. */
	public static final String TMALL_ORDER_FIND_ERROR = "tmall_order_find_error";
	/** 天猫订单信息分页查询错误. */
	public static final String TMALL_ORDER_FIND_PAGE_ERROR = "tmall_order_find_page_error";
	/** 天猫订单信息不存在错误. */
	public static final String TMALL_ORDER_NOT_EXIST_ERROR = "tmall_order_not_exist_error";
	/** 天猫订单验证错误. */
	public static final String TMALL_ORDER_VALIDATE_ERROR = "tmall_order_not_exist_error";

	/** 新增设置添加好友数信息错误. */
	public static final String SET_FRIEND_NUM_ADD_ERROR = "set_friend_num_add_error";
	/** 设置添加好友数信息更新错误. */
	public static final String SET_FRIEND_NUM_UPDATE_ERROR = "set_friend_num_update_error";
	/** 查找设置添加好友数信息错误. */
	public static final String SET_FRIEND_NUM_FIND_ERROR = "set_friend_num_find_error";
	/** 设置添加好友数信息分页查询错误. */
	public static final String SET_FRIEND_NUM_FIND_PAGE_ERROR = "set_friend_num_find_page_error";
	/** 设置添加好友数信息不存在错误. */
	public static final String SET_FRIEND_NUM_NOT_EXIST_ERROR = "set_friend_num_not_exist_error";
	/** 新增导购扩展热文用户记录信息错误. */
	public static final String GUID_MEMBER_RW_ADD_ERROR = "guid_member_rw_add_error";
	/** 导购扩展热文用户记录信息更新错误. */
	public static final String GUID_MEMBER_RW_UPDATE_ERROR = "guid_member_rw_update_error";
	/** 查找导购扩展热文用户记录信息错误. */
	public static final String GUID_MEMBER_RW_FIND_ERROR = "guid_member_rw_find_error";
	/** 导购扩展热文用户记录信息分页查询错误. */
	public static final String GUID_MEMBER_RW_FIND_PAGE_ERROR = "guid_member_rw_find_page_error";
	/** 导购扩展热文用户记录信息不存在错误. */
	public static final String GUID_MEMBER_RW_NOT_EXIST_ERROR = "guid_member_rw_not_exist_error";

	/** 新增加粉任务详情信息错误. */
	public static final String ADDFRIENDS_TASK_ADD_ERROR = "addfriends_task_add_error";
	/** 加粉任务详情信息更新错误. */
	public static final String ADDFRIENDS_TASK_UPDATE_ERROR = "addfriends_task_update_error";
	/** 查找加粉任务详情信息错误. */
	public static final String ADDFRIENDS_TASK_FIND_ERROR = "addfriends_task_find_error";
	/** 加粉任务详情信息分页查询错误. */
	public static final String ADDFRIENDS_TASK_FIND_PAGE_ERROR = "addfriends_task_find_page_error";
	/** 加粉任务详情信息不存在错误. */
	public static final String ADDFRIENDS_TASK_NOT_EXIST_ERROR = "addfriends_task_not_exist_error";

	/** 新增加粉任务信息错误. */
	public static final String ADDFRIENDS_TASK_DETAIL_ADD_ERROR = "addfriends_task_detail_add_error";
	/** 加粉任务信息更新错误. */
	public static final String ADDFRIENDS_TASK_DETAIL_UPDATE_ERROR = "addfriends_task_detail_update_error";
	/** 查找加粉任务信息错误. */
	public static final String ADDFRIENDS_TASK_DETAIL_FIND_ERROR = "addfriends_task_detail_find_error";
	/** 加粉任务信息分页查询错误. */
	public static final String ADDFRIENDS_TASK_DETAIL_FIND_PAGE_ERROR = "addfriends_task_detail_find_page_error";
	/** 加粉任务信息不存在错误. */
	public static final String ADDFRIENDS_TASK_DETAIL_NOT_EXIST_ERROR = "addfriends_task_detail_not_exist_error";
	/**
	 * 加粉重复错误
	 */
	public static final String ADDFRIENDS_TASK_DETAIL_ADD_REPEAT_ERROR = "addfriends_task_detail_add_repeat_error";

	/** 新增个人标签信息错误. */
	public static final String GM_LABEL_ADD_ERROR = "gm_label_add_error";
	/** 个人标签信息更新错误. */
	public static final String GM_LABEL_UPDATE_ERROR = "gm_label_update_error";
	/** 查找个人标签信息错误. */
	public static final String GM_LABEL_FIND_ERROR = "gm_label_find_error";
	/** 个人标签信息分页查询错误. */
	public static final String GM_LABEL_FIND_PAGE_ERROR = "gm_label_find_page_error";
	/** 个人标签信息不存在错误. */
	public static final String GM_LABEL_NOT_EXIST_ERROR = "gm_label_not_exist_error";
}
