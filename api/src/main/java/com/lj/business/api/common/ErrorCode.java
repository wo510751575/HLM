package com.lj.business.api.common;

/**
 * 
 * 
 * 类说明：错误编码
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public interface ErrorCode {
	
	/**
	 * 时间戳为空
	 */
	public static final String TIMESTAMP_IS_EMPTY = "api_timestamp_is_empty";
	
	/**
	 * 时间戳错误
	 */
	public static final String TIMESTAMP_ERROR = "api_timestamp_error";

	/**
	 * 平台商编号为空
	 */
	public static final String PLATFORM_NO_IS_EMPTY = "api_platform_no_is_empty";
	
	/**
	 * 平台商编号错误
	 */
	public static final String PLATFORM_NO_ERROR = "api_platform_no_error";
	
	/**
	 * 接入应用KEY为空
	 */
	public static final String APP_KEY_IS_EMPTY = "api_app_key_is_empty";
	
	/**
	 * 接入应用KEY错误
	 */
	public static final String APP_KEY_ERROR = "api_app_key_error";
	
	/**
	 * 接入应用KEY过期
	 */
	public static final String APP_KEY_EXPIRED = "api_app_key_expired";
	
	/**
	 * 接入应用密钥为空
	 */
	public static final String APP_SECRET_IS_EMPTY = "api_app_secret_is_empty";
	
	/**
	 * 接入应用密钥非法
	 */
	public static final String APP_SECRET_ERROR = "api_app_secret_is_error";
	
	/**
	 * 终端标识为空
	 */
	public static final String TERMINAL_ID_IS_EMPTY = "api_terminal_Id_is_empty";
	
	/**
	 * token为空
	 */
	public static final String TOKEN_IS_EMPTY = "api_token_is_empty";
	
	/**
	 * 登录超时
	 */
	public static final String TOKEN_TIMEOUT = "api_token_timeout";
	
	/**
	 * token刷新失败
	 */
	public static final String TOKEN_REFRESH_ERROR = "api_token_refresh_error";
	
	/**
	 * 签名为空
	 */
	public static final String SIGNATURE_IS_EMPTY = "api_signature_is_empty";
	
	/**
	 * 签名错误
	 */
	public static final String SIGNATURE_ERROR = "api_signature_error";
	
	/**
	 * 非法请求
	 */
	public static final String ACCESS_VALID = "api_access_valid";
	
	/**
	 * 请求参数错误
	 */
	public static final String PARAM_ERROR = "api_param_error";
	
	/**
	 * 请求参数为空
	 */
	public static final String PARAM_IS_EMPTY = "api_param_is_empty";
	
	/**
	 * 商户编号为空
	 */
	public static final String MERCHANT_NO_IS_EMPTY = "api_merchant_no_is_empty";
	
	/**
	 * 商户编号错误
	 */
	public static final String MERCHANT_NO_ERROR = "api_merchant_no_error";
	
	/**
	 * 加密KEY为空
	 */
	public static final String ENCRYPT_KEY_IS_EMPTY = "api_encrypt_key_is_empty";
	
	/**
	 * 短信验证码已存在，不能频繁发送
	 */
	public static final String SMS_CODE_EXIST = "api_sms_code_exist";
	
	/**
	 * 短信验证码错误
	 */
	public static final String SMS_CODE_ERROR = "api_sms_code_error";
	
	/**
	 * 注册失败
	 */
	public static final String REGISTER_ERROR = "api_register_error";
	
	/**
	 * 重置登录密码失败
	 */
	public static final String RESET_LOGIN_PASSWORD_ERROR = "api_reset_login_password_error";
	
	/**
	 * 重置登录密码_会员不存在_失败
	 */
	public static final String RESET_LOGIN_PASSWORD_MEMBER_NOT_EXISTS_ERROR = "api_reset_login_password_member_not_exists_error";
	
	/**
	 * 重置支付密码失败
	 */
	public static final String RESET_PAY_PASSWORD_ERROR = "api_reset_pay_password_error";
	
	/**
	 * 上传头像文件为空
	 */
	public static final String HEAD_IMAGE_IS_EMPTY = "api_head_image_is_empty";
	
	/**
	 * 上传物品放行条图片为空
	 */
	public static final String RELEASE_IMAGE_IS_EMPTY = "api_RELEASE_image_is_empty";
	
	/**
	 * 系统升级中
	 */
	public static final String SYSTEM_UPGRADE = "api_system_upgrade";
	
	/**
	 * 当前功能不可用，版本过低不支持
	 */
	public static final String UNSUPPORTED_FUNCTION = "api_unsupported_function";
	
	/**
	 * 不支持的文件格式
	 */
	public static final String UNSUPPORTED_FILE_FORMAT = "unsupported_file_format";

	/**
	 * MEC相关异常
	 */
	public static final String MEC_ERROR = "MEC_ERROR";
	
	/**
	 * 考試結束
	 */
	public static final String RETEST_STOP = "RETEST_STOP";
	
	/**
	 * 面试官不存在
	 */
	public static final String INTERVIEWER_NOT_EXIST = "INTERVIEWER_NOT_EXIST";
	
	/**
	 * 无法识别该员工号
	 */
	public static final String EMPLOYEE_EXIST_ERROR ="employee_not_exist_error";
	/**
	 * 没有绑定打卡班次
	 */
	public static final String CLOCK_IN_WORK_SET_NOT_EXIST_ERROR = "clock_in_work_set_not_exist_error";
}
