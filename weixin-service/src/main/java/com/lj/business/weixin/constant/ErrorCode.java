package com.lj.business.weixin.constant;
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
 * @author LeoPeng
 * 
 * 
 * CreateDate: 2017-5-28
 */
public class ErrorCode {
	
	/** 新增IM临时聊天记录信息错误. */
	public static final String IM_CHAT_INFO_TEMP_ADD_ERROR = "im_chat_info_temp_add_error";
	/** 删除IM临时聊天记录信息错误. */
	public static final String IM_CHAT_INFO_TEMP_DEL_ERROR = "im_chat_info_temp_del_error";
	/** IM临时聊天记录信息更新错误. */
	public static final String IM_CHAT_INFO_TEMP_UPDATE_ERROR = "im_chat_info_temp_update_error";
	/** 查找IM临时聊天记录信息错误. */
	public static final String IM_CHAT_INFO_TEMP_FIND_ERROR = "im_chat_info_temp_find_error";
	/** IM临时聊天记录信息分页查询错误. */
	public static final String IM_CHAT_INFO_TEMP_FIND_PAGE_ERROR = "im_chat_info_temp_find_page_error";
	/** IM临时聊天记录信息不存在错误. */
	public static final String IM_CHAT_INFO_TEMP_NOT_EXIST_ERROR = "im_chat_info_temp_not_exist_error";
	/** IM同步历史聊天记录错误. */
	public static final String IM_CHAT_INFO_TEMP_SYS_ERROR = "im_chat_info_temp_sys_error";
	/**
	 * 内容包含敏感词
	 */
	public static final String INCLUDE_SENSITIVE_WORDS = "include_sensitive_words";
	/** 新增IM敏感词表信息错误. */
	public static final String IM_SENSITIVE_WORDS_ADD_ERROR = "im_sensitive_words_add_error";
	/** 删除IM敏感词表信息错误. */
	public static final String IM_SENSITIVE_WORDS_DEL_ERROR = "im_sensitive_words_del_error";
	/** IM敏感词表信息更新错误. */
	public static final String IM_SENSITIVE_WORDS_UPDATE_ERROR = "im_sensitive_words_update_error";
	/** 查找IM敏感词表信息错误. */
	public static final String IM_SENSITIVE_WORDS_FIND_ERROR = "im_sensitive_words_find_error";
	/** IM敏感词表信息分页查询错误. */
	public static final String IM_SENSITIVE_WORDS_FIND_PAGE_ERROR = "im_sensitive_words_find_page_error";
	/** IM敏感词表信息不存在错误. */
	public static final String IM_SENSITIVE_WORDS_NOT_EXIST_ERROR = "im_sensitive_words_not_exist_error";
	
	
	/** 新增微信朋友圈信息信息错误. */
	public static final String WX_FRIENDS_INFO_ADD_ERROR = "wx_friends_info_add_error";
	/** 删除微信朋友圈信息信息错误. */
	public static final String WX_FRIENDS_INFO_DEL_ERROR = "wx_friends_info_del_error";
	/** 微信朋友圈信息信息更新错误. */
	public static final String WX_FRIENDS_INFO_UPDATE_ERROR = "wx_friends_info_update_error";
	/** 查找微信朋友圈信息信息错误. */
	public static final String WX_FRIENDS_INFO_FIND_ERROR = "wx_friends_info_find_error";
	/** 微信朋友圈信息信息分页查询错误. */
	public static final String WX_FRIENDS_INFO_FIND_PAGE_ERROR = "wx_friends_info_find_page_error";
	/** 微信朋友圈信息信息不存在错误. */
	public static final String WX_FRIENDS_INFO_NOT_EXIST_ERROR = "wx_friends_info_not_exist_error";

	

