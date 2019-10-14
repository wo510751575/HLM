package com.lj.business.ai.common;

public class ResultCode {

	/**
	 * 匹配次数超限
	 */
	public static Integer MAX_MATCH = 50000;

	/**
	 * 匹配成功
	 */
	public static Integer MATCH_SUCCESS = 10000;

	/**
	 * 匹配失败
	 */
	public static Integer MATCH_FAIL = 20000;

	/**
	 * 用户确认解决
	 */
	public static Integer CUSTOM_CONFIM = 30000;
	
	/**
	 * 超时确认
	 */
	public static Integer TIMEOUT_CONFIM = 30001;
	
	/**
	 *  没有匹配内容
	 */
	public static Integer NOT_PROBLEM=40000;
	

}
