/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

package com.lj.business.weixin.emus;

/**
 * 
 * 
 * 类说明：消息发送状态枚举
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2017年10月27日
 */
public enum MessageStatus {
	
	/**
	 * 未发送
	 */
	OFFLINE("1","未发送"),
	/**
	 * 发送成功
	 */
	SUCCESS("2","发送成功"),
	/**
	 * 发送失败
	 */
	FAILURE("3","发送失败"),
	/**
	 * 客户已撤回，保留原消息
	 */
	CANCEL("4","已撤回"),
	;
	
	MessageStatus(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	private String code;
	private String name;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