	/** 个人会员更新密码：更新个人用户密码错误. */
	public static final String MEM_UPDATE_PSW_ERROR = "mem_update_psw_error";
	/** 个人会员更新密码：更新个人用户支付密码错误. */
	public static final String MEM_UPDATE_PAYPSW_ERROR = "mem_update_paypsw_error";
	/** 个人会员更新手机号：更新个人用户手机号错误. */
	public static final String MEM_UPDATE_MOBILE_ERROR = "mem_update_mobile_error";
	/** 个人会员更新密码：重置个人用户支付密码错误. */
	public static final String MEM_RESET_PAYPSW_ERROR = "mem_reset_paypsw_error";
	/** 个人会员查询密码：查询个人用户支付密码错误. */
	public static final String MEM_HAVE_PAYPSW_ERROR = "mem_have_paypsw_error";
	/** 个人会员：验证个人用户支付密码错误. */
	public static final String MEM_CHECK_PAYPSW_ERROR = "mem_check_paypsw_error";
	/** 个人会员：个人用户支付密码错误. */
	public static final String MEM_PAYPSW_ERROR = "mem_paypsw_error";
	/** 个人会员更新密码：重置个人用户密码错误. */
	public static final String MEM_RESET_PSW_ERROR = "mem_reset_psw_error";
	/** 个人会员更新信息错误. */
	public static final String PERSON_UPDATE_ERROR = "person_update_error";
	/** 个人会员实名认证错误. */
	public static final String PERSON_AUTHENTICATION_ERROR = "person_authentication_error";

	/** 平台商不存在. */
	public static final String PLATFORM_NOT_EXIST_ERROR = "platform_not_exist_error";

	/** 个人会员注册错误. */
	public static final String MEM_OPEN_ERROR = "mem_open_error";
	/** 个人会员错误-手机号已经存在. */
	public static final String PERSON_MOBILE_ERROR = "mem_open_mobile_error";
	/** 个人会员错误-EMAIL已经存在. */
	public static final String PERSON_EMAIL_ERROR = "person_open_email_error";
	/** 个人会员错误-工号已经存在. */
	public static final String PERSON_JOB_NUM_FOXCONN_ERROR = "person_job_num_foxconn_error";
	/** 个人会员错误-登录名已经存在. */
	public static final String PERSON_LOGINNAME_ERROR = "person_open_loginname_error";
	/** 个人会员错误-证件信息绑定的账号数量超过限制. */
	public static final String PERSON_CERTINFO_ERROR = "person_certinfo_error";
	/** 个人会员错误-证件信息不合法. */
	public static final String PERSON_CERTINFO_UNVALID_ERROR = "person_certinfo_unvalid_error";
	/** 个人会员错误-证件信息不全. */
	public static final String PERSON_CERTINFO_DELETION_ERROR = "person_certinfo_deletion_error";
	/** 个人会员错误-工号信息不合法. */
	public static final String PERSON_FOXCONN_JOBNUM_UNVALID_ERROR = "person_foxconn_jobnum_unvalid_error";
	/** 个人会员错误-员工信息不合法. */
	public static final String PERSON_FOXCONN_UNVALID_ERROR = "person_foxconn_unvalid_error";
	/** 个人会员更新密码：原密码错误. */
	public static final String MEM_UPDATE_ORGPSW_ERROR = "mem_update_orgpsw_error";

	/** 个人会员更新密码：会员被冻结！. */
	public static final String MEM_UPDATE_ERROR_FREEZE = "mem_update_error_freeze";

	/** 个人会员更新密码：登录失败次数过多，会员被登录锁定！. */
	public static final String MEM_UPDATE_ERROR_AUTOLOCK = "mem_update_error_autolock";

	/** 企业会员更新密码：会员被冻结！. */
	public static final String ENTER_UPDATE_ERROR_FREEZE = "enter_update_error_freeze";

	/** 企业会员更新密码：登录失败次数过多，会员被登录锁定！. */
	public static final String ENTER_UPDATE_ERROR_AUTOLOCK = "enter_update_error_autolock";
	/** 企业会员更新密码：重置企业用户支付密码错误. */
	public static final String ENTERPRISE_RESET_PAYPSW_ERROR = "enterprise_reset_paypsw_error";
	/** 企业会员更新密码：更新企业用户支付密码错误. */
	public static final String ENTERPRISE_UPDATE_PAYPSW_ERROR = "enterprise_update_paypsw_error";


	/** 企业会员注册错误. */
	public static final String ENTER_MEM_OPEN_ERROR = "enter_mem_open_error";

	/** 企业会员错误-EMAIL已经存在. */
	public static final String ENTER_EMAIL_ERROR = "mem_open_email_error";
	/** 企业会员错误-手机号已经存在. */
	public static final String ENTER_MOBILE_ERROR = "enter_mobile_error";
	/** 企业会员错误-登录名已经存在. */
	public static final String ENTER_LOGINNAME_ERROR = "enter_loginname_error";
	/** 企业会员更新信息错误. */
	public static final String ENTER_UPDATE_ERROR = "enter_update_error";

