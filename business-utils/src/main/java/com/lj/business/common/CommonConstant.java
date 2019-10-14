package com.lj.business.common;


/**
 * 
 * 
 * 类说明：公用常量
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年8月16日
 */
public class CommonConstant {
	
	/**
	 * 共用分隔符
	 */
	public static final String SPLIT_DOU_HAO = ",";
	
	/**
	 * 创建用户：system
	 */
	public static final String SYSTEM = "system";
	
	/**
	 * HTTP地址前缀
	 */
	public static final String HTTP_PRE = "http://";
	
	
	/**
	 * 任务备注-替换字段
	 */
	public static final String REPLACE_REMARK_COM = "{value}";
	
	public static final String VALIDATION_CODE_KEY_PREFIX = "smsValidationCode";
	public static final String VALIDATION_CODE_KEY_PREFIX_MH = "smsValidationCodeMH";
	public static final String PROCESS_CODE_KEY_PREFIX = "smsProcessCode";
	public static final String PROCESS_CODE_KEY_PREFIX_MH = "smsProcessCodeMH";
	
	/**
	 * 微信陌生人对应username的后缀
	 */
	public static final String WX_STRANGER_USERNAME_SUFFIX = "@stranger"; 
	
	public static final String Y = "Y";
	public static final String N = "N";
	
	public static final String YES = "YES";
	public static final String NO = "NO";
	
	public static final String ON = "ON";
	public static final String OFF = "OFF";
	
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	
	public static final int I_YES = 1;
	public static final int I_NO = 0;
	
	/**
	 * 门店级别
	 */
	public static final String GRADE_SHOP = "4";
	/**
     * 经销商级别
     */
	public static final String GRADE_ORG = "3";
	/**
     * 商户级别
     */
	public static final String GRADE_MERCHANT = "2";

	//netty地址key
	public static final String REDISNETTYKEY = "REDIS-nettyAddress-";
}