	/** 企业会员更新密码：原密码错误. */
	public static final String ENTER_UPDATE_ORGPSW_ERROR = "enter_update_orgpsw_error";
	/** 企业会员更新密码：更新企业用户密码错误. */
	public static final String ENTER_UPDATE_PSW_ERROR = "enter_update_psw_error";
	/** 重置企业用户密码错误. */
	public static final String ENTER_RESET_PSW_ERROR = "enter_reset_psw_error";

	/** 查找个人会员信息错误. */
	public static final String PERSON_MEM_FIND_ERROR = "person_mem_find_error";
	/** 查找平台商信息错误. */
	public static final String PLATFORM_MEM_FIND_ERROR = "platform_mem_find_error";
	/** 通过平台商编号查找企业会员信息错误. */
	public static final String ENTERPRISE_MEM_FIND_ERROR_PLATFORM_NO = "enterprise_mem_find_error_platform_no";
	/** 查找企业用户API信息错误. */
	public static final String ENTER_API_FIND_ERROR = "enter_api_find_error";
	/** 查找个人会员实名认证信息错误. */
	public static final String PERSON_AUTH_FIND_ERROR = "person_auth_find_error";

	/** 已注销会员不允许更新会员状态. */
	public static final String PERSON_STATUS_UPDATE_ERROR = "person_status_update_error";

	/** 已注销会员不允许更新会员状态. */
	public static final String ENTERPRISE_STATUS_UPDATE_ERROR = "enterprise_status_update_error";

	/** 分页查询个人会员信息错误. */
	public static final String PERSON_MEM_FIND_PAGE_ERROR = "person_mem_find_page_error";

	/** 分页查询个人会员登录信息错误. */
	public static final String MEM_LOGIN_INFO_FIND_PAGE_ERROR = "mem_login_info_find_page_error";

	/** 分页查询企业会员信息错误. */
	public static final String ENTERPRISE_MEM_FIND_PAGE_ERROR = "enterprise_mem_find_page_error";

	/** 分页查询平台商信息错误. */
	public static final String PLATFORM_MEM_FIND_PAGE_ERROR = "platform_mem_find_page_error";

	/** 查询可注册的平台商列表错误. */
	public static final String PLATFORM_MEM_FIND_BY_REGISTER_ERROR = "platform_mem_find_by_register_error";



	/** 查找商户会员信息错误. */
	public static final String DAELER_FIND_ERROR = "daeler_find_error";
	/** 查找商户会员信息错误-商户号不唯一. */
	public static final String DAELER_FIND_REPEAT_ERROR = "daeler_find_repeat_error";
	/** 查找企业会员信息错误-手机号不唯一. */
	public static final String ENTER_FIND_MOBILE_REPEAT_ERROR = "enter_find_mobile_repeat_error";
	/** 查找个人会员信息错误-手机号不唯一. */
	public static final String PERSON_FIND_MOBILE_REPEAT_ERROR = "person_find_mobile_repeat_error";
	/** 查找企业会员信息错误-EMAIL不唯一. */
	public static final String ENTER_FIND_EMAIL_REPEAT_ERROR = "enter_find_email_repeat_error";
	/** 查找商户账户信息错误. */
	public static final String DAELER_ACCOUNT_FIND_ERROR = "daeler_account_find_error";
	/** 查找企业会员账户信息错误. */
	public static final String ENTER_ACCOUNT_FIND_ERROR = "enter_account_find_error";
	/** 查找个人会员账户信息错误. */
	public static final String PERSON_ACCOUNT_FIND_ERROR = "person_account_find_error";
	/** 查找商户、账户信息错误. */
	public static final String DAELER_AND_ACCOUNT_FIND_ERROR = "daeler_and_account_find_error";
	/** 查找企业、账户信息错误. */
	public static final String ENTER_AND_ACCOUNT_FIND_ERROR = "enter_and_account_find_error";
	/** 查找个人、账户信息错误. */
	public static final String PERSON_AND_ACCOUNT_FIND_ERROR = "person_and_account_find_error";
	/** 个人会员登录错误. */
	public static final String PERSON_LOGIN_ERROR = "person_login_error";
	/** 添加会员登录信息错误. */
	public static final String PERSON_ADD_LOGIN_INFO_ERROR = "person_add_login_info_error";
	/** 个人会员登录错误：会员不存在. */
	public static final String PERSON_LOGIN_ERROR_NOT_EXIST = "person_login_error_not_exist";
	/** 会员不存在. */
	public static final String PERSON_ERROR_NOT_EXIST = "person_error_not_exist";
	/** 会员没有实名认证. */
	public static final String PERSON_ERROR_NOT_AUTH = "person_error_not_auth";
	/** 会员实名认证信息错误. */
	public static final String PERSON_ERROR_AUTH_INFO = "person_error_auth_info";
	/** 个人会员登录错误：会员被冻结. */
	public static final String PERSON_LOGIN_ERROR_FREEZE = "person_login_error_freeze";
	/** 个人会员登录错误：登录失败次数过多，会员被登录锁定. */
	public static final String PERSON_LOGIN_ERROR_AUTOLOCK = "person_login_error_autolock";
	/** 企业会员登录错误：登录失败次数过多，会员被登录锁定. */
	public static final String ENTERPRIS_LOGIN_ERROR_AUTOLOCK = "enterpris_login_error_autolock";
	/** 个人会员登录错误：登录密码错误. */
	public static final String PERSON_LOGIN_ERROR_PSW = "person_login_error_psw";

	/** 个人会员登录错误：登录密码错误，错误次数已超过限制，账户暂时冻结. */
	public static final String PERSON_LOGIN_ERROR_PSW_FREEZE = "person_login_error_psw_freeze";

	/** 企业会员登录错误：登录密码错误，错误次数已超过限制，账户暂时冻结. */
	public static final String ENTERPRIS_LOGIN_ERROR_PSW_FREEZE = "enterpris_login_error_psw_freeze";
	/** 个人会员登录错误：根据手机号和密码查询！返回的条数大于1条!!有重复数据!. */
	public static final String PERSON_LOGIN_ERROR_REPEAT = "person_login_error_repeat";
	/** 企业会员登录错误. */
	public static final String ENTERPRIS_LOGIN_ERROR = "enterpris_login_error";
	/** 企业会员登录错误：会员不存在. */
	public static final String ENTERPRIS_LOGIN_ERROR_NOT_EXIST = "enterpris_login_error_not_exist";
	/** 企业会员登录错误：会员被冻结. */
	public static final String ENTERPRIS_LOGIN_ERROR_FREEZE = "enterpris_login_error_freeze";
	/** 企业会员登录错误：登录密码错误. */
	public static final String ENTERPRIS_LOGIN_ERROR_PSW = "enterpris_login_error_psw";
	/** 个人会员登录错误：根据手机号和密码查询！返回的条数大于1条!!有重复数据!. */
	public static final String ENTERPRIS_LOGIN_ERROR_REPEAT = "enterpris_login_error_repeat";

	/** 会员编号不符合规则. */
	public static final String MEMBER_NO_ERROR = "member_no_error";

	/** 操作员注册错误. */
	public static final String OPERATOR_OPEN_ERROR = "operator_open_error";

	/** 平台商注册错误. */
	public static final String PLATFORM_OPEN_ERROR = "platform_open_error";

	/** 操作员错误-手机号已经存在. */
	public static final String OPERATOR_MOBILE_ERROR = "operator_mobile_error";
	/** 操作员错误-EMAIL已经存在. */
	public static final String OPERATOR_EMAIL_ERROR = "operator_email_error";
	/** 操作员错误-登录名已经存在. */
	public static final String OPERATOR_LOGINNAME_ERROR = "operator_loginname_error";


	/** 商户密钥信息查询错误. */
	public static final String MERCHANT_KEY_QUERY_ERROR = "merchant_key_query_error";

	/** 更新商户密钥信息错误. */
	public static final String MERCHANT_KEY_UPDATE_ERROR = "merchant_key_update_error";


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

	/** 会员已实名. */
	public static final String MBR_MEMBER_HAVE_BEEN_NAME_AUTHENTICATION = "mbr_member_have_been_name_authentication";

	/** 会员未实名. */
	public static final String MBR_MEMBER_NOT_BEEN_NAME_AUTHENTICATION = "mbr_member_not_been_name_authentication";

	/** 银行卡号已经存在. */
	public static final String BANKCARD_NO_EXISTS_ERROR = "bankcard_no_exists_error";

	/** 银行卡号绑定的账号数量超过限制. */
	public static final String BANKCARD_MAX_COUNT_ERROR = "bankcard_max_count_error";
	/** 银行CODE不存在. */
	public static final String BANKCODE_NOT_EXISTS_ERROR = "bankcode_not_exists_error";

	/** 银行卡号不合法. */
	public static final String BANK_NO_UNVALID_ERROR = "bank_no_unvalid_error";
	/** 银行卡号不存在. */
	public static final String BANK_NO_NOT_EXISTS_ERROR = "bank_no_not_exists_error";
	/** 不能添加贷记卡. */
	public static final String CREDIT_ERROR = "credit_error";
	/** 无法识别卡类型. */
	public static final String CARD_TYPE_ERROR = "card_type_error";
	/** 添加银行卡错误. */
	public static final String ADD_BANKCARD_ERROR = "add_bankcard_error";
	/** 删除银行卡错误. */
	public static final String DEL_BANKCARD_ERROR = "del_bankcard_error";
	/** 批量查找银行卡错误. */
	public static final String MUTI_FIND_BANKCARD_ERROR = "muti_find_bankcard_error";
	/** 查找银行卡错误. */
	public static final String FIND_BANKCARD_ERROR = "find_bankcard_error";

	/** 批量查找银行卡错误. */
	public static final String MUTI_FIND_BANKCARD_ENTER_ERROR = "muti_find_bankcard_enter_error";
	/** 查找银行卡错误. */
	public static final String FIND_BANKCARD_ENTER_ERROR = "find_bankcard_enter_error";

	/** 银行卡号已经存在. */
	public static final String BANKCARD_NO_EXISTS_ENTER_ERROR = "bankcard_no_exists_enter_error";
	/** 银行CODE不存在. */
	public static final String BANKCODE_NOT_EXISTS_ENTER_ERROR = "bankcode_not_exists_enter_error";
	/** 添加银行卡错误. */
	public static final String ADD_BANKCARD_ENTER_ERROR = "add_bankcard_enter_error";
	/** 修改银行卡错误. */
	public static final String UPDATE_BANKCARD_ENTER_ERROR = "update_bankcard_enter_error";


	/** 查找企业会员信息错误. */
	public static final String ENTERPRISE_MEM_FIND_ERROR = "enterprise_mem_find_error";

	/** 查找企业用户API信息错误. */
	public static final String ENTERPRISE_API_FIND_ERROR = "enterprise_mem_find_error";

	/** 查找员工信息错误. */
	public static final String FOXCONN_PERSON_FIND_ERROR = "foxconn_person_find_error";

	// 创建企业API接入记录异常
	public static final String ADD_ENTERPRISE_API_ERROR = "add_enterprise_api_error";

	// 查询企业API接入记录异常
	public static final String FIND_ENTERPRISE_API_ERROR = "find_enterprise_api_error";

	// 更新企业API接入记录异常
	public static final String UPDATE_ENTERPRISE_API_ERROR = "update_enterprise_api_error";

	// 商户接入平台商查询异常
	public static final String PLATFORM_ACCESS_QUERY_ERROR = "platform_access_query_error";

	// 商户接入平台商新增异常
	public static final String PLATFORM_ACCESS_ADD_ERROR = "platform_access_add_error";

	// 商户没有接入平台商
	public static final String MERCHANT_NOT_ACCESS_PLATFORM = "merchant_not_access_platform";

	// 商户接入平台商已过期
	public static final String PLATFORM_ACCESS_EXPIRED = "platform_access_expired";

	// 查询平台商地址错误
	public static final String FIND_PLATFORM_ADDRESS_ERROR = "find_platform_address_error";

	// 添加平台商地址错误
	public static final String ADD_PLATFORM_ADDRESS_ERROR = "add_platform_address_error";

	// 平台商地址不存在
	public static final String PLATFORM_ADDRESS_NOT_EXISTS = "platform_address_not_exists";

	// 修改平台商地址错误
	public static final String UPDATE_PLATFORM_ADDRESS_ERROR = "update_platform_address_error";

	// 删除平台商地址错误
	public static final String DELETE_PLATFORM_ADDRESS_ERROR = "delete_platform_address_error";

	// 平台商区域地址不正确
	public static final String PLATFORM_ADDRESS_AREA_EXISTS = "platform_address_area_error";

	// 添加入驻企业失败
	public static final String ENTER_ENTERPRISE_FAILURE = "enter_enterprise_failure";

	// 添加入驻失败
	public static final String PARK_EMPTY = "park_empty";

	// 添加入驻企业失败
	public static final String ENTERPRISE_NAME_EMPTY = "enterprise_name_empty";

	// 添加入驻失败
	public static final String LINK_PERSON_EMPTY = "link_person_empty";

	// 添加入驻失败
	public static final String LINK_MOBLIE_EMPTY = "link__empty";

	// 添加入驻园区失败
	public static final String ENTER_PARK_FAILURE = "enter_park_failure";

	// 查询入驻企业失败
	public static final String QUERY_ENTERPRISE_FAILURE = "query_enterprise_failure";

	// 查询入驻园区失败
	public static final String QUERY_PARK_FAILURE = "query_park_failure";

	/** 个人会员没有加入企业错误. */
	public static final String PUNCH_CARD_SET_NOT_JOIN_ENTERPRISE_ERROR = "punch_card_set_not_join_enterprise_error";
	
	// 企业展示信息失败
	public static final String ENTERPRISE_FAILURE = "enterprise_failure";
	// 更新企业展示信息失败
	public static final String UPDATE_ENTERPRISE_FAILURE = "update_enterprise_failure";
	// 添加留言信息失败
	public static final String FEEDBACK_ADD_FAILURE = "feedback_add_failure";
	// 更新留言信息失败
	public static final String FEEDBACK_UPDATE_FAILURE = "feedback_update_failure";
	// 查询留言信息失败
	public static final String FEEDBACK_QUERY_FAILURE = "feedback_query_failure";
	// 删除留言信息失败
	public static final String FEEDBACK_DEL_FAILURE = "feedback_del_failure";
	
	// 添加加入企业失败
	public static final String JOIN_ENTERPRISE_FAILURE = "join_enterprise_failure";
	// 查询入驻园区失败
	public static final String QUERY_JOINENTERPRISE_FAILURE = "query_joinenterprise_failure";
	
	
	/** 查询平台商微信配置信息失败 */
	public static final String PWC_FIND_ERROR = "pwc_find_error";
	
	/** 新增平台商微信配置信息失败 */
	public static final String PWC_ADD_ERROR = "pwc_add_error";
	
	/** 修改平台商微信配置信息失败 */
	public static final String PWC_UPDATE_ERROR = "pwc_update_error";
	
	/** 商户没有配置接入信息 **/
	public static final String MAC_NOT_ACCESS = "mac_not_access";
	
	/**新增微信朋友圈评论表信息错误！ */
	public static final String WX_CHAT_INFO_ADD_ERROR = "wx_chat_info_add_error";
	
	/**删除微信朋友圈评论表信息错误！ */
	public static final String WX_CHAT_INFO_DEL_ERROR = "wx_chat_info_del_error";
	
	/**微信朋友圈评论表信息更新信息错误！ */
	public static final String WX_CHAT_INFO_UPDATE_ERROR = "wx_chat_info_update_error";
	
	/**微信朋友圈评论表信息不存在 */
	public static final String WX_CHAT_INFO_NOT_EXIST_ERROR = "wx_chat_info_not_exist_error";
	
	/**查找微信朋友圈评论表信息信息错误！ */
	public static final String WX_CHAT_INFO_FIND_ERROR = "wx_chat_info_find_error";

	/**微信朋友圈评论表信息不存在错误.！ */
	public static final String WX_CHAT_INFO_FIND_PAGE_ERROR = "wx_chat_info_find_page_error";
	
	/** 新增微信朋友圈评论表信息错误. */
	public static final String WX_COMMENT_INFO_ADD_ERROR = "wx_comment_info_add_error";
	
	/** 删除微信朋友圈评论表信息错误. */
	public static final String WX_COMMENT_INFO_DEL_ERROR = "wx_comment_info_del_error";
	
	/** 微信朋友圈评论表信息更新错误. */
	public static final String WX_COMMENT_INFO_UPDATE_ERROR = "wx_comment_info_update_error";
	
	/** 查找微信朋友圈评论表信息错误. */
	public static final String WX_COMMENT_INFO_FIND_ERROR = "wx_comment_info_find_error";
	
	/** 微信朋友圈评论表信息分页查询错误. */
	public static final String WX_COMMENT_INFO_FIND_PAGE_ERROR = "wx_comment_info_find_page_error";
	
	/** 微信朋友圈评论表信息不存在错误. */
	public static final String WX_COMMENT_INFO_NOT_EXIST_ERROR = "wx_comment_info_not_exist_error";

	/** 发送IM聊天记录信息错误. */
	public static final String IM_CHAT_INFO_SEND_ERROR = "im_chat_info_send_error";
	/** 新增IM聊天记录信息错误. */
	public static final String IM_CHAT_INFO_ADD_ERROR = "im_chat_info_add_error";
	/** 删除IM聊天记录信息错误. */
	public static final String IM_CHAT_INFO_DEL_ERROR = "im_chat_info_del_error";
	/** IM聊天记录信息更新错误. */
	public static final String IM_CHAT_INFO_UPDATE_ERROR = "im_chat_info_update_error";
	/** 查找IM聊天记录信息错误. */
	public static final String IM_CHAT_INFO_FIND_ERROR = "im_chat_info_find_error";
	/** IM聊天记录信息分页查询错误. */
	public static final String IM_CHAT_INFO_FIND_PAGE_ERROR = "im_chat_info_find_page_error";
	/** IM聊天记录信息不存在错误. */
	public static final String IM_CHAT_INFO_NOT_EXIST_ERROR = "im_chat_info_not_exist_error";
	/** 消息类型非法. */
	public static final String IM_CHAT_TYPE_INVAILD = "im_chat_type_invaild";
	/** 离线IM聊天记录信息查询错误. */
	public static final String IM_OFFLINE_CHAT_INFO_FIND_ERROR = "im_offline_chat_info_find_error";
	/** 查找导购与客户的最后一条聊天记录错误. */
	public static final String IM_LAST_MATCH_CHAT_INFO_FIND_ERROR = "im_last_match_chat_info_find_error";
	/** 撤回聊天记录错误. */
	public static final String IM_CHAT_INFO_CANCEL_ERROR = "im_chat_info_cancel_error";
	/** 超过发送时间不能撤回聊天记录错误. */
	public static final String IM_CHAT_INFO_CANCEL_TIMEOUT_ERROR = "im_chat_info_cancel_timeout_error";
	
	/** 新增IM表情包信息错误. */
	public static final String IM_EMOJI_PACKAGE_ADD_ERROR = "im_emoji_package_add_error";
	/** 删除IM表情包信息错误. */
	public static final String IM_EMOJI_PACKAGE_DEL_ERROR = "im_emoji_package_del_error";
	/** IM表情包信息更新错误. */
	public static final String IM_EMOJI_PACKAGE_UPDATE_ERROR = "im_emoji_package_update_error";
	/** 查找IM表情包信息错误. */
	public static final String IM_EMOJI_PACKAGE_FIND_ERROR = "im_emoji_package_find_error";
	/** IM表情包信息分页查询错误. */
	public static final String IM_EMOJI_PACKAGE_FIND_PAGE_ERROR = "im_emoji_package_find_page_error";
	/** IM表情包信息不存在错误. */
	public static final String IM_EMOJI_PACKAGE_NOT_EXIST_ERROR = "im_emoji_package_not_exist_error";
	
	/** 新增IM表情信息错误. */
	public static final String IM_EMOJI_ADD_ERROR = "im_emoji_add_error";
	/** 删除IM表情信息错误. */
	public static final String IM_EMOJI_DEL_ERROR = "im_emoji_del_error";
	/** IM表情信息更新错误. */
	public static final String IM_EMOJI_UPDATE_ERROR = "im_emoji_update_error";
	/** 查找IM表情信息错误. */
	public static final String IM_EMOJI_FIND_ERROR = "im_emoji_find_error";
	/** IM表情信息分页查询错误. */
	public static final String IM_EMOJI_FIND_PAGE_ERROR = "im_emoji_find_page_error";
	/** IM表情信息不存在错误. */
	public static final String IM_EMOJI_NOT_EXIST_ERROR = "im_emoji_not_exist_error";
	/** 查询发送朋友圈任务错误. */
    public static final String SEND_FRIENDS_JOB_FIND_ERROR = "send_friends_job_find_error";
    /** 新增发送朋友圈任务错误. */
    public static final String SEND_FRIENDS_JOB_ADD_ERROR = "send_friends_job_add_error";
    /** 发送微信号不属于该助手管理. */
    public static final String SEND_FRIENDS_WX_ERROR = "send_friends_wx_error";
    /** 暂不支持该类型朋友圈素材. */
    public static final String FRIENDS_MATERIAL_TYPE_NOT_SUPPORT_ERROR = "friends_material_type_not_support_error";
    /** 发送朋友圈，保存任务时执行时间不能为空. */
    public static final String SEND_FRIENDS_EXECUTE_TIME_EMPTY_ERROR = "send_friends_execute_time_empty_error";
    /** 发送朋友圈，执行时间不能早于当前时间. */
    public static final String SEND_FRIENDS_EXECUTE_TIME_BEFORE_NOW_ERROR = "send_friends_execute_time_before_now_error";
    /** 群发优惠券，保存任务时执行时间不能为空. */
    public static final String COUPON_MULTI_PUSH_EXECUTE_TIME_EMPTY_ERROR = "coupon_multi_push_execute_time_empty_error";
    /** 群发优惠券，执行时间不能早于当前时间. */
    public static final String COUPON_MULTI_PUSH_EXECUTE_TIME_BEFORE_NOW_ERROR = "coupon_multi_push_execute_time_before_now_error";
    /** 群发优惠券，优惠券不存在. */
    public static final String COUPON_MULTI_PUSH_COUPON_RULE_NOT_FOUND_ERROR = "coupon_multi_push_coupon_rule_not_found_error";
    /** 新增优惠券群发任务错误. */
    public static final String COUPON_MULTI_PUSH_JOB_ADD_ERROR = "coupon_multi_push_job_add_error";
    /** 终端微信有群发任务在执行中. */
    public static final String COUPON_MULTI_PUSH_SHOP_NO_WX_PENDING_ERROR = "coupon_multi_push_shop_no_wx_pending_error";
    /** 该优惠券在发送前可能过期. */
    public static final String COUPON_MULTI_PUSH_COUPON_EXECUTE_DATE_AFTER_EXPIRE_DATE_ERROR = "coupon_multi_push_coupon_execute_date_after_expire_date_error";
    

	/** 新增微信公众号信息错误. */
	public static final String WX_PUBLIC_ACCOUNT_ADD_ERROR = "wx_public_account_add_error";
	/** 删除微信公众号信息错误. */
	public static final String WX_PUBLIC_ACCOUNT_DEL_ERROR = "wx_public_account_del_error";
	/** 微信公众号信息更新错误. */
	public static final String WX_PUBLIC_ACCOUNT_UPDATE_ERROR = "wx_public_account_update_error";
	/** 查找微信公众号信息错误. */
	public static final String WX_PUBLIC_ACCOUNT_FIND_ERROR = "wx_public_account_find_error";
	/** 微信公众号信息分页查询错误. */
	public static final String WX_PUBLIC_ACCOUNT_FIND_PAGE_ERROR = "wx_public_account_find_page_error";
	/** 微信公众号信息不存在错误. */
	public static final String WX_PUBLIC_ACCOUNT_NOT_EXIST_ERROR = "wx_public_account_not_exist_error";
	
	
	/** 新增微信小程序信息错误. */
	public static final String WX_SMALL_PROGRAM_ADD_ERROR = "wx_small_program_add_error";
	/** 删除微信小程序信息错误. */
	public static final String WX_SMALL_PROGRAM_DEL_ERROR = "wx_small_program_del_error";
	/** 微信小程序信息更新错误. */
	public static final String WX_SMALL_PROGRAM_UPDATE_ERROR = "wx_small_program_update_error";
	/** 查找微信小程序信息错误. */
	public static final String WX_SMALL_PROGRAM_FIND_ERROR = "wx_small_program_find_error";
	/** 微信小程序信息分页查询错误. */
	public static final String WX_SMALL_PROGRAM_FIND_PAGE_ERROR = "wx_small_program_find_page_error";
	/** 微信小程序信息不存在错误. */
	public static final String WX_SMALL_PROGRAM_NOT_EXIST_ERROR = "wx_small_program_not_exist_error";
	
	/** 新增商户发朋友圈任务信息错误. */
	public static final String MERCHANT_SEND_FRIENDS_JOB_ADD_ERROR = "merchant_send_friends_job_add_error";
	/** 商户发朋友圈任务信息更新错误. */
	public static final String MERCHANT_SEND_FRIENDS_JOB_UPDATE_ERROR = "merchant_send_friends_job_update_error";
	/** 查找商户发朋友圈任务信息错误. */
	public static final String MERCHANT_SEND_FRIENDS_JOB_FIND_ERROR = "merchant_send_friends_job_find_error";
	/** 商户发朋友圈任务信息分页查询错误. */
	public static final String MERCHANT_SEND_FRIENDS_JOB_FIND_PAGE_ERROR = "merchant_send_friends_job_find_page_error";
	/** 商户发朋友圈任务信息不存在错误. */
	public static final String MERCHANT_SEND_FRIENDS_JOB_NOT_EXIST_ERROR = "merchant_send_friends_job_not_exist_error";
}
